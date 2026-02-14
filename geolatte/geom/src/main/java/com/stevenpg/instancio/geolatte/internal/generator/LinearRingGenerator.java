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
import org.geolatte.geom.LinearRing;
import org.geolatte.geom.PositionSequenceBuilder;
import org.geolatte.geom.PositionSequenceBuilders;
import org.instancio.Random;
import org.instancio.generator.Generator;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

/**
 * Generator for creating a Geolatte {@link LinearRing} with WGS84 coordinates.
 * Produces a closed {@code LinearRing<G2D>} where the first and last positions
 * are identical, with a minimum of 4 positions total.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class LinearRingGenerator implements Generator<LinearRing>, NumericRangeSpec<LinearRingGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private int minLength = 4;
    private int maxLength = 8;

    /**
     * Default constructor.
     */
    public LinearRingGenerator() {
        // No custom instantiations needed
    }

    @Override
    public LinearRingGenerator xRange(final double minX, final double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public LinearRingGenerator yRange(final double minY, final double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the range of total positions in the generated linear ring.
     * The minimum is clamped to 4 since a valid linear ring requires at least 4 positions
     * (3 distinct positions plus the closing position that duplicates the first).
     *
     * @param min minimum number of positions (clamped to at least 4)
     * @param max maximum number of positions
     * @return this generator for fluent chaining
     */
    public LinearRingGenerator length(final int min, final int max) {
        // A valid linear ring needs at least 4 positions (3 unique + 1 closing)
        this.minLength = Math.max(4, min);
        this.maxLength = max;
        return this;
    }

    /**
     * Generates a random closed {@link LinearRing} with WGS84 CRS.
     * The ring is closed by appending the first position as the last position.
     *
     * @param random the Instancio random provider
     * @return a randomly generated LinearRing
     */
    @Override
    @SuppressWarnings("unchecked")
    public LinearRing generate(final Random random) {
        // innerPoints represents the number of distinct positions before closing
        int innerPoints = random.intRange(Math.max(3, minLength - 1), maxLength - 1);
        double firstX = random.doubleRange(minX, maxX);
        double firstY = random.doubleRange(minY, maxY);

        // Total positions = innerPoints + 1 (the closing position that duplicates the first)
        PositionSequenceBuilder<G2D> builder = PositionSequenceBuilders.fixedSized(innerPoints + 1, G2D.class);
        builder.add(firstX, firstY);
        for (int i = 1; i < innerPoints; i++) {
            builder.add(random.doubleRange(minX, maxX), random.doubleRange(minY, maxY));
        }
        // Close the ring by repeating the first position
        builder.add(firstX, firstY);
        return new LinearRing<>(builder.toPositionSequence(), WGS84);
    }
}
