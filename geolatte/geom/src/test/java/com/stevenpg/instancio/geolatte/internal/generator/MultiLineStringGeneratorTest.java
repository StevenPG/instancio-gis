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
import org.geolatte.geom.MultiLineString;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiLineStringGeneratorTest {

    @RepeatedTest(5)
    void create() {
        // MultiLineString<P> requires a type parameter; use G2D since generators produce WGS84 coordinates
        var multiLineString = Instancio.of(MultiLineString.class).withTypeParameters(G2D.class).create();
        assertNotNull(multiLineString);

        // A multi-line string must contain at least 2 geometries
        assertTrue(multiLineString.getNumGeometries() >= 2,
                "MultiLineString must have >= 2 geometries, had: " + multiLineString.getNumGeometries());

        // Each contained line string must have at least 2 positions
        for (int i = 0; i < multiLineString.getNumGeometries(); i++) {
            assertTrue(multiLineString.getGeometryN(i).getNumPositions() >= 2,
                    "LineString at index " + i + " must have >= 2 positions");
        }
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new MultiLineStringGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .length(2, 4)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getNumGeometries() >= 2 && result.getNumGeometries() <= 4);
    }

    @Test
    void generate() {
        var result = new MultiLineStringGenerator().generate(new DefaultRandom());
        assertNotNull(result);

        // A multi-line string must contain at least 2 geometries
        assertTrue(result.getNumGeometries() >= 2,
                "MultiLineString must have >= 2 geometries, had: " + result.getNumGeometries());

        // Each contained line string must have at least 2 positions
        for (int i = 0; i < result.getNumGeometries(); i++) {
            assertTrue(result.getGeometryN(i).getNumPositions() >= 2,
                    "LineString at index " + i + " must have >= 2 positions");
        }
    }
}
