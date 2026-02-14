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

package com.stevenpg.instancio.proj4j.internal.generator;

import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.proj4j.ProjCoordinate;

import static org.junit.jupiter.api.Assertions.*;

class ProjCoordinateGeneratorTest {

    private final Random random = new DefaultRandom();

    @RepeatedTest(5)
    void create() {
        var coordinate = Instancio.create(ProjCoordinate.class);
        assertNotNull(coordinate);
        assertTrue(coordinate.x >= -180 && coordinate.x <= 180,
                "x should be within [-180, 180] but was " + coordinate.x);
        assertTrue(coordinate.y >= -90 && coordinate.y <= 90,
                "y should be within [-90, 90] but was " + coordinate.y);
    }

    @Test
    void shouldGenerateWithinRanges() {
        var generator = new ProjCoordinateGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5);

        var coordinate = generator.generate(random);

        assertNotNull(coordinate);
        assertTrue(coordinate.x >= -10 && coordinate.x <= 10,
                "x should be within [-10, 10] but was " + coordinate.x);
        assertTrue(coordinate.y >= -5 && coordinate.y <= 5,
                "y should be within [-5, 5] but was " + coordinate.y);
    }

    @Test
    void shouldGenerateWithZRange() {
        var generator = new ProjCoordinateGenerator()
                .zRange(100, 500);

        var coordinate = generator.generate(random);

        assertNotNull(coordinate);
        assertTrue(coordinate.z >= 100 && coordinate.z <= 500,
                "z should be within [100, 500] but was " + coordinate.z);
    }

    @Test
    void generate() {
        var coordinate = new ProjCoordinateGenerator().generate(random);
        assertNotNull(coordinate);
        assertTrue(coordinate.x >= -180 && coordinate.x <= 180,
                "x should be within [-180, 180] but was " + coordinate.x);
        assertTrue(coordinate.y >= -90 && coordinate.y <= 90,
                "y should be within [-90, 90] but was " + coordinate.y);
    }
}
