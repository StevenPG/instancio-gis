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
import org.geolatte.geom.GeometryCollection;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeometryCollectionGeneratorTest {

    @RepeatedTest(5)
    void create() {
        // GeometryCollection<P> requires a type parameter; use G2D since generators produce WGS84 coordinates
        var geometryCollection = Instancio.of(GeometryCollection.class).withTypeParameters(G2D.class).create();
        assertNotNull(geometryCollection);

        // A geometry collection must contain at least 2 geometries
        assertTrue(geometryCollection.getNumGeometries() >= 2,
                "GeometryCollection must have >= 2 geometries, had: " + geometryCollection.getNumGeometries());

        // Each contained geometry must not be null or empty
        for (int i = 0; i < geometryCollection.getNumGeometries(); i++) {
            assertNotNull(geometryCollection.getGeometryN(i),
                    "Geometry at index " + i + " must not be null");
            assertTrue(!geometryCollection.getGeometryN(i).isEmpty(),
                    "Geometry at index " + i + " must not be empty");
        }
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new GeometryCollectionGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .length(3, 6)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getNumGeometries() >= 3 && result.getNumGeometries() <= 6);
    }

    @Test
    void generate() {
        var result = new GeometryCollectionGenerator().generate(new DefaultRandom());
        assertNotNull(result);

        // A geometry collection must contain at least 2 geometries
        assertTrue(result.getNumGeometries() >= 2,
                "GeometryCollection must have >= 2 geometries, had: " + result.getNumGeometries());

        // Each contained geometry must not be null or empty
        for (int i = 0; i < result.getNumGeometries(); i++) {
            assertNotNull(result.getGeometryN(i),
                    "Geometry at index " + i + " must not be null");
            assertTrue(!result.getGeometryN(i).isEmpty(),
                    "Geometry at index " + i + " must not be empty");
        }
    }
}
