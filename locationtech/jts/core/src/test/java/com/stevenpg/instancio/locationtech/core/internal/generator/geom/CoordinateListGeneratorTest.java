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

import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateListGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var list = Instancio.create(CoordinateList.class);
        assertNotNull(list);
        assertTrue(list.size() >= 0);
    }

    @RepeatedTest(5)
    void coordinateList() {
        var c1 = new CoordinateGenerator().generate(new DefaultRandom());
        var c2 = new CoordinateGenerator().generate(new DefaultRandom());
        var c3 = new CoordinateGenerator().generate(new DefaultRandom());

        var list = new CoordinateListGenerator()
                .coordinateList(List.of(c1, c2, c3))
                .generate(new DefaultRandom());

        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(c1, list.get(0));
        assertEquals(c2, list.get(1));
        assertEquals(c3, list.get(2));
    }

    @RepeatedTest(5)
    void length() {
        var list = new CoordinateListGenerator().length(7).generate(new DefaultRandom());
        assertEquals(7, list.size());
    }

    @Test
    void lengthLessThanOne() {
        var result = assertThrows(IllegalArgumentException.class, () ->
                new CoordinateListGenerator().length(0).generate(new DefaultRandom()));

        assertNotNull(result);
        assertInstanceOf(IllegalArgumentException.class, result);
        assertEquals("length must be >= 1", result.getMessage());
    }

    @Test
    void lengthSpecifiedWithProvidedList() {
        var c1 = new Coordinate(1, 1);
        var result = assertThrows(IllegalArgumentException.class, () ->
                new CoordinateListGenerator()
                        .coordinateList(List.of(c1))
                        .length(5)
                        .generate(new DefaultRandom()));

        assertNotNull(result);
        assertInstanceOf(IllegalArgumentException.class, result);
        assertEquals("can't specify length and coordinate list", result.getMessage());
    }

    @RepeatedTest(5)
    void lengthRange() {
        var list = new CoordinateListGenerator().length(3, 6).generate(new DefaultRandom());
        assertTrue(list.size() >= 3 && list.size() <= 6);
    }

    @RepeatedTest(5)
    void generateDefault() {
        var list = new CoordinateListGenerator().generate(new DefaultRandom());
        assertTrue(list.size() >= 1 && list.size() <= 10);
    }
}
