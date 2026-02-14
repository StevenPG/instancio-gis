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
import org.geolatte.geom.Point;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointGeneratorTest {

    @RepeatedTest(5)
    void create() {
        // Point<P> requires a type parameter; use G2D since generators produce WGS84 coordinates
        var point = Instancio.of(Point.class).withTypeParameters(G2D.class).create();
        assertNotNull(point);

        // Validate coordinates fall within default WGS84 bounds
        var position = (G2D) point.getPosition();
        assertTrue(position.getLon() >= -180 && position.getLon() <= 180,
                "Longitude must be within [-180, 180], was: " + position.getLon());
        assertTrue(position.getLat() >= -90 && position.getLat() <= 90,
                "Latitude must be within [-90, 90], was: " + position.getLat());
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new PointGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getPosition().getCoordinate(0) >= -10
                && result.getPosition().getCoordinate(0) <= 10);
        assertTrue(result.getPosition().getCoordinate(1) >= -5
                && result.getPosition().getCoordinate(1) <= 5);
    }

    @Test
    void generate() {
        var result = new PointGenerator().generate(new DefaultRandom());
        assertNotNull(result);

        // Validate coordinates fall within default WGS84 bounds
        var position = (G2D) result.getPosition();
        assertTrue(position.getLon() >= -180 && position.getLon() <= 180,
                "Longitude must be within [-180, 180], was: " + position.getLon());
        assertTrue(position.getLat() >= -90 && position.getLat() <= 90,
                "Latitude must be within [-90, 90], was: " + position.getLat());
    }
}
