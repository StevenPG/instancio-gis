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
package com.stevenpg.instancio.postgis.jdbc.internal.generator.pg;

import com.stevenpg.instancio.postgis.jdbc.internal.generator.specs.NumericRangeSpec;
import org.instancio.Random;
import org.instancio.generator.AfterGenerate;
import org.instancio.generator.Generator;
import org.instancio.generator.Hints;
import org.postgresql.geometric.PGpoint;
import org.postgresql.geometric.PGpolygon;

/** Generator for {@link PGpolygon}. */
public class PGPolygonGenerator implements Generator<PGpolygon>, NumericRangeSpec<PGPolygonGenerator> {

    private final PGPointGenerator pointGen = new PGPointGenerator();

    /**
     * Default constructor.
     */
    public PGPolygonGenerator() {}

    @Override
    public Hints hints() {
        return Hints.builder()
                .afterGenerate(AfterGenerate.DO_NOT_MODIFY)
                .build();
    }

    private int minVertices = 3;
    private int maxVertices = 8;

    /**
     * Sets the total number of vertices generated.
     * @param min minimum number of vertices
     * @param max maximum number of vertices
     * @return this generator
     */
    public PGPolygonGenerator vertices(int min, int max) {
        this.minVertices = Math.max(3, min);
        this.maxVertices = Math.max(this.minVertices, max);
        return this;
    }

    @Override
    public PGPolygonGenerator xRange(double minX, double maxX) {
        pointGen.xRange(minX, maxX);
        return this;
    }

    @Override
    public PGPolygonGenerator yRange(double minY, double maxY) {
        pointGen.yRange(minY, maxY);
        return this;
    }

    @Override
    public PGpolygon generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        int n = minVertices + r.nextInt((maxVertices - minVertices) + 1);
        PGpoint[] pts = new PGpoint[n];
        for (int i = 0; i < n; i++) {
            pts[i] = pointGen.generate(random);
        }
        return new PGpolygon(pts);
    }
}
