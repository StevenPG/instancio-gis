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

import com.stevenpg.instancio.spatial4j.internal.generator.RectangleGenerator;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.shape.Rectangle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectangleGeneratorTest {

    private final Random random = new DefaultRandom();

    @RepeatedTest(5)
    void create() {
        var rectangle = Instancio.create(Rectangle.class);
        assertNotNull(rectangle);
        // Validate default WGS84 longitude bounds
        assertTrue(rectangle.getMinX() >= -180 && rectangle.getMinX() <= 180,
                "MinX should be within [-180, 180], was: " + rectangle.getMinX());
        assertTrue(rectangle.getMaxX() >= -180 && rectangle.getMaxX() <= 180,
                "MaxX should be within [-180, 180], was: " + rectangle.getMaxX());
        // Validate default WGS84 latitude bounds
        assertTrue(rectangle.getMinY() >= -90 && rectangle.getMinY() <= 90,
                "MinY should be within [-90, 90], was: " + rectangle.getMinY());
        assertTrue(rectangle.getMaxY() >= -90 && rectangle.getMaxY() <= 90,
                "MaxY should be within [-90, 90], was: " + rectangle.getMaxY());
        // Validate min <= max ordering
        assertTrue(rectangle.getMinX() <= rectangle.getMaxX(),
                "MinX should be <= MaxX, but was: " + rectangle.getMinX() + " > " + rectangle.getMaxX());
        assertTrue(rectangle.getMinY() <= rectangle.getMaxY(),
                "MinY should be <= MaxY, but was: " + rectangle.getMinY() + " > " + rectangle.getMaxY());
    }

    @Test
    void shouldGenerateWithinRanges() {
        var gen = new RectangleGenerator().xRange(-10, 10).yRange(-5, 5);
        var rect = gen.generate(random);
        assertNotNull(rect);
        // Verify the rectangle bounds are properly ordered
        assertTrue(rect.getMinX() <= rect.getMaxX());
        assertTrue(rect.getMinY() <= rect.getMaxY());
        // Verify values are within the configured ranges
        assertTrue(rect.getMinX() >= -10 && rect.getMaxX() <= 10);
        assertTrue(rect.getMinY() >= -5 && rect.getMaxY() <= 5);
    }

    @Test
    void generate() {
        var result = new RectangleGenerator().generate(random);
        assertNotNull(result);
        // Validate default WGS84 longitude bounds
        assertTrue(result.getMinX() >= -180 && result.getMinX() <= 180,
                "MinX should be within [-180, 180], was: " + result.getMinX());
        assertTrue(result.getMaxX() >= -180 && result.getMaxX() <= 180,
                "MaxX should be within [-180, 180], was: " + result.getMaxX());
        // Validate default WGS84 latitude bounds
        assertTrue(result.getMinY() >= -90 && result.getMinY() <= 90,
                "MinY should be within [-90, 90], was: " + result.getMinY());
        assertTrue(result.getMaxY() >= -90 && result.getMaxY() <= 90,
                "MaxY should be within [-90, 90], was: " + result.getMaxY());
        // Validate min <= max ordering
        assertTrue(result.getMinX() <= result.getMaxX(),
                "MinX should be <= MaxX, but was: " + result.getMinX() + " > " + result.getMaxX());
        assertTrue(result.getMinY() <= result.getMaxY(),
                "MinY should be <= MaxY, but was: " + result.getMinY() + " > " + result.getMaxY());
    }
}
