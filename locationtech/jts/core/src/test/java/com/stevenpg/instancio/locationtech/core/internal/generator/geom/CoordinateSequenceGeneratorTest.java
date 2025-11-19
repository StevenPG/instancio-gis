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
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.CoordinateSequence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateSequenceGeneratorTest {

    @RepeatedTest(5)
    void create() {
        // TODO could not create an instance of interface org.locationtech.jts.geom.CoordinateSequence
        var coordinateSequence = Instancio.create(CoordinateSequence.class);
        assertNotNull(coordinateSequence);
        assertTrue(coordinateSequence.size() >= 0);
    }

    @RepeatedTest(5)
    void coordinateSequence() {
        var coordinate1 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate2 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate3 = new CoordinateGenerator().generate(new DefaultRandom());

        var coordinateArraySequence = new CoordinateArraySequenceGenerator()
                .coordinateArraySequence(List.of(
                        coordinate1, coordinate2, coordinate3
                )).generate(new DefaultRandom());

        var coordinateSequence = new CoordinateSequenceGenerator()
                .coordinateSequence(coordinateArraySequence)
                .generate(new DefaultRandom());

        assertNotNull(coordinateSequence);

        assertEquals(3, coordinateArraySequence.size());
        assertEquals(coordinate1, coordinateArraySequence.getCoordinate(0));
        assertEquals(coordinate2, coordinateArraySequence.getCoordinate(1));
        assertEquals(coordinate3, coordinateArraySequence.getCoordinate(2));

        assertEquals(3, coordinateSequence.size());
        assertEquals(coordinate1, coordinateSequence.getCoordinate(0));
        assertEquals(coordinate2, coordinateSequence.getCoordinate(1));
        assertEquals(coordinate3, coordinateSequence.getCoordinate(2));
    }

    @RepeatedTest(5)
    void length() {
        var coordinateSequence = new CoordinateSequenceGenerator().length(10).generate(new DefaultRandom());
        assertEquals(10, coordinateSequence.size());
    }

    @Test
    void lengthLessThanOne() {
        var result = assertThrows(IllegalArgumentException.class, () ->
                new CoordinateSequenceGenerator().length(-5).generate(new DefaultRandom()));

        assertNotNull(result);
        assertInstanceOf(IllegalArgumentException.class, result);
        assertEquals("length must be >= 1", result.getMessage());
    }

    @Test
    void lengthSpecifiedWithProvidedSequence() {
        var coordinate1 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate2 = new CoordinateGenerator().generate(new DefaultRandom());
        var coordinate3 = new CoordinateGenerator().generate(new DefaultRandom());

        var coordinateArraySequence = new CoordinateArraySequenceGenerator()
                .coordinateArraySequence(List.of(
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
        var coordinateSequence = new CoordinateSequenceGenerator().length(10, 20).generate(new DefaultRandom());
        assertTrue(coordinateSequence.size() >= 10 && coordinateSequence.size() <= 20);
    }

    @RepeatedTest(5)
    void generate() {
        var coordinateSequence = new CoordinateSequenceGenerator().generate(new DefaultRandom());
        // Default values are min:1 max:10
        assertTrue(coordinateSequence.size() >= 1 && coordinateSequence.size() <= 10);
    }
}