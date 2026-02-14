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

import com.esri.core.geometry.Point;
import com.stevenpg.instancio.esri.internal.generator.specs.NumericRangeSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating ESRI {@link Point} instances with random coordinates
 * within configurable X and Y ranges.
 *
 * <p>Default coordinate ranges correspond to valid WGS84 longitude/latitude bounds:
 * X (longitude) from -180 to 180, Y (latitude) from -90 to 90.</p>
 *
 * @since 1.0.0
 */
public class PointGenerator implements Generator<Point>, NumericRangeSpec<PointGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;

    /**
     * Default constructor.
     */
    public PointGenerator() {
        // No custom instantiations needed
    }

    /** {@inheritDoc} */
    @Override
    public PointGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public PointGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Generates a random ESRI Point within the configured coordinate ranges.
     *
     * @param random the Instancio random instance used for value generation
     * @return a new {@link Point} with random X and Y coordinates
     */
    @Override
    public Point generate(Random random) {
        return new Point(
                random.doubleRange(minX, maxX),
                random.doubleRange(minY, maxY)
        );
    }
}
