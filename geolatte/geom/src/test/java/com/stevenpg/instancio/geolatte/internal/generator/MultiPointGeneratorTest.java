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
import org.geolatte.geom.MultiPoint;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiPointGeneratorTest {

    @RepeatedTest(5)
    void create() {
        // MultiPoint<P> requires a type parameter; use G2D since generators produce WGS84 coordinates
        var multiPoint = Instancio.of(MultiPoint.class).withTypeParameters(G2D.class).create();
        assertNotNull(multiPoint);

        // A multi-point must contain at least 2 geometries
        assertTrue(multiPoint.getNumGeometries() >= 2,
                "MultiPoint must have >= 2 geometries, had: " + multiPoint.getNumGeometries());

        // Validate each contained point's coordinates fall within default WGS84 bounds
        for (int i = 0; i < multiPoint.getNumGeometries(); i++) {
            var pos = (G2D) multiPoint.getGeometryN(i).getPositionN(0);
            assertTrue(pos.getLon() >= -180 && pos.getLon() <= 180,
                    "Longitude must be within [-180, 180], was: " + pos.getLon());
            assertTrue(pos.getLat() >= -90 && pos.getLat() <= 90,
                    "Latitude must be within [-90, 90], was: " + pos.getLat());
        }
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new MultiPointGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .length(3, 6)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getNumGeometries() >= 3 && result.getNumGeometries() <= 6);
        for (int i = 0; i < result.getNumGeometries(); i++) {
            var point = result.getGeometryN(i);
            assertTrue(point.getPositionN(0).getCoordinate(0) >= -10
                    && point.getPositionN(0).getCoordinate(0) <= 10);
            assertTrue(point.getPositionN(0).getCoordinate(1) >= -5
                    && point.getPositionN(0).getCoordinate(1) <= 5);
        }
    }

    @Test
    void generate() {
        var result = new MultiPointGenerator().generate(new DefaultRandom());
        assertNotNull(result);

        // A multi-point must contain at least 2 geometries
        assertTrue(result.getNumGeometries() >= 2,
                "MultiPoint must have >= 2 geometries, had: " + result.getNumGeometries());

        // Validate each contained point's coordinates fall within default WGS84 bounds
        for (int i = 0; i < result.getNumGeometries(); i++) {
            var pos = (G2D) result.getGeometryN(i).getPositionN(0);
            assertTrue(pos.getLon() >= -180 && pos.getLon() <= 180,
                    "Longitude must be within [-180, 180], was: " + pos.getLon());
            assertTrue(pos.getLat() >= -90 && pos.getLat() <= 90,
                    "Latitude must be within [-90, 90], was: " + pos.getLat());
        }
    }
}
