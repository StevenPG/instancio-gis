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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.CoordinateArraySequenceGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.PackedCoordinateSequenceGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateSequenceGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateSequenceSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.instancio.generator.GeneratorContext;
import org.instancio.internal.generator.AbstractGenerator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.CoordinateSequenceFactory;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

/**
 * This sequence generator returns a random coordinate sequence implemented by either
 * coordinate array sequence or packed sequence.
 */
public class CoordinateSequenceGenerator
        implements CoordinateSequenceSpec, CoordinateSequenceGeneratorSpec, Generator<CoordinateSequence> {

    // TODO - potentially ask why I need two of these, why was one inconsistent?
    private final CoordinateArraySequenceGenerator coordinateArraySequenceGenerator
            = new CoordinateArraySequenceGenerator();
    private final PackedCoordinateSequenceGenerator packedCoordinateSequenceGenerator
            = new PackedCoordinateSequenceGenerator();

    private CoordinateSequence coordinateSequence;

    // Default 1..10 to preserve current behavior
    private int minLength = 1;
    private int maxLength = 10;
    // When set, overrides min/max
    private Integer fixedLength;

    @Override
    public CoordinateSequenceGenerator coordinateSequence(CoordinateSequence coordinateSequence) {
        this.coordinateSequence = coordinateSequence;
        return this;
    }

    /**
     * Sets a fixed length for the generated {@link CoordinateArraySequence}.
     * @param length the number of coordinates must be >= 1
     * @return this generator
     */
    @Override
    public CoordinateSequenceGenerator length(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length must be >= 1");
        }
        if (coordinateSequence != null && coordinateSequence.toCoordinateArray().length != 0) {
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
    public CoordinateSequenceGenerator length(int min, int max) {
        if (min < 1) {
            throw new IllegalArgumentException("min must be >= 1");
        }
        if (max < min) {
            throw new IllegalArgumentException("max must be >= min");
        }
        if (coordinateSequence != null && coordinateSequence.toCoordinateArray().length != 0) {
            throw new IllegalArgumentException("can't specify length and coordinate sequence");
        }
        this.fixedLength = null;
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    @Override
    public CoordinateSequence generate(Random random) {
        var randomInteger = random.intRange(1, 10);
        if(coordinateSequence != null) {
            return coordinateSequence;
        } else {
            if(randomInteger % 2 == 0) {
                if(fixedLength != null) {
                    return coordinateArraySequenceGenerator.length(fixedLength).generate(random);
                } else {
                    return coordinateArraySequenceGenerator.length(minLength, maxLength).generate(random);
                }
            } else {
                if(fixedLength != null) {
                    return packedCoordinateSequenceGenerator.length(fixedLength).generate(random);
                } else {
                    return packedCoordinateSequenceGenerator.length(minLength, maxLength).generate(random);
                }
            }
        }
    }
}
