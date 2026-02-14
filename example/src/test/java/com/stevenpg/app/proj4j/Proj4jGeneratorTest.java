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

package com.stevenpg.app.proj4j;

import com.stevenpg.instancio.proj4j.GenProj4j;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Proj4jGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGenerateProjCoordinateWithInstancio() {
        ProjCoordinate pc = Instancio.create(ProjCoordinate.class);
        assertNotNull(pc);
        assertTrue(pc.x >= -180 && pc.x <= 180);
        assertTrue(pc.y >= -90 && pc.y <= 90);
    }

    @Test
    void shouldGenerateCrsWithInstancio() {
        CoordinateReferenceSystem crs = Instancio.create(CoordinateReferenceSystem.class);
        assertNotNull(crs);
        assertNotNull(crs.getProjection());
    }

    @Test
    void shouldGenerateProjCoordinateWithGenerator() {
        ProjCoordinate pc = GenProj4j.projCoordinate()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .generate(random);
        assertNotNull(pc);
        assertTrue(pc.x >= -10 && pc.x <= 10);
        assertTrue(pc.y >= -5 && pc.y <= 5);
    }

    @Test
    void shouldGenerateCrsWithGenerator() {
        CoordinateReferenceSystem crs = GenProj4j.crs()
                .epsgCodes("EPSG:4326")
                .generate(random);
        assertNotNull(crs);
    }
}
