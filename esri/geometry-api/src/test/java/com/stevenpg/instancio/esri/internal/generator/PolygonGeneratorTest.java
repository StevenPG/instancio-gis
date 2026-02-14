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
import com.esri.core.geometry.Polygon;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolygonGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var polygon = Instancio.create(Polygon.class);
        assertNotNull(polygon);
        assertTrue(polygon.getPointCount() >= 3,
                "Polygon should contain at least 3 vertices, had: " + polygon.getPointCount());
        for (int i = 0; i < polygon.getPointCount(); i++) {
            Point pt = polygon.getPoint(i);
            assertTrue(pt.getX() >= -180 && pt.getX() <= 180,
                    "Vertex[" + i + "] X coordinate should be within WGS84 longitude bounds [-180, 180], was: " + pt.getX());
            assertTrue(pt.getY() >= -90 && pt.getY() <= 90,
                    "Vertex[" + i + "] Y coordinate should be within WGS84 latitude bounds [-90, 90], was: " + pt.getY());
        }
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new PolygonGenerator()
                .xRange(-50, 50)
                .yRange(-25, 25)
                .vertices(5, 5)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(5, result.getPointCount());
        for (int i = 0; i < result.getPointCount(); i++) {
            Point pt = result.getPoint(i);
            assertTrue(pt.getX() >= -50 && pt.getX() <= 50);
            assertTrue(pt.getY() >= -25 && pt.getY() <= 25);
        }
    }

    @Test
    void generate() {
        var result = new PolygonGenerator().generate(new DefaultRandom());
        assertNotNull(result);
        assertTrue(result.getPointCount() >= 3,
                "Polygon should contain at least 3 vertices, had: " + result.getPointCount());
        for (int i = 0; i < result.getPointCount(); i++) {
            Point pt = result.getPoint(i);
            assertTrue(pt.getX() >= -180 && pt.getX() <= 180,
                    "Vertex[" + i + "] X coordinate should be within WGS84 longitude bounds [-180, 180], was: " + pt.getX());
            assertTrue(pt.getY() >= -90 && pt.getY() <= 90,
                    "Vertex[" + i + "] Y coordinate should be within WGS84 latitude bounds [-90, 90], was: " + pt.getY());
        }
    }
}
