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

package com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.CoordinateGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.EnvelopableGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.impl.PackedCoordinateSequenceGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.impl.PackedCoordinateSequenceSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.locationtech.jts.geom.impl.PackedCoordinateSequence;
import org.locationtech.jts.geom.impl.PackedCoordinateSequenceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for creating a CoordinateArraySequence, with 1,10 coordinates randomly
 * generated in the array sequence.

 * @since 1.0.0
 */
public class PackedCoordinateSequenceGenerator
        implements PackedCoordinateSequenceSpec, PackedCoordinateSequenceGeneratorSpec,
        EnvelopableGenerator<PackedCoordinateSequence> {

    private final CoordinateGenerator coordinateGenerator =
            new CoordinateGenerator();

    private final List<Coordinate> overriddenCoordinateSequence = new ArrayList<>();

    // Default 1..10 to preserve current behavior
    private int minLength = 1;
    private int maxLength = 10;
    // When set, overrides min/max
    private Integer fixedLength;
    private Envelope inputEnvelope;

    @Override
    public PackedCoordinateSequenceGenerator coordinateSequence(List<Coordinate> coordinateSequence) {
        overriddenCoordinateSequence.addAll(coordinateSequence);
        return this;
    }

    /**
     * Sets a fixed length for the generated {@link CoordinateArraySequence}.
     * @param length number of coordinates, must be >= 1
     * @return this generator
     */
    @Override
    public PackedCoordinateSequenceGenerator length(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length must be >= 1");
        }
        if (!overriddenCoordinateSequence.isEmpty()) {
            throw new IllegalArgumentException("can't specify length and coordinate sequence");
        }
        this.fixedLength = length;
        this.minLength = length;
        this.maxLength = length;
        return this;
    }

    /**
     * Sets a range for the number of coordinates generated.
     * @param min minimum number of coordinates, must be >= 1
     * @param max maximum number of coordinates, must be >= min
     * @return this generator
     */
    @Override
    public PackedCoordinateSequenceGenerator length(int min, int max) {
        if (min < 1) {
            throw new IllegalArgumentException("min must be >= 1");
        }
        if (max < min) {
            throw new IllegalArgumentException("max must be >= min");
        }
        if (!overriddenCoordinateSequence.isEmpty()) {
            throw new IllegalArgumentException("can't specify length and coordinate sequence");
        }
        this.fixedLength = null;
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    @Override
    public Generator<PackedCoordinateSequence> within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public PackedCoordinateSequence generate(Random random) {
        // TODO - handle within
        if(overriddenCoordinateSequence.isEmpty()) {
            int totalCoordinates = (fixedLength != null)
                    ? fixedLength
                    : random.intRange(minLength, maxLength);
            var coordinates = new ArrayList<Coordinate>();
            for (int i = 0; i < totalCoordinates; i++) {
                coordinates.add(coordinateGenerator.generate(random));
            }

            var factory = new PackedCoordinateSequenceFactory();
            return (PackedCoordinateSequence) factory.create(coordinates.toArray(Coordinate[]::new));
        } else {
            return (PackedCoordinateSequence) new PackedCoordinateSequenceFactory(PackedCoordinateSequenceFactory.DOUBLE).create(
                    overriddenCoordinateSequence.toArray(Coordinate[]::new)
            );
        }
    }
}
