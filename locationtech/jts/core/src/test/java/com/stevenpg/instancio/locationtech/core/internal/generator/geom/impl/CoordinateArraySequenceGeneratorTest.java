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
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateArraySequenceGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var coordinateArraySequence = Instancio.create(CoordinateArraySequence.class);
        assertNotNull(coordinateArraySequence);
        assertTrue(coordinateArraySequence.size() >= 0);
    }

    @RepeatedTest(5)
    void coordinateArraySequence() {
        var coordinate1 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate2 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate3 = new CoordinateGenerator().generate(new DefaultRandom());

        var coordinateArraySequence = new CoordinateArraySequenceGenerator()
                .coordinateArraySequence(List.of(
                        coordinate1, coordinate2, coordinate3
                )).generate(new DefaultRandom());

        assertNotNull(coordinateArraySequence);
        assertEquals(3, coordinateArraySequence.size());
        assertEquals(coordinate1, coordinateArraySequence.getCoordinate(0));
        assertEquals(coordinate2, coordinateArraySequence.getCoordinate(1));
        assertEquals(coordinate3, coordinateArraySequence.getCoordinate(2));
    }

    @RepeatedTest(5)
    void length() {
        var coordinateArraySequence = new CoordinateArraySequenceGenerator().length(10).generate(null);
        assertEquals(10, coordinateArraySequence.size());
    }

    @RepeatedTest(5)
    void lengthRange() {
        var coordinateArraySequence = new CoordinateArraySequenceGenerator().length(10, 20).generate(new DefaultRandom());
        assertTrue(coordinateArraySequence.size() >= 10 && coordinateArraySequence.size() <= 20);
    }

    @RepeatedTest(5)
    void generate() {
        var coordinateArraySequence = new CoordinateArraySequenceGenerator().generate(new DefaultRandom());
        // Default values are min:1 max:10
        assertTrue(coordinateArraySequence.size() >= 1 && coordinateArraySequence.size() <= 10);
    }

    @Test
    void invalidLength() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new CoordinateArraySequenceGenerator()
                .length(-1)
                .generate(new DefaultRandom())
        );

        assertEquals("length must be >= 1", exception.getMessage());
    }

    @Test
    void invalidLengthRangeUnderMin() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new CoordinateArraySequenceGenerator()
                .length(-1, 10)
                .generate(new DefaultRandom())
        );

        assertEquals("min must be >= 1", exception.getMessage());
    }

    @Test
    void invalidLengthRangeUnderMinMaxMismatch() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new CoordinateArraySequenceGenerator()
                .length(10, -1)
                .generate(new DefaultRandom())
        );

        assertEquals("max must be >= min", exception.getMessage());
    }

    @Test
    void invalidLengthOverride() {
        var coordinate1 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate2 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate3 = new CoordinateGenerator().generate(new DefaultRandom());

        var exception = assertThrows(IllegalArgumentException.class, () -> new CoordinateArraySequenceGenerator()
                .coordinateArraySequence(List.of(
                        coordinate1, coordinate2, coordinate3
                ))
                .length(1)
                .generate(new DefaultRandom())
        );

        assertEquals("can't specify length and coordinate sequence", exception.getMessage());
    }

    @Test
    void invalidLengthRangeOverride() {
        var coordinate1 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate2 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate3 = new CoordinateGenerator().generate(new DefaultRandom());

        var exception = assertThrows(IllegalArgumentException.class, () -> new CoordinateArraySequenceGenerator()
                .coordinateArraySequence(List.of(
                        coordinate1, coordinate2, coordinate3
                ))
                .length(1, 5)
                .generate(new DefaultRandom())
        );

        assertEquals("can't specify length and coordinate sequence", exception.getMessage());
    }
}