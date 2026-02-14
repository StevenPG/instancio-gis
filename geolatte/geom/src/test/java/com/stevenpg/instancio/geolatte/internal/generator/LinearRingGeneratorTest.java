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
import org.geolatte.geom.LinearRing;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinearRingGeneratorTest {

    @RepeatedTest(5)
    void create() {
        // LinearRing<P> requires a type parameter; use G2D since generators produce WGS84 coordinates
        var linearRing = Instancio.of(LinearRing.class).withTypeParameters(G2D.class).create();
        assertNotNull(linearRing);

        // A valid linear ring must have at least 4 positions (3 distinct + closing point)
        assertTrue(linearRing.getNumPositions() >= 4,
                "LinearRing must have >= 4 positions, had: " + linearRing.getNumPositions());

        // The ring must be closed: first position equals last position
        var first = (G2D) linearRing.getPositionN(0);
        var last = (G2D) linearRing.getPositionN(linearRing.getNumPositions() - 1);
        assertEquals(first.getLon(), last.getLon(), "First and last longitude must match for a closed ring");
        assertEquals(first.getLat(), last.getLat(), "First and last latitude must match for a closed ring");

        // Validate all positions fall within default WGS84 bounds
        for (int i = 0; i < linearRing.getNumPositions(); i++) {
            var pos = (G2D) linearRing.getPositionN(i);
            assertTrue(pos.getLon() >= -180 && pos.getLon() <= 180,
                    "Longitude must be within [-180, 180], was: " + pos.getLon());
            assertTrue(pos.getLat() >= -90 && pos.getLat() <= 90,
                    "Latitude must be within [-90, 90], was: " + pos.getLat());
        }
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new LinearRingGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .generate(new DefaultRandom());

        assertNotNull(result);
        // A valid linear ring must have at least 4 positions
        assertTrue(result.getNumPositions() >= 4);
        // The ring must be closed: first position equals last position
        var first = result.getPositionN(0);
        var last = result.getPositionN(result.getNumPositions() - 1);
        assertEquals(first.getCoordinate(0), last.getCoordinate(0));
        assertEquals(first.getCoordinate(1), last.getCoordinate(1));

        for (int i = 0; i < result.getNumPositions(); i++) {
            var pos = result.getPositionN(i);
            assertTrue(pos.getCoordinate(0) >= -10 && pos.getCoordinate(0) <= 10);
            assertTrue(pos.getCoordinate(1) >= -5 && pos.getCoordinate(1) <= 5);
        }
    }

    @Test
    void generate() {
        var result = new LinearRingGenerator().generate(new DefaultRandom());
        assertNotNull(result);

        // A valid linear ring must have at least 4 positions
        assertTrue(result.getNumPositions() >= 4,
                "LinearRing must have >= 4 positions, had: " + result.getNumPositions());

        // The ring must be closed: first position equals last position
        var first = (G2D) result.getPositionN(0);
        var last = (G2D) result.getPositionN(result.getNumPositions() - 1);
        assertEquals(first.getLon(), last.getLon(), "First and last longitude must match for a closed ring");
        assertEquals(first.getLat(), last.getLat(), "First and last latitude must match for a closed ring");

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
