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
import net.postgis.jdbc.geometry.Point;
import org.instancio.Random;
import org.instancio.generator.AfterGenerate;
import org.instancio.generator.Generator;
import org.instancio.generator.Hints;

/** Generator for org.postgis.Point using WKT. */
public class PointGenerator implements Generator<Point>, NumericRangeSpec<PointGenerator> {
    private double minX = -180d;
    private double maxX = 180d;
    private double minY = -90d;
    private double maxY = 90d;
    private double minZ = 0d;
    private double maxZ = 0d;
    private boolean useZ = false;
    private int srid = 0;

    @Override
    public Hints hints() {
        return Hints.builder()
                .afterGenerate(AfterGenerate.DO_NOT_MODIFY)
                .build();
    }

    @Override
    public PointGenerator xRange(double minX, double maxX) { this.minX = minX; this.maxX = maxX; return this; }

    @Override
    public PointGenerator yRange(double minY, double maxY) { this.minY = minY; this.maxY = maxY; return this; }

    @Override
    public PointGenerator zRange(double minZ, double maxZ) {
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.useZ = true;
        return this;
    }

    @Override
    public PointGenerator srid(int srid) {
        this.srid = srid;
        return this;
    }

    @Override
    public Point generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        double x = minX + (maxX - minX) * r.nextDouble();
        double y = minY + (maxY - minY) * r.nextDouble();

        String wkt;
        if (useZ) {
            double z = minZ + (maxZ - minZ) * r.nextDouble();
            wkt = String.format("POINT(%f %f %f)", x, y, z);
        } else {
            wkt = String.format("POINT(%f %f)", x, y);
        }
        try {
            Point p = (Point) new PGgeometry(wkt).getGeometry();
            if (srid != 0) {
                p.setSrid(srid);
            }
            return p;
        } catch (java.sql.SQLException e) {
            throw new IllegalStateException("Failed to parse WKT to Point", e);
        }
    }
}
