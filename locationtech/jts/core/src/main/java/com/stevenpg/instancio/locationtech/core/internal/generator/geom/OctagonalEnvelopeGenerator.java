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

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.OctagonalEnvelopeGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.OctagonalEnvelopeSpec;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.OctagonalEnvelope;

/**
 * Generator for creating an OctagonalEnvelope.
 * @since 1.0.0
 */
public class OctagonalEnvelopeGenerator implements OctagonalEnvelopeSpec, OctagonalEnvelopeGeneratorSpec, Generator<OctagonalEnvelope> {

    private Coordinate inputCoordinate0;
    private Coordinate inputCoordinate1;
    private Envelope inputEnvelope;
    private OctagonalEnvelope inputOctagonalEnvelope;
    private Geometry inputGeometry;

    /**
     * Default constructor.
     */
    public OctagonalEnvelopeGenerator() {
        // No custom instantiations needed
    }

    /**
     * Configure the generator to generate an octagonal envelope with the specified coordinate.
     * @param coordinate coordinate to use
     * @return spec builder
     */
    @Override
    public OctagonalEnvelopeGenerator coordinate(Coordinate coordinate) {
        this.inputCoordinate0 = coordinate;
        return this;
    }

    /**
     * Configure the generator to generate an octagonal envelope with the specified coordinates.
     * @param coordinate0 first coordinate
     * @param coordinate1 second coordinate
     * @return spec builder
     */
    @Override
    public OctagonalEnvelopeGenerator coordinate(Coordinate coordinate0, Coordinate coordinate1) {
        this.inputCoordinate0 = coordinate0;
        this.inputCoordinate1 = coordinate1;
        return this;
    }

    /**
     * Configure the generator to generate an octagonal envelope with the specified envelope.
     * @param envelope envelope to use
     * @return spec builder
     */
    @Override
    public OctagonalEnvelopeGenerator envelope(Envelope envelope) {
        this.inputEnvelope = envelope;
        return this;
    }

    /**
     * Configure the generator to generate an octagonal envelope with the specified octagonal envelope.
     * @param octagonalEnvelope octagonal envelope to use
     * @return spec builder
     */
    @Override
    public OctagonalEnvelopeGenerator envelope(OctagonalEnvelope octagonalEnvelope) {
        this.inputOctagonalEnvelope = octagonalEnvelope;
        return this;
    }

    /**
     * Configure the generator to generate an octagonal envelope with the specified geometry.
     * @param geometry geometry to use
     * @return spec builder
     */
    @Override
    public OctagonalEnvelopeGenerator geometry(Geometry geometry) {
        this.inputGeometry = geometry;
        return this;
    }

    @Override
    public OctagonalEnvelope generate(Random random) {
        // If a specific octagonal envelope is provided, return a copy
        if (inputOctagonalEnvelope != null) {
            return new OctagonalEnvelope(inputOctagonalEnvelope);
        }

        // If a geometry is provided, create octagonal envelope from it
        if (inputGeometry != null) {
            return new OctagonalEnvelope(inputGeometry);
        }

        // If an envelope is provided, create octagonal envelope from it
        if (inputEnvelope != null) {
            return new OctagonalEnvelope(inputEnvelope);
        }

        // If two coordinates are provided, create octagonal envelope from them
        if (inputCoordinate0 != null && inputCoordinate1 != null) {
            return new OctagonalEnvelope(inputCoordinate0, inputCoordinate1);
        }

        // If one coordinate is provided, create octagonal envelope from it
        if (inputCoordinate0 != null) {
            return new OctagonalEnvelope(inputCoordinate0);
        }

        // Default: generate random octagonal envelope
        // Generate two random coordinates using Instancio spatial generators
        double x1 = Instancio.gen().spatial().coordinate().lon().get();
        double y1 = Instancio.gen().spatial().coordinate().lat().get();
        double x2 = Instancio.gen().spatial().coordinate().lon().get();
        double y2 = Instancio.gen().spatial().coordinate().lat().get();

        // Ensure proper ordering so min < max for all dimensions
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);

        // If coordinates are the same, add a small offset to ensure min < max
        if (minX == maxX) {
            maxX = minX + 0.01;
        }
        if (minY == maxY) {
            maxY = minY + 0.01;
        }

        Coordinate coord1 = new Coordinate(minX, minY);
        Coordinate coord2 = new Coordinate(maxX, maxY);

        return new OctagonalEnvelope(coord1, coord2);
    }
}
