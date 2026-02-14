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

package com.stevenpg.instancio.geolatte.internal.generator;

import com.stevenpg.instancio.geolatte.internal.generator.specs.NumericRangeSpec;
import org.geolatte.geom.G2D;
import org.geolatte.geom.MultiPoint;
import org.geolatte.geom.Point;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating a Geolatte {@link MultiPoint} with WGS84 coordinates.
 * Produces a {@code MultiPoint<G2D>} containing a configurable number of points
 * within configurable longitude/latitude bounds.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
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

    @Override
    public MultiPointGenerator xRange(final double minX, final double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public MultiPointGenerator yRange(final double minY, final double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the range of points in the generated multi-point.
     *
     * @param min minimum number of points
     * @param max maximum number of points
     * @return this generator for fluent chaining
     */
    public MultiPointGenerator length(final int min, final int max) {
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    /**
     * Generates a random {@link MultiPoint} with WGS84 CRS.
     * Each constituent point is generated using {@link PointGenerator}.
     *
     * @param random the Instancio random provider
     * @return a randomly generated MultiPoint
     */
    @Override
    @SuppressWarnings("unchecked")
    public MultiPoint generate(final Random random) {
        int len = random.intRange(minLength, maxLength);
        PointGenerator pointGen = new PointGenerator().xRange(minX, maxX).yRange(minY, maxY);
        Point<G2D>[] points = new Point[len];
        for (int i = 0; i < len; i++) {
            points[i] = pointGen.generate(random);
        }
        return new MultiPoint<>(points);
    }
}
