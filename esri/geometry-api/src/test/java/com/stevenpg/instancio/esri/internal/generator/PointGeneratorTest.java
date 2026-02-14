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

package com.stevenpg.instancio.esri.internal.generator;

import com.esri.core.geometry.Point;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var point = Instancio.create(Point.class);
        assertNotNull(point);
        assertTrue(point.getX() >= -180 && point.getX() <= 180,
                "X coordinate should be within WGS84 longitude bounds [-180, 180], was: " + point.getX());
        assertTrue(point.getY() >= -90 && point.getY() <= 90,
                "Y coordinate should be within WGS84 latitude bounds [-90, 90], was: " + point.getY());
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new PointGenerator()
                .xRange(-100, -80)
                .yRange(30, 50)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getX() >= -100 && result.getX() <= -80);
        assertTrue(result.getY() >= 30 && result.getY() <= 50);
    }

    @Test
    void generate() {
        var result = new PointGenerator().generate(new DefaultRandom());
        assertNotNull(result);
        assertTrue(result.getX() >= -180 && result.getX() <= 180,
                "X coordinate should be within WGS84 longitude bounds [-180, 180], was: " + result.getX());
        assertTrue(result.getY() >= -90 && result.getY() <= 90,
                "Y coordinate should be within WGS84 latitude bounds [-90, 90], was: " + result.getY());
    }
}
