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
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateGeneratorTest {

    private final Random random = new DefaultRandom();

    @RepeatedTest(5)
    void create() {
        var coordinate = Instancio.create(Coordinate.class);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertTrue(Double.isNaN(coordinate.z));
    }

    @RepeatedTest(5)
    void latitude() {
        var coordinate = new CoordinateGenerator().latitude(10d).generate(random);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertEquals(10d, coordinate.y);
        assertTrue(Double.isNaN(coordinate.z));
    }

    @RepeatedTest(5)
    void longitude() {
        var coordinate = new CoordinateGenerator().longitude(20d).generate(random);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertEquals(20d, coordinate.x);
        assertTrue(Double.isNaN(coordinate.z));
    }

    @RepeatedTest(5)
    void within() {
        var bounds = new Envelope(-90, 90, -45, 45);
        var coordinate = new CoordinateGenerator().within(bounds).generate(random);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -90 && coordinate.x < 90);
        assertTrue(coordinate.y > -45 && coordinate.y < 45);
        assertTrue(Double.isNaN(coordinate.z));
    }

    @RepeatedTest(5)
    void generate() {
        var coordinate = new CoordinateGenerator().generate(random);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertTrue(Double.isNaN(coordinate.z));
    }
}