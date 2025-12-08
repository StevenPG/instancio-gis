package com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.MultiLineStringGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SampleLineStringsTest {

    @Test
    void linestringGeoJsonValidation() {
        var multiLineString = new MultiLineStringGenerator()
                .lineStrings(
                        List.of(
                                SampleLineStrings.DOVER_MOTOR_SPEEDWAY_DELAWARE,
                                SampleLineStrings.DESERT_ROAD_NEW_MEXICO
                        )
                )
                .generate(null);

        assertNotNull(multiLineString);
        // https://atlas.co/tools/wkt-to-geojson/
        System.out.println(multiLineString.toText());
    }

}