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

import com.esri.core.geometry.Envelope;
import com.stevenpg.instancio.esri.internal.generator.specs.NumericRangeSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating ESRI {@link Envelope} instances with random bounds
 * within configurable X and Y ranges.
 *
 * <p>The generated envelope is constructed by generating two random X values
 * and two random Y values, then sorting them to ensure proper min/max ordering.</p>
 *
 * @since 1.0.0
 */
public class EnvelopeGenerator implements Generator<Envelope>, NumericRangeSpec<EnvelopeGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;

    /**
     * Default constructor.
     */
    public EnvelopeGenerator() {
        // No custom instantiations needed
    }

    /** {@inheritDoc} */
    @Override
    public EnvelopeGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public EnvelopeGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Generates a random ESRI Envelope within the configured coordinate ranges.
     *
     * <p>Two X values and two Y values are generated independently, then sorted
     * to ensure the envelope has correct min/max ordering.</p>
     *
     * @param random the Instancio random instance used for value generation
     * @return a new {@link Envelope} with properly ordered random bounds
     */
    @Override
    public Envelope generate(Random random) {
        double x1 = random.doubleRange(minX, maxX);
        double x2 = random.doubleRange(minX, maxX);
        double y1 = random.doubleRange(minY, maxY);
        double y2 = random.doubleRange(minY, maxY);

        // Sort values to ensure proper min/max ordering for the envelope
        return new Envelope(
                Math.min(x1, x2), Math.min(y1, y2),
                Math.max(x1, x2), Math.max(y1, y2)
        );
    }
}
