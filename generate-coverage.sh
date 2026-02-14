#!/usr/bin/env bash
#
# Generates JaCoCo coverage reports for all modules and writes
# a combined summary to COVERAGE.md in the project root.
#

set -eo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

echo "Running tests and generating JaCoCo reports..."
if ! ./gradlew clean test jacocoTestReport > /dev/null 2>&1; then
    echo "ERROR: Gradle build failed. Run './gradlew clean test' to see details."
    exit 1
fi

OUTPUT="COVERAGE.md"

MODULE_NAMES=(
    "locationtech-core"
    "locationtech-spatial4j"
    "locationtech-proj4j"
    "postgis-jdbc"
    "postgis-jdbc-geometry"
    "postgis-jdbc-jts"
    "geolatte-geom"
    "esri-geometry-api"
    "uber-h3"
)
MODULE_PATHS=(
    "locationtech/jts/core/build/reports/jacoco/test/jacocoTestReport.xml"
    "locationtech/spatial4j/build/reports/jacoco/test/jacocoTestReport.xml"
    "locationtech/proj4j/build/reports/jacoco/test/jacocoTestReport.xml"
    "postgis-java/postgis-jdbc/build/reports/jacoco/test/jacocoTestReport.xml"
    "postgis-java/postgis-jdbc-geometry/build/reports/jacoco/test/jacocoTestReport.xml"
    "postgis-java/postgis-jdbc-jts/build/reports/jacoco/test/jacocoTestReport.xml"
    "geolatte/geom/build/reports/jacoco/test/jacocoTestReport.xml"
    "esri/geometry-api/build/reports/jacoco/test/jacocoTestReport.xml"
    "uber/h3/build/reports/jacoco/test/jacocoTestReport.xml"
)

# Extract the last occurrence of a counter type (report-level total)
# Usage: parse_counter <xml_file> <type> -> prints "missed covered"
parse_counter() {
    local file="$1" type="$2"
    local result
    result=$(grep -oE "counter type=\"${type}\" missed=\"[0-9]+\" covered=\"[0-9]+\"" "$file" \
        | tail -1 \
        | sed -E 's/.*missed="([0-9]+)" covered="([0-9]+)".*/\1 \2/' || true)
    if [ -z "$result" ]; then
        echo "0 0"
    else
        echo "$result"
    fi
}

calc_pct() {
    local missed="$1" covered="$2"
    local total=$((missed + covered))
    if [ "$total" -eq 0 ]; then
        echo "N/A"
    else
        awk "BEGIN { printf \"%.1f%%\", ($covered / $total) * 100 }"
    fi
}

# Extract per-class data from a JaCoCo XML report.
# Splits the single-line XML into one <class>...</class> block per line,
# then parses each block with sed.
parse_classes() {
    local xml="$1"
    # Put each <class ...>...</class> on its own line
    sed 's/<class /\n<class /g' "$xml" \
    | while IFS= read -r line; do
        # Only process lines starting with <class
        case "$line" in
            '<class '*);;
            *) continue;;
        esac

        # Extract class name
        class_fqn=$(echo "$line" | sed -E 's/^<class name="([^"]+)".*/\1/')
        class_simple=$(echo "$class_fqn" | sed 's:.*/::')

        # Extract counters - take the FIRST occurrence of each type within this block
        # (class-level counters come before </class>, before any sourcefile counters)
        inst_m=0; inst_c=0; br_m=0; br_c=0; ln_m=0; ln_c=0; mt_m=0; mt_c=0

        counter_data=$(echo "$line" | grep -oE 'counter type="[A-Z]+" missed="[0-9]+" covered="[0-9]+"' || true)

        while IFS= read -r counter; do
            [ -z "$counter" ] && continue
            ctype=$(echo "$counter" | sed -E 's/counter type="([^"]+)".*/\1/')
            cmissed=$(echo "$counter" | sed -E 's/.*missed="([0-9]+)".*/\1/')
            ccovered=$(echo "$counter" | sed -E 's/.*covered="([0-9]+)".*/\1/')
            case "$ctype" in
                INSTRUCTION) inst_m=$cmissed; inst_c=$ccovered;;
                BRANCH)      br_m=$cmissed;   br_c=$ccovered;;
                LINE)        ln_m=$cmissed;   ln_c=$ccovered;;
                METHOD)      mt_m=$cmissed;   mt_c=$ccovered;;
            esac
        done <<< "$counter_data"

        inst_pct=$(calc_pct "$inst_m" "$inst_c")
        br_pct=$(calc_pct "$br_m" "$br_c")
        ln_pct=$(calc_pct "$ln_m" "$ln_c")
        mt_pct=$(calc_pct "$mt_m" "$mt_c")

        # Skip classes with no coverage data (all N/A)
        if [ "$inst_pct" = "N/A" ] && [ "$br_pct" = "N/A" ] && [ "$ln_pct" = "N/A" ] && [ "$mt_pct" = "N/A" ]; then
            continue
        fi

        echo "| $class_simple | $inst_pct | $br_pct | $ln_pct | $mt_pct |"
    done
}

