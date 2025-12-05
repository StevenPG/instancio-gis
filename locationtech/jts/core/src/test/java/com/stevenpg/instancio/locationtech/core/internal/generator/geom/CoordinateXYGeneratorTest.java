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
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateXYGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var coordinate = Instancio.create(CoordinateXY.class);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertEquals(Double.NaN, coordinate.z);
    }

    @RepeatedTest(5)
    void latitude() {
        var coordinate = new CoordinateXYGenerator().latitude(10d).generate(null);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertEquals(10d, coordinate.y);
        assertEquals(Double.NaN, coordinate.z);
    }

    @RepeatedTest(5)
    void longitude() {
        var coordinate = new CoordinateXYGenerator().longitude(20d).generate(null);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertEquals(20d, coordinate.x);
        assertEquals(Double.NaN, coordinate.z);
    }

    @RepeatedTest(5)
    void generate() {
        var coordinate = new CoordinateXYGenerator().generate(null);
        assertNotNull(coordinate);
        assertTrue(coordinate.x > -180 && coordinate.x < 180);
        assertTrue(coordinate.y > -90 && coordinate.y < 90);
        assertEquals(Double.NaN, coordinate.z);
    }
}