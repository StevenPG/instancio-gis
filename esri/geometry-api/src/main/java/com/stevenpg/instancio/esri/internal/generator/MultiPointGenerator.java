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

package com.stevenpg.instancio.esri.internal.generator;

import com.esri.core.geometry.MultiPoint;
import com.stevenpg.instancio.esri.internal.generator.specs.NumericRangeSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating ESRI {@link MultiPoint} instances containing
 * a configurable number of random points within coordinate ranges.
 *
 * <p>Default coordinate ranges correspond to valid WGS84 longitude/latitude bounds.
 * The number of points generated is randomly chosen between {@code minLength}
 * and {@code maxLength} (defaults: 2 to 5).</p>
 *
 * @since 1.0.0
 */
public class MultiPointGenerator implements Generator<MultiPoint>, NumericRangeSpec<MultiPointGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private int minLength = 2;
    private int maxLength = 5;

    /**
     * Default constructor.
     */
    public MultiPointGenerator() {
        // No custom instantiations needed
    }

    /** {@inheritDoc} */
    @Override
    public MultiPointGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public MultiPointGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the number of points to generate.
     *
     * @param min the minimum number of points (inclusive)
     * @param max the maximum number of points (inclusive)
     * @return this generator instance for fluent chaining
     */
    public MultiPointGenerator length(int min, int max) {
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    /**
     * Generates a random ESRI MultiPoint with a random number of points,
     * each having coordinates within the configured ranges.
     *
     * @param random the Instancio random instance used for value generation
     * @return a new {@link MultiPoint} containing randomly generated points
     */
    @Override
    public MultiPoint generate(Random random) {
        MultiPoint mp = new MultiPoint();
        int count = random.intRange(minLength, maxLength);
        for (int i = 0; i < count; i++) {
            mp.add(random.doubleRange(minX, maxX), random.doubleRange(minY, maxY));
        }
        return mp;
    }
}
