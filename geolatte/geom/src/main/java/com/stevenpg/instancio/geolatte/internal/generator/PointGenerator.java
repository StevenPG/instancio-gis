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
import org.geolatte.geom.Point;
import org.instancio.Random;
import org.instancio.generator.Generator;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

/**
 * Generator for creating a Geolatte {@link Point} with WGS84 coordinates.
 * Produces a {@code Point<G2D>} within configurable longitude/latitude bounds.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
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

    @Override
    public PointGenerator xRange(final double minX, final double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public PointGenerator yRange(final double minY, final double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Generates a random {@link Point} with WGS84 CRS within the configured coordinate ranges.
     *
     * @param random the Instancio random provider
     * @return a randomly generated Point
     */
    @Override
    @SuppressWarnings("unchecked")
    public Point generate(final Random random) {
        double x = random.doubleRange(minX, maxX);
        double y = random.doubleRange(minY, maxY);
        return point(WGS84, g(x, y));
    }
}
