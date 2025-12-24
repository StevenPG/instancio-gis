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
import net.postgis.jdbc.geometry.MultiLineString;
import org.instancio.Random;
import org.instancio.generator.AfterGenerate;
import org.instancio.generator.Generator;
import org.instancio.generator.Hints;

/** Generator for net.postgis.jdbc.geometry.MultiLineString using WKT. */
public class MultiLineStringGenerator implements Generator<MultiLineString>, NumericRangeSpec<MultiLineStringGenerator> {
    private double minX = -180d, maxX = 180d;
    private double minY = -90d, maxY = 90d;
    private int minElements = 1, maxElements = 3;

    @Override
    public Hints hints() {
        return Hints.builder()
                .afterGenerate(AfterGenerate.DO_NOT_MODIFY)
                .build();
    }

    @Override
    public MultiLineStringGenerator xRange(double minX, double maxX) { this.minX = minX; this.maxX = maxX; return this; }

    @Override
    public MultiLineStringGenerator yRange(double minY, double maxY) { this.minY = minY; this.maxY = maxY; return this; }

    public MultiLineStringGenerator elementsRange(int min, int max) { this.minElements = min; this.maxElements = max; return this; }

    @Override
    public MultiLineString generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        int m = minElements + r.nextInt(maxElements - minElements + 1);
        StringBuilder sb = new StringBuilder("MULTILINESTRING(");
        for (int j = 0; j < m; j++) {
            if (j > 0) sb.append(", ");
            int n = 2 + r.nextInt(4);
            sb.append('(');
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(", ");
                double x = minX + (maxX - minX) * r.nextDouble();
                double y = minY + (maxY - minY) * r.nextDouble();
                sb.append(x).append(' ').append(y);
            }
            sb.append(')');
        }
        sb.append(')');
        try {
            return (MultiLineString) new PGgeometry(sb.toString()).getGeometry();
        } catch (java.sql.SQLException e) {
            throw new IllegalStateException("Failed to parse WKT to MultiLineString", e);
        }
    }
}
