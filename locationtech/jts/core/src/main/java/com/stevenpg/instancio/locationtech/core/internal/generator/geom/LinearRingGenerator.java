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

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.EnvelopableGenerator;

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.LinearRingGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.LinearRingSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;

/**
 * Generator for creating a LinearRing.
 *
 * @since 1.0.0
 */
public class LinearRingGenerator implements LinearRingSpec, LinearRingGeneratorSpec, EnvelopableGenerator<LinearRing> {

    private final static GeometryFactory defaultGeometryFactory = new GeometryFactory();
    private final static java.util.Random random = new java.util.Random();

    private GeometryFactory inputGeometryFactory;
    private CoordinateSequence inputCoordinateSequence;
    private Integer inputLength;
    private Envelope inputEnvelope;

    /**
     * Default constructor.
     */
    public LinearRingGenerator() {
    }

    @Override
    public LinearRingGenerator coordinateSequence(CoordinateSequence coordinateSequence) {
        this.inputCoordinateSequence = coordinateSequence;
        return this;
    }

    @Override
    public LinearRingGenerator length(int length) {
        this.inputLength = length;
        return this;
    }

    @Override
    public LinearRingGenerator geometryFactory(GeometryFactory geometryFactory) {
        this.inputGeometryFactory = geometryFactory;
        return this;
    }

    @Override
    public LinearRingGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public LinearRing generate(Random random) {
        var geometryFactory = inputGeometryFactory != null ? inputGeometryFactory : defaultGeometryFactory;
        if (inputCoordinateSequence != null) {
            return geometryFactory.createLinearRing(ensureClosedRing(inputCoordinateSequence, geometryFactory));
        } else {
            var sequenceGenerator = new CoordinateSequenceGenerator();
            // Default range for number of unique coordinates (not including closing coordinate)
            var uniqueCoordinateCount = random != null
                    ? random.intRange(3, 9)
                    : LinearRingGenerator.random.nextInt(3, 9);
            if (inputLength != null) {
                uniqueCoordinateCount = Math.max(3, inputLength); // LinearRing must have at least 3 unique points
            }

            CoordinateSequence sequence;
            if (inputEnvelope != null) {
                sequence = sequenceGenerator.length(uniqueCoordinateCount).within(inputEnvelope).generate(random);
            } else {
                sequence = sequenceGenerator.length(uniqueCoordinateCount).generate(random);
            }

            return geometryFactory.createLinearRing(ensureClosedRing(sequence, geometryFactory));
        }
    }

    /**
     * Ensures the coordinate sequence forms a closed ring by making the last coordinate equal to the first.
     * If the sequence is already closed, returns it as-is. Otherwise, creates a new sequence with the closing coordinate.
     */
    private CoordinateSequence ensureClosedRing(CoordinateSequence sequence, GeometryFactory geometryFactory) {
        if (sequence.size() < 4) {
            throw new IllegalArgumentException("LinearRing must have at least 4 coordinates");
        }

        // Check if already closed
        Coordinate first = sequence.getCoordinate(0);
        Coordinate last = sequence.getCoordinate(sequence.size() - 1);

        if (first.equals2D(last)) {
            return sequence; // Already closed
        }

        // Create a new sequence with the closing coordinate
        Coordinate[] coordinates = new Coordinate[sequence.size() + 1];
        for (int i = 0; i < sequence.size(); i++) {
            coordinates[i] = sequence.getCoordinate(i);
        }
        coordinates[coordinates.length - 1] = new Coordinate(first.x, first.y, first.getZ());

        return geometryFactory.getCoordinateSequenceFactory().create(coordinates);
    }
}
