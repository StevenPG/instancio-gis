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
import org.postgresql.geometric.PGlseg;
import org.postgresql.geometric.PGpoint;

/** Generator for {@link PGlseg}. */
public class PGLsegGenerator implements Generator<PGlseg>, NumericRangeSpec<PGLsegGenerator> {

    private final PGPointGenerator pointGen = new PGPointGenerator();

    /**
     * Default constructor.
     */
    public PGLsegGenerator() {}

    @Override
    public PGLsegGenerator xRange(double minX, double maxX) {
        pointGen.xRange(minX, maxX);
        return this;
    }

    @Override
    public PGLsegGenerator yRange(double minY, double maxY) {
        pointGen.yRange(minY, maxY);
        return this;
    }

    @Override
    public PGlseg generate(Random random) {
        PGpoint a = pointGen.generate(random);
        PGpoint b = pointGen.generate(random);
        return new PGlseg(a, b);
    }
}
