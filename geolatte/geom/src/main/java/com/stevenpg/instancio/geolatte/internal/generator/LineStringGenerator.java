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
import org.geolatte.geom.LineString;
import org.geolatte.geom.PositionSequenceBuilder;
import org.geolatte.geom.PositionSequenceBuilders;
import org.instancio.Random;
import org.instancio.generator.Generator;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

/**
 * Generator for creating a Geolatte {@link LineString} with WGS84 coordinates.
 * Produces a {@code LineString<G2D>} with a configurable number of positions
 * within configurable longitude/latitude bounds.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class LineStringGenerator implements Generator<LineString>, NumericRangeSpec<LineStringGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private int minLength = 2;
    private int maxLength = 5;

    /**
     * Default constructor.
     */
    public LineStringGenerator() {
        // No custom instantiations needed
    }

    @Override
    public LineStringGenerator xRange(final double minX, final double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public LineStringGenerator yRange(final double minY, final double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the range of positions (vertices) in the generated line string.
     *
     * @param min minimum number of positions (must be at least 2)
     * @param max maximum number of positions
     * @return this generator for fluent chaining
     */
    public LineStringGenerator length(final int min, final int max) {
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    /**
     * Generates a random {@link LineString} with WGS84 CRS.
     * The number of positions is randomly chosen within the configured length range.
     *
     * @param random the Instancio random provider
     * @return a randomly generated LineString
     */
    @Override
    @SuppressWarnings("unchecked")
    public LineString generate(final Random random) {
        int len = random.intRange(minLength, maxLength);
        PositionSequenceBuilder<G2D> builder = PositionSequenceBuilders.fixedSized(len, G2D.class);
        for (int i = 0; i < len; i++) {
            builder.add(random.doubleRange(minX, maxX), random.doubleRange(minY, maxY));
        }
        return new LineString<>(builder.toPositionSequence(), WGS84);
    }
}
