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
import net.postgis.jdbc.geometry.LinearRing;
import org.instancio.Random;
import org.instancio.generator.AfterGenerate;
import org.instancio.generator.Generator;
import org.instancio.generator.Hints;

/** Generator for net.postgis.jdbc.geometry.LinearRing using WKT. */
public class LinearRingGenerator implements Generator<LinearRing>, NumericRangeSpec<LinearRingGenerator> {
    private double minX = -180d;
    private double maxX = 180d;
    private double minY = -90d;
    private double maxY = 90d;
    private int minPoints = 3;
    private int maxPoints = 5;

    @Override
    public Hints hints() {
        return Hints.builder()
                .afterGenerate(AfterGenerate.DO_NOT_MODIFY)
                .build();
    }

    @Override
    public LinearRingGenerator xRange(double minX, double maxX) { this.minX = minX; this.maxX = maxX; return this; }

    @Override
    public LinearRingGenerator yRange(double minY, double maxY) { this.minY = minY; this.maxY = maxY; return this; }

    public LinearRingGenerator pointsRange(int min, int max) { this.minPoints = min; this.maxPoints = max; return this; }

    @Override
    public LinearRing generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        int n = minPoints + r.nextInt(maxPoints - minPoints + 1);
        StringBuilder sb = new StringBuilder("LINESTRING(");
        double fx = 0;
        double fy = 0;
        for (int i = 0; i < n; i++) {
            double x = minX + (maxX - minX) * r.nextDouble();
            double y = minY + (maxY - minY) * r.nextDouble();
            if (i == 0) { fx = x; fy = y; }
            if (i > 0) sb.append(", ");
            sb.append(x).append(' ').append(y);
        }
        sb.append(", ").append(fx).append(' ').append(fy).append(')');
        try {
            // net.postgis.jdbc.geometry.LinearRing is a subclass of LineString
            // In WKT it is still represented as LINESTRING or we can try to cast it
            return new LinearRing(sb.toString());
        } catch (java.sql.SQLException e) {
            throw new IllegalStateException("Failed to parse WKT to LinearRing", e);
        }
    }
}
