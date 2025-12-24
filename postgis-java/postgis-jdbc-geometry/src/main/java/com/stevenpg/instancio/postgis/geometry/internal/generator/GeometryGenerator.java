/*
 * Copyright 2025 Steven Gantz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stevenpg.instancio.postgis.geometry.internal.generator;

import com.stevenpg.instancio.postgis.geometry.internal.generator.specs.NumericRangeSpec;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Geometry;
import org.instancio.Random;
import org.instancio.generator.AfterGenerate;
import org.instancio.generator.Generator;
import org.instancio.generator.Hints;

/** Generator that returns a random org.postgis Geometry subtype using WKT parsing. */
public class GeometryGenerator implements Generator<Geometry>, NumericRangeSpec<GeometryGenerator> {

    private double minX = -180d, maxX = 180d;
    private double minY = -90d, maxY = 90d;
    private int srid = 0;

    @Override
    public Hints hints() {
        return Hints.builder()
                .afterGenerate(AfterGenerate.DO_NOT_MODIFY)
                .build();
    }

    @Override
    public GeometryGenerator xRange(double minX, double maxX) {
        this.minX = minX; this.maxX = maxX; return this;
    }

    @Override
    public GeometryGenerator yRange(double minY, double maxY) {
        this.minY = minY; this.maxY = maxY; return this;
    }

    @Override
    public GeometryGenerator zRange(double minZ, double maxZ) {
        // Z not supported in random WKT generation for now to keep it simple,
        // but we could add it if needed.
        return this;
    }

    @Override
    public GeometryGenerator srid(int srid) {
        this.srid = srid;
        return this;
    }

    @Override
    public Geometry generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        int pick = r.nextInt(7); // 0..6 (POINT, LINESTRING, POLYGON, MULTIPOINT, MULTILINESTRING, MULTIPOLYGON, GEOMETRYCOLLECTION)
        String wkt = switch (pick) {
            case 0 -> pointWkt(r);
            case 1 -> lineStringWkt(r);
            case 2 -> polygonWkt(r);
            case 3 -> multiPointWkt(r);
            case 4 -> multiLineStringWkt(r);
            case 5 -> multiPolygonWkt(r);
            default -> geometryCollectionWkt(r);
        };
        try {
            Geometry geom = new PGgeometry(wkt).getGeometry();
            if (srid != 0) {
                geom.setSrid(srid);
            }
            return geom;
        } catch (java.sql.SQLException e) {
            throw new IllegalStateException("Failed to parse WKT to Geometry", e);
        }
    }

    private double rx(java.util.Random r, double a, double b) { return a + (b - a) * r.nextDouble(); }

    private String pointWkt(java.util.Random r) {
        double x = rx(r, minX, maxX); double y = rx(r, minY, maxY);
        return String.format("POINT(%f %f)", x, y);
    }

    private String lineStringWkt(java.util.Random r) {
        int n = 2 + r.nextInt(4);
        StringBuilder sb = new StringBuilder("LINESTRING(");
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(", ");
            sb.append(rx(r, minX, maxX)).append(' ').append(rx(r, minY, maxY));
        }
        sb.append(')');
        return sb.toString();
    }

    private String polygonWkt(java.util.Random r) {
        int n = 3 + r.nextInt(5);
        StringBuilder sb = new StringBuilder("POLYGON((");
        double fx = 0, fy = 0;
        for (int i = 0; i < n; i++) {
            double x = rx(r, minX, maxX), y = rx(r, minY, maxY);
            if (i == 0) { fx = x; fy = y; }
            if (i > 0) sb.append(", ");
            sb.append(x).append(' ').append(y);
        }
        sb.append(", ").append(fx).append(' ').append(fy).append("))");
        return sb.toString();
    }

    private String multiPointWkt(java.util.Random r) {
        int n = 1 + r.nextInt(5);
        StringBuilder sb = new StringBuilder("MULTIPOINT(");
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(", ");
            sb.append('(').append(rx(r, minX, maxX)).append(' ').append(rx(r, minY, maxY)).append(')');
        }
        sb.append(')');
        return sb.toString();
    }

    private String multiLineStringWkt(java.util.Random r) {
        int m = 1 + r.nextInt(3);
        StringBuilder sb = new StringBuilder("MULTILINESTRING(");
        for (int j = 0; j < m; j++) {
            if (j > 0) sb.append(", ");
            int n = 2 + r.nextInt(4);
            sb.append('(');
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(", ");
                sb.append(rx(r, minX, maxX)).append(' ').append(rx(r, minY, maxY));
            }
            sb.append(')');
        }
        sb.append(')');
        return sb.toString();
    }

    private String multiPolygonWkt(java.util.Random r) {
        int m = 1 + r.nextInt(3);
        StringBuilder sb = new StringBuilder("MULTIPOLYGON(");
        for (int k = 0; k < m; k++) {
            if (k > 0) sb.append(", ");
            int n = 3 + r.nextInt(5);
            sb.append("((");
            double fx = 0, fy = 0;
            for (int i = 0; i < n; i++) {
                double x = rx(r, minX, maxX), y = rx(r, minY, maxY);
                if (i == 0) { fx = x; fy = y; }
                if (i > 0) sb.append(", ");
                sb.append(x).append(' ').append(y);
            }
            sb.append(", ").append(fx).append(' ').append(fy).append(")")
              .append(")");
        }
        sb.append(')');
        return sb.toString();
    }

    private String geometryCollectionWkt(java.util.Random r) {
        String p = pointWkt(r);
        String l = lineStringWkt(r);
        return "GEOMETRYCOLLECTION(" + p + ", " + l + ")";
    }
}
