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
    private double minX = -180d;
    private double maxX = 180d;
    private double minY = -90d;
    private double maxY = 90d;
    private int minElements = 1;
    private int maxElements = 3;

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
        int m = random.intRange(minElements, maxElements);
        StringBuilder sb = new StringBuilder("MULTILINESTRING(");
        for (int j = 0; j < m; j++) {
            if (j > 0) sb.append(", ");
            int n = random.intRange(2, 5);
            sb.append('(');
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(", ");
                double x = random.doubleRange(minX, maxX);
                double y = random.doubleRange(minY, maxY);
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