{
    echo "# Coverage Report"
    echo ""
    echo "Generated on $(date '+%Y-%m-%d %H:%M:%S')"
    echo ""
    echo "## Summary"
    echo ""
    echo "| Module | Instruction | Branch | Line | Method | Class |"
    echo "|--------|------------|--------|------|--------|-------|"

    total_inst_m=0; total_inst_c=0
    total_br_m=0;   total_br_c=0
    total_ln_m=0;   total_ln_c=0
    total_mt_m=0;   total_mt_c=0
    total_cl_m=0;   total_cl_c=0

    i=0
    while [ $i -lt ${#MODULE_NAMES[@]} ]; do
        module="${MODULE_NAMES[$i]}"
        xml="${MODULE_PATHS[$i]}"
        i=$((i + 1))

        if [ ! -f "$xml" ]; then
            echo "| $module | — | — | — | — | — |"
            continue
        fi

        inst=$(parse_counter "$xml" INSTRUCTION); inst_m=${inst% *}; inst_c=${inst#* }
        br=$(parse_counter "$xml" BRANCH);       br_m=${br% *};     br_c=${br#* }
        ln=$(parse_counter "$xml" LINE);         ln_m=${ln% *};     ln_c=${ln#* }
        mt=$(parse_counter "$xml" METHOD);       mt_m=${mt% *};     mt_c=${mt#* }
        cl=$(parse_counter "$xml" CLASS);        cl_m=${cl% *};     cl_c=${cl#* }

        total_inst_m=$((total_inst_m + inst_m)); total_inst_c=$((total_inst_c + inst_c))
        total_br_m=$((total_br_m + br_m));       total_br_c=$((total_br_c + br_c))
        total_ln_m=$((total_ln_m + ln_m));       total_ln_c=$((total_ln_c + ln_c))
        total_mt_m=$((total_mt_m + mt_m));       total_mt_c=$((total_mt_c + mt_c))
        total_cl_m=$((total_cl_m + cl_m));       total_cl_c=$((total_cl_c + cl_c))

        echo "| $module | $(calc_pct "$inst_m" "$inst_c") | $(calc_pct "$br_m" "$br_c") | $(calc_pct "$ln_m" "$ln_c") | $(calc_pct "$mt_m" "$mt_c") | $(calc_pct "$cl_m" "$cl_c") |"
    done

    echo "| **Total** | **$(calc_pct $total_inst_m $total_inst_c)** | **$(calc_pct $total_br_m $total_br_c)** | **$(calc_pct $total_ln_m $total_ln_c)** | **$(calc_pct $total_mt_m $total_mt_c)** | **$(calc_pct $total_cl_m $total_cl_c)** |"

    echo ""
    echo "## Module Details"

    i=0
    while [ $i -lt ${#MODULE_NAMES[@]} ]; do
        module="${MODULE_NAMES[$i]}"
        xml="${MODULE_PATHS[$i]}"
        i=$((i + 1))

        if [ ! -f "$xml" ]; then
            continue
        fi

        echo ""
        echo "### $module"
        echo ""
        echo "| Class | Instruction | Branch | Line | Method |"
        echo "|-------|------------|--------|------|--------|"

        parse_classes "$xml"
    done

} > "$OUTPUT"

echo "Coverage report written to $OUTPUT"
