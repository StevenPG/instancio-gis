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
import org.instancio.generator.Generator;

/** Generator for org.postgis.Point using WKT. */
public class PointGenerator implements Generator<Point>, NumericRangeSpec<PointGenerator> {
    private double minX = -180d, maxX = 180d;
    private double minY = -90d, maxY = 90d;

    @Override
    public PointGenerator xRange(double minX, double maxX) { this.minX = minX; this.maxX = maxX; return this; }

    @Override
    public PointGenerator yRange(double minY, double maxY) { this.minY = minY; this.maxY = maxY; return this; }

    @Override
    public Point generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        double x = minX + (maxX - minX) * r.nextDouble();
        double y = minY + (maxY - minY) * r.nextDouble();
        String wkt = String.format("POINT(%f %f)", x, y);
        try {
            return (Point) new PGgeometry(wkt).getGeometry();
        } catch (java.sql.SQLException e) {
            throw new IllegalStateException("Failed to parse WKT to Point", e);
        }
    }
}
