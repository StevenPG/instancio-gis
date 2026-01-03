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
import org.postgresql.geometric.PGcircle;
import org.postgresql.geometric.PGpoint;

/** Generator for {@link PGcircle}. */
public class PGCircleGenerator implements Generator<PGcircle>, NumericRangeSpec<PGCircleGenerator> {

    private final PGPointGenerator pointGen = new PGPointGenerator();
    private double minRadius = 0.1d;
    private double maxRadius = 100d;

    /**
     * Default constructor.
     */
    public PGCircleGenerator() {
        // No custom instantiations needed
    }

    /**
     * set raidusRange for generator
     * @param min min radius range
     * @param max max radius range
     * @return generator
     */
    public PGCircleGenerator radiusRange(double min, double max) {
        this.minRadius = Math.max(0.0d, min);
        this.maxRadius = Math.max(this.minRadius, max);
        return this;
    }

    @Override
    public PGCircleGenerator xRange(double minX, double maxX) {
        pointGen.xRange(minX, maxX);
        return this;
    }

    @Override
    public PGCircleGenerator yRange(double minY, double maxY) {
        pointGen.yRange(minY, maxY);
        return this;
    }

    @Override
    public PGcircle generate(Random random) {
        final double radius = random.doubleRange(minRadius, maxRadius);
        PGpoint center = pointGen.generate(random);
        return new PGcircle(center, radius);
    }
}
