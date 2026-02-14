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

package com.stevenpg.instancio.spatial4j;

import com.stevenpg.instancio.spatial4j.internal.generator.PointGenerator;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.shape.Point;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointGeneratorTest {

    private final Random random = new DefaultRandom();

    @RepeatedTest(5)
    void create() {
        var point = Instancio.create(Point.class);
        assertNotNull(point);
        // Validate default WGS84 longitude bounds
        assertTrue(point.getX() >= -180 && point.getX() <= 180,
                "X (longitude) should be within [-180, 180], was: " + point.getX());
        // Validate default WGS84 latitude bounds
        assertTrue(point.getY() >= -90 && point.getY() <= 90,
                "Y (latitude) should be within [-90, 90], was: " + point.getY());
    }

    @Test
    void shouldGenerateWithinRanges() {
        var gen = new PointGenerator().xRange(-10, 10).yRange(-5, 5);
        var p = gen.generate(random);
        assertNotNull(p);
        assertTrue(p.getX() >= -10 && p.getX() <= 10);
        assertTrue(p.getY() >= -5 && p.getY() <= 5);
    }

    @Test
    void generate() {
        var result = new PointGenerator().generate(random);
        assertNotNull(result);
        // Validate default WGS84 longitude bounds
        assertTrue(result.getX() >= -180 && result.getX() <= 180,
                "X (longitude) should be within [-180, 180], was: " + result.getX());
        // Validate default WGS84 latitude bounds
        assertTrue(result.getY() >= -90 && result.getY() <= 90,
                "Y (latitude) should be within [-90, 90], was: " + result.getY());
    }
}
