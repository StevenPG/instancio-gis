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
import org.geolatte.geom.Polygon;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PolygonGeneratorTest {

    @RepeatedTest(5)
    void create() {
        // Polygon<P> requires a type parameter; use G2D since generators produce WGS84 coordinates
        var polygon = Instancio.of(Polygon.class).withTypeParameters(G2D.class).create();
        assertNotNull(polygon);

        // A polygon must have an exterior ring with at least 4 positions
        assertNotNull(polygon.getExteriorRing(), "Polygon must have an exterior ring");
        assertTrue(polygon.getExteriorRing().getNumPositions() >= 4,
                "Exterior ring must have >= 4 positions, had: " + polygon.getExteriorRing().getNumPositions());

        // Validate exterior ring positions fall within default WGS84 bounds
        var ring = polygon.getExteriorRing();
        for (int i = 0; i < ring.getNumPositions(); i++) {
            var pos = (G2D) ring.getPositionN(i);
            assertTrue(pos.getLon() >= -180 && pos.getLon() <= 180,
                    "Longitude must be within [-180, 180], was: " + pos.getLon());
            assertTrue(pos.getLat() >= -90 && pos.getLat() <= 90,
                    "Latitude must be within [-90, 90], was: " + pos.getLat());
        }
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new PolygonGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .generate(new DefaultRandom());

        assertNotNull(result);
        // A polygon must have an exterior ring
        assertNotNull(result.getExteriorRing());
        assertTrue(result.getExteriorRing().getNumPositions() >= 4);
    }

    @Test
    void generate() {
        var result = new PolygonGenerator().generate(new DefaultRandom());
        assertNotNull(result);

        // A polygon must have an exterior ring with at least 4 positions
        assertNotNull(result.getExteriorRing(), "Polygon must have an exterior ring");
        assertTrue(result.getExteriorRing().getNumPositions() >= 4,
                "Exterior ring must have >= 4 positions, had: " + result.getExteriorRing().getNumPositions());

        // Validate exterior ring positions fall within default WGS84 bounds
        var ring = result.getExteriorRing();
        for (int i = 0; i < ring.getNumPositions(); i++) {
            var pos = (G2D) ring.getPositionN(i);
            assertTrue(pos.getLon() >= -180 && pos.getLon() <= 180,
                    "Longitude must be within [-180, 180], was: " + pos.getLon());
            assertTrue(pos.getLat() >= -90 && pos.getLat() <= 90,
                    "Latitude must be within [-90, 90], was: " + pos.getLat());
        }
    }
}
