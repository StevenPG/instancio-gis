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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateListGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateListSpec;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateList;
import org.locationtech.jts.geom.Envelope;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for creating a CoordinateList.
 *
 * @since 1.0.0
 */
public class CoordinateListGenerator implements CoordinateListSpec, CoordinateListGeneratorSpec,
        Generator<CoordinateList>, EnvelopableGenerator<CoordinateList> {

    private static final java.util.Random rnd = new java.util.Random();

    private List<Coordinate> providedCoordinates;
    private Envelope inputEnvelope;

    // Default 1..10 to mirror other generators
    private int minLength = 1;
    private int maxLength = 10;
    private Integer fixedLength;

    /**
     * Default constructor.
     */
    public CoordinateListGenerator() {
    }

    @Override
    public CoordinateListGenerator coordinateList(List<Coordinate> coordinates) {
        this.providedCoordinates = coordinates == null ? List.of() : new ArrayList<>(coordinates);
        return this;
    }

    @Override
    public CoordinateListGenerator length(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length must be >= 1");
        }
        if (providedCoordinates != null && !providedCoordinates.isEmpty()) {
            throw new IllegalArgumentException("can't specify length and coordinate list");
        }
        this.fixedLength = length;
        this.minLength = length;
        this.maxLength = length;
        return this;
    }

    @Override
    public CoordinateListGenerator length(int min, int max) {
        if (min < 1) {
            throw new IllegalArgumentException("min must be >= 1");
        }
        if (max < min) {
            throw new IllegalArgumentException("max must be >= min");
        }
        if (providedCoordinates != null && !providedCoordinates.isEmpty()) {
            throw new IllegalArgumentException("can't specify length and coordinate list");
        }
        this.fixedLength = null;
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    @Override
    public Generator<CoordinateList> within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public CoordinateList generate(Random random) {
        if (providedCoordinates != null && !providedCoordinates.isEmpty()) {
            return new CoordinateList(providedCoordinates.toArray(new Coordinate[0]));
        }

        // Determine count
        int count;
        if (fixedLength != null) {
            count = fixedLength;
        } else if (random != null) {
            count = random.intRange(minLength, maxLength);
        } else {
            count = rnd.nextInt(minLength, maxLength + 1);
        }

        final List<Coordinate> coords = new ArrayList<>(count);
        final CoordinateGenerator coordinateGen = new CoordinateGenerator();
        if (inputEnvelope != null) {
            coordinateGen.within(inputEnvelope);
        }
        for (int i = 0; i < count; i++) {
            coords.add(coordinateGen.generate(random));
        }
        return new CoordinateList(coords.toArray(new Coordinate[0]));
    }
}
