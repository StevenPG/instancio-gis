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

package com.stevenpg.app.locationtech;

import org.instancio.Random;
import org.instancio.support.DefaultRandom;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;

import static org.junit.jupiter.api.Assertions.*;

class GeometryGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGenerateGeometryUsingInstancio() {
        var geom = Instancio.create(Geometry.class);
        assertNotNull(geom);
        assertFalse(geom.isEmpty());
    }

    @Test
    void shouldGenerateGeometryWithinEnvelopeUsingGenerator() {
        var bounds = new Envelope(-5, 5, -5, 5);
        var geom = new com.stevenpg.instancio.locationtech.core.internal.generator.geom.GeometryGenerator()
                .within(bounds)
                .generate(random);

        assertNotNull(geom);
        var env = geom.getEnvelopeInternal();
        assertTrue(env.getMinX() >= bounds.getMinX());
        assertTrue(env.getMaxX() <= bounds.getMaxX());
        assertTrue(env.getMinY() >= bounds.getMinY());
        assertTrue(env.getMaxY() <= bounds.getMaxY());
    }
}
