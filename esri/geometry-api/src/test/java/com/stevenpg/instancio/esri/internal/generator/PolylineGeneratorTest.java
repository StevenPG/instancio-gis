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
import com.esri.core.geometry.Polyline;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolylineGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var polyline = Instancio.create(Polyline.class);
        assertNotNull(polyline);
        assertTrue(polyline.getPointCount() >= 2,
                "Polyline should contain at least 2 points, had: " + polyline.getPointCount());
        for (int i = 0; i < polyline.getPointCount(); i++) {
            Point pt = polyline.getPoint(i);
            assertTrue(pt.getX() >= -180 && pt.getX() <= 180,
                    "Vertex[" + i + "] X coordinate should be within WGS84 longitude bounds [-180, 180], was: " + pt.getX());
            assertTrue(pt.getY() >= -90 && pt.getY() <= 90,
                    "Vertex[" + i + "] Y coordinate should be within WGS84 latitude bounds [-90, 90], was: " + pt.getY());
        }
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new PolylineGenerator()
                .xRange(-10, 10)
                .yRange(-10, 10)
                .length(4, 4)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(4, result.getPointCount());
        for (int i = 0; i < result.getPointCount(); i++) {
            Point pt = result.getPoint(i);
            assertTrue(pt.getX() >= -10 && pt.getX() <= 10);
            assertTrue(pt.getY() >= -10 && pt.getY() <= 10);
        }
    }

    @Test
    void generate() {
        var result = new PolylineGenerator().generate(new DefaultRandom());
        assertNotNull(result);
        assertTrue(result.getPointCount() >= 2,
                "Polyline should contain at least 2 points, had: " + result.getPointCount());
        for (int i = 0; i < result.getPointCount(); i++) {
            Point pt = result.getPoint(i);
            assertTrue(pt.getX() >= -180 && pt.getX() <= 180,
                    "Vertex[" + i + "] X coordinate should be within WGS84 longitude bounds [-180, 180], was: " + pt.getX());
            assertTrue(pt.getY() >= -90 && pt.getY() <= 90,
                    "Vertex[" + i + "] Y coordinate should be within WGS84 latitude bounds [-90, 90], was: " + pt.getY());
        }
    }
}
