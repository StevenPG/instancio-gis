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

import com.stevenpg.instancio.spatial4j.internal.generator.CircleGenerator;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.shape.Circle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CircleGeneratorTest {

    private final Random random = new DefaultRandom();

    @RepeatedTest(5)
    void create() {
        var circle = Instancio.create(Circle.class);
        assertNotNull(circle);
        // Validate center point is within default WGS84 bounds
        assertTrue(circle.getCenter().getX() >= -180 && circle.getCenter().getX() <= 180,
                "Center X (longitude) should be within [-180, 180], was: " + circle.getCenter().getX());
        assertTrue(circle.getCenter().getY() >= -90 && circle.getCenter().getY() <= 90,
                "Center Y (latitude) should be within [-90, 90], was: " + circle.getCenter().getY());
        // Validate radius is within default range
        assertTrue(circle.getRadius() >= 0.1 && circle.getRadius() <= 10.0,
                "Radius should be within [0.1, 10.0], was: " + circle.getRadius());
    }

    @Test
    void shouldGenerateWithinRanges() {
        var gen = new CircleGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .radiusRange(1.0, 3.0);
        var circle = gen.generate(random);
        assertNotNull(circle);
        // Verify center is within the configured ranges
        assertTrue(circle.getCenter().getX() >= -10 && circle.getCenter().getX() <= 10);
        assertTrue(circle.getCenter().getY() >= -5 && circle.getCenter().getY() <= 5);
        // Verify radius is within the configured range
        assertTrue(circle.getRadius() >= 1.0 && circle.getRadius() <= 3.0);
    }

    @Test
    void generate() {
        var result = new CircleGenerator().generate(random);
        assertNotNull(result);
        // Validate center point is within default WGS84 bounds
        assertTrue(result.getCenter().getX() >= -180 && result.getCenter().getX() <= 180,
                "Center X (longitude) should be within [-180, 180], was: " + result.getCenter().getX());
        assertTrue(result.getCenter().getY() >= -90 && result.getCenter().getY() <= 90,
                "Center Y (latitude) should be within [-90, 90], was: " + result.getCenter().getY());
        // Validate radius is within default range
        assertTrue(result.getRadius() >= 0.1 && result.getRadius() <= 10.0,
                "Radius should be within [0.1, 10.0], was: " + result.getRadius());
    }
}
