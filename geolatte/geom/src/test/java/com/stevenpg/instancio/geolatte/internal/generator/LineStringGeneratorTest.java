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

package com.stevenpg.instancio.geolatte.internal.generator;

import org.geolatte.geom.G2D;
import org.geolatte.geom.LineString;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LineStringGeneratorTest {

    @RepeatedTest(5)
    void create() {
        // LineString<P> requires a type parameter; use G2D since generators produce WGS84 coordinates
        var lineString = Instancio.of(LineString.class).withTypeParameters(G2D.class).create();
        assertNotNull(lineString);

        // A valid line string must have at least 2 positions
        assertTrue(lineString.getNumPositions() >= 2,
                "LineString must have >= 2 positions, had: " + lineString.getNumPositions());

        // Validate all positions fall within default WGS84 bounds
        for (int i = 0; i < lineString.getNumPositions(); i++) {
            var pos = (G2D) lineString.getPositionN(i);
            assertTrue(pos.getLon() >= -180 && pos.getLon() <= 180,
                    "Longitude must be within [-180, 180], was: " + pos.getLon());
            assertTrue(pos.getLat() >= -90 && pos.getLat() <= 90,
                    "Latitude must be within [-90, 90], was: " + pos.getLat());
        }
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new LineStringGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .length(3, 6)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getNumPositions() >= 3 && result.getNumPositions() <= 6);
        for (int i = 0; i < result.getNumPositions(); i++) {
            var pos = result.getPositionN(i);
            assertTrue(pos.getCoordinate(0) >= -10 && pos.getCoordinate(0) <= 10);
            assertTrue(pos.getCoordinate(1) >= -5 && pos.getCoordinate(1) <= 5);
        }
    }

    @Test
    void generate() {
        var result = new LineStringGenerator().generate(new DefaultRandom());
        assertNotNull(result);

        // A valid line string must have at least 2 positions
        assertTrue(result.getNumPositions() >= 2,
                "LineString must have >= 2 positions, had: " + result.getNumPositions());

        // Validate all positions fall within default WGS84 bounds
        for (int i = 0; i < result.getNumPositions(); i++) {
            var pos = (G2D) result.getPositionN(i);
            assertTrue(pos.getLon() >= -180 && pos.getLon() <= 180,
                    "Longitude must be within [-180, 180], was: " + pos.getLon());
            assertTrue(pos.getLat() >= -90 && pos.getLat() <= 90,
                    "Latitude must be within [-90, 90], was: " + pos.getLat());
        }
    }
}
