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
import org.instancio.generator.Generator;
import org.postgresql.geometric.PGpath;
import org.postgresql.geometric.PGpoint;

/** Generator for {@link PGpath}. */
public class PGPathGenerator implements Generator<PGpath>, NumericRangeSpec<PGPathGenerator> {

    private final PGPointGenerator pointGen = new PGPointGenerator();
    private int minVertices = 2;
    private int maxVertices = 8;
    private boolean open = true;

    /**
     * Default constructor.
     */
    public PGPathGenerator() {}

    /**
     * sets whether the path is open
     * @param open open or closed
     * @return spec builder
     */
    public PGPathGenerator open(boolean open) {
        this.open = open;
        return this;
    }

    /**
     * sets the number of vertices in the path
     * @param min minimum
     * @param max maximum
     * @return spec builder
     */
    public PGPathGenerator vertices(int min, int max) {
        this.minVertices = Math.max(2, min);
        this.maxVertices = Math.max(this.minVertices, max);
        return this;
    }

    @Override
    public PGPathGenerator xRange(double minX, double maxX) {
        pointGen.xRange(minX, maxX);
        return this;
    }

    @Override
    public PGPathGenerator yRange(double minY, double maxY) {
        pointGen.yRange(minY, maxY);
        return this;
    }

    @Override
    public PGpath generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        int n = minVertices + r.nextInt((maxVertices - minVertices) + 1);
        PGpoint[] pts = new PGpoint[n];
        for (int i = 0; i < n; i++) {
            pts[i] = pointGen.generate(random);
        }
        return new PGpath(pts, open);
    }
}
