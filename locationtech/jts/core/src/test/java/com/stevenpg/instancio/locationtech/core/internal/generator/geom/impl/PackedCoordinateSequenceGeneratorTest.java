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
import com.stevenpg.instancio.locationtech.core.internal.generator.geom.CoordinateSequenceGenerator;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.impl.PackedCoordinateSequence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PackedCoordinateSequenceGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var packedCoordinateSequence = Instancio.create(PackedCoordinateSequence.class);
        assertNotNull(packedCoordinateSequence);
        assertTrue(packedCoordinateSequence.size() >= 0);
    }

    @RepeatedTest(5)
    void coordinateSequence() {
        var coordinate1 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate2 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate3 = new CoordinateGenerator().generate(new DefaultRandom());

        var packedCoordinateSequence = new PackedCoordinateSequenceGenerator()
                .coordinateSequence(List.of(
                        coordinate1, coordinate2, coordinate3
                )).generate(new DefaultRandom());

        assertEquals(3, packedCoordinateSequence.size());
        assertEquals(coordinate1, packedCoordinateSequence.getCoordinate(0));
        assertEquals(coordinate2, packedCoordinateSequence.getCoordinate(1));
        assertEquals(coordinate3, packedCoordinateSequence.getCoordinate(2));
    }

    @RepeatedTest(5)
    void length() {
        var coordinateSequence = new PackedCoordinateSequenceGenerator().length(10).generate(new DefaultRandom());
        assertEquals(10, coordinateSequence.size());
    }

    @Test
    void lengthLessThanOne() {
        var result = assertThrows(IllegalArgumentException.class, () ->
                new PackedCoordinateSequenceGenerator().length(-5).generate(new DefaultRandom()));

        assertNotNull(result);
        assertInstanceOf(IllegalArgumentException.class, result);
        assertEquals("length must be >= 1", result.getMessage());
    }

    @Test
    void lengthSpecifiedWithProvidedSequence() {
        var coordinate1 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate2 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate3 = new CoordinateGenerator().generate(new DefaultRandom());

        var coordinateArraySequence = new PackedCoordinateSequenceGenerator()
                .coordinateSequence(List.of(
                        coordinate1, coordinate2, coordinate3
                )).generate(new DefaultRandom());

        var result = assertThrows(IllegalArgumentException.class, () -> {
                    new CoordinateSequenceGenerator()
                            .coordinateSequence(coordinateArraySequence)
                            .length(1, 10)
                            .generate(new DefaultRandom());
                }
        );

        assertNotNull(result);
        assertInstanceOf(IllegalArgumentException.class, result);
        assertEquals("can't specify length and coordinate sequence", result.getMessage());
    }

    @RepeatedTest(5)
    void lengthRange() {
        var coordinateSequence = new PackedCoordinateSequenceGenerator().length(10, 20).generate(new DefaultRandom());
        assertTrue(coordinateSequence.size() >= 10 && coordinateSequence.size() <= 20);
    }

    @RepeatedTest(5)
    void generate() {
        var coordinateSequence = new PackedCoordinateSequenceGenerator().generate(new DefaultRandom());
        // Default values are min:1 max:10
        assertTrue(coordinateSequence.size() >= 1 && coordinateSequence.size() <= 10);
    }
}