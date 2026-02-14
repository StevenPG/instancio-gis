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

import com.esri.core.geometry.Polyline;
import com.stevenpg.instancio.esri.internal.generator.specs.NumericRangeSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating ESRI {@link Polyline} instances with a random path
 * of configurable length within coordinate ranges.
 *
 * <p>The polyline is built by calling {@code startPath} for the first point
 * and {@code lineTo} for subsequent points. The number of points is randomly
 * chosen between {@code minLength} and {@code maxLength} (defaults: 2 to 5).</p>
 *
 * @since 1.0.0
 */
public class PolylineGenerator implements Generator<Polyline>, NumericRangeSpec<PolylineGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private int minLength = 2;
    private int maxLength = 5;

    /**
     * Default constructor.
     */
    public PolylineGenerator() {
        // No custom instantiations needed
    }

    /** {@inheritDoc} */
    @Override
    public PolylineGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public PolylineGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the number of points in the polyline path.
     *
     * @param min the minimum number of points (inclusive)
     * @param max the maximum number of points (inclusive)
     * @return this generator instance for fluent chaining
     */
    public PolylineGenerator length(int min, int max) {
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    /**
     * Generates a random ESRI Polyline with a single path containing
     * a random number of points within the configured coordinate ranges.
     *
     * @param random the Instancio random instance used for value generation
     * @return a new {@link Polyline} with a randomly generated path
     */
    @Override
    public Polyline generate(Random random) {
        Polyline pl = new Polyline();
        int count = random.intRange(minLength, maxLength);

        // Start the path with the first point
        pl.startPath(
                random.doubleRange(minX, maxX),
                random.doubleRange(minY, maxY)
        );

        // Add remaining points to the path
        for (int i = 1; i < count; i++) {
            pl.lineTo(
                    random.doubleRange(minX, maxX),
                    random.doubleRange(minY, maxY)
            );
        }

        return pl;
    }
}
