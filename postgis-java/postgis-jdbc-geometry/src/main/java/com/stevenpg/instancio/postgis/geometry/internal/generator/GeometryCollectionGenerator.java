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
import net.postgis.jdbc.geometry.GeometryCollection;
import org.instancio.Random;
import org.instancio.generator.AfterGenerate;
import org.instancio.generator.Generator;
import org.instancio.generator.Hints;

/** Generator for net.postgis.jdbc.geometry.GeometryCollection using WKT. */
public class GeometryCollectionGenerator implements Generator<GeometryCollection>, NumericRangeSpec<GeometryCollectionGenerator> {
    private double minX = -180d, maxX = 180d;
    private double minY = -90d, maxY = 90d;

    @Override
    public Hints hints() {
        return Hints.builder()
                .afterGenerate(AfterGenerate.DO_NOT_MODIFY)
                .build();
    }

    @Override
    public GeometryCollectionGenerator xRange(double minX, double maxX) { this.minX = minX; this.maxX = maxX; return this; }

    @Override
    public GeometryCollectionGenerator yRange(double minY, double maxY) { this.minY = minY; this.maxY = maxY; return this; }

    @Override
    public GeometryCollection generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        
        // Simpler implementation just combining a Point and a LineString
        double x1 = minX + (maxX - minX) * r.nextDouble();
        double y1 = minY + (maxY - minY) * r.nextDouble();
        double x2 = minX + (maxX - minX) * r.nextDouble();
        double y2 = minY + (maxY - minY) * r.nextDouble();
        double x3 = minX + (maxX - minX) * r.nextDouble();
        double y3 = minY + (maxY - minY) * r.nextDouble();
        
        String wkt = String.format("GEOMETRYCOLLECTION(POINT(%f %f), LINESTRING(%f %f, %f %f))", x1, y1, x2, y2, x3, y3);
        
        try {
            return (GeometryCollection) new PGgeometry(wkt).getGeometry();
        } catch (java.sql.SQLException e) {
            throw new IllegalStateException("Failed to parse WKT to GeometryCollection", e);
        }
    }
}
