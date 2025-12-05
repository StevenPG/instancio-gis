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
import org.junit.jupiter.api.RepeatedTest;
import org.locationtech.jts.geom.CoordinateXYM;
import org.locationtech.jts.geom.CoordinateXYZM;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateXYZMGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var coordinate = Instancio.create(CoordinateXYZM.class);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertTrue(coordinate.getZ() >= 0);
        assertTrue(coordinate.getM() >= 0);
    }

    @RepeatedTest(5)
    void latitude() {
        var coordinate = new CoordinateXYZMGenerator().latitude(10d).generate(null);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertTrue(coordinate.getZ() >= 0);
        assertTrue(coordinate.getM() >= 0);
        assertEquals(10d, coordinate.y);
    }

    @RepeatedTest(5)
    void longitude() {
        var coordinate = new CoordinateXYZMGenerator().longitude(20d).generate(null);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertEquals(20d, coordinate.x);
        assertTrue(coordinate.getM() >= 0);
        assertTrue(coordinate.getZ() >= 0);
    }

    @RepeatedTest(5)
    void altitude() {
        var coordinate = new CoordinateXYZMGenerator().altitude(40d).generate(null);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertEquals(40d, coordinate.z);
        assertTrue(coordinate.getM() >= 0);
    }

    @RepeatedTest(5)
    void measure() {
        var coordinate = new CoordinateXYZMGenerator().measure(5d).generate(null);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertTrue(coordinate.getZ() >= 0);
        assertEquals(5d, coordinate.getM());
    }

    @RepeatedTest(5)
    void generate() {
        var coordinate = new CoordinateXYZMGenerator().generate(null);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertTrue(coordinate.getZ() >= 0);
        assertTrue(coordinate.getM() >= 0);

    }
}