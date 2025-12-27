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

package com.stevenpg.instancio.locationtech.core.internal.generator.geom;

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.EnvelopeGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.EnvelopeSpec;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;

/**
 * Generator for creating an Envelope.
 * @since 1.0.0
 */
public class EnvelopeGenerator implements EnvelopeSpec, EnvelopeGeneratorSpec, Generator<Envelope> {

    private Double inputX1;
    private Double inputX2;
    private Double inputY1;
    private Double inputY2;
    private Coordinate inputCoordinate0;
    private Coordinate inputCoordinate1;
    private Envelope inputEnvelope;

    /**
     * Default constructor.
     */
    public EnvelopeGenerator() {
        // No custom instantiations needed
    }

    /**
     * Configure the generator to generate an envelope with the specified bounds.
     * @param x1 minimum x coordinate
     * @param x2 maximum x coordinate
     * @param y1 minimum y coordinate
     * @param y2 maximum y coordinate
     * @return spec builder
     */
    @Override
    public EnvelopeGenerator bounds(double x1, double x2, double y1, double y2) {
        this.inputX1 = x1;
        this.inputX2 = x2;
        this.inputY1 = y1;
        this.inputY2 = y2;
        return this;
    }

    /**
     * Configure the generator to generate an envelope with the specified coordinate.
     * @param coordinate coordinate to use
     * @return spec builder
     */
    @Override
    public EnvelopeGenerator coordinate(Coordinate coordinate) {
        this.inputCoordinate0 = coordinate;
        return this;
    }

    /**
     * Configure the generator to generate an envelope with the specified coordinates.
     * @param coordinate0 first coordinate
     * @param coordinate1 second coordinate
     * @return spec builder
     */
    @Override
    public EnvelopeGenerator coordinate(Coordinate coordinate0, Coordinate coordinate1) {
        this.inputCoordinate0 = coordinate0;
        this.inputCoordinate1 = coordinate1;
        return this;
    }

    /**
     * Configure the generator to generate an envelope with the specified envelope.
     * @param envelope envelope to use
     * @return spec builder
     */
    @Override
    public EnvelopeGenerator envelope(Envelope envelope) {
        this.inputEnvelope = envelope;
        return this;
    }

    @Override
    public Envelope generate(Random random) {
        // If a specific envelope is provided, return a copy
        if (inputEnvelope != null) {
            return new Envelope(inputEnvelope);
        }

        // If bounds are provided, use them
        if (inputX1 != null && inputX2 != null && inputY1 != null && inputY2 != null) {
            return new Envelope(inputX1, inputX2, inputY1, inputY2);
        }

        // If two coordinates are provided, create envelope from them
        if (inputCoordinate0 != null && inputCoordinate1 != null) {
            return new Envelope(inputCoordinate0, inputCoordinate1);
        }

        // If one coordinate is provided, create envelope from it
        if (inputCoordinate0 != null) {
            return new Envelope(inputCoordinate0);
        }

        // Default: generate random envelope
        // Generate two random coordinates using Instancio spatial generators
        double x1 = Instancio.gen().spatial().coordinate().lon().get();
        double y1 = Instancio.gen().spatial().coordinate().lat().get();
        double x2 = Instancio.gen().spatial().coordinate().lon().get();
        double y2 = Instancio.gen().spatial().coordinate().lat().get();

        // Ensure proper min/max ordering
        return new Envelope(
            Math.min(x1, x2), Math.max(x1, x2),
            Math.min(y1, y2), Math.max(y1, y2)
        );
    }
}
