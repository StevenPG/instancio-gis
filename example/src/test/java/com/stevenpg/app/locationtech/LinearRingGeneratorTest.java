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

import com.stevenpg.instancio.locationtech.core.GenLocationtechJtsCore;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.LinearRing;

import static org.junit.jupiter.api.Assertions.*;

class LinearRingGeneratorTest {

    @Test
    void shouldGenerateLinearRingUsingInstancio() {
        var linearRing = Instancio.create(LinearRing.class);

        assertNotNull(linearRing);
        assertTrue(linearRing.isClosed());
        assertTrue(linearRing.getCoordinates().length >= 4);

        // Verify first and last coordinates are the same
        var coords = linearRing.getCoordinates();
        assertEquals(coords[0], coords[coords.length - 1]);
    }

    @Test
    void shouldGenerateLinearRingUsingGenerator() {
        var linearRing = GenLocationtechJtsCore.linearRing()
                .length(5)
                .generate(null);

        assertNotNull(linearRing);
        assertTrue(linearRing.isClosed());
        assertEquals(6, linearRing.getCoordinates().length); // 5 unique + 1 closing

        var coords = linearRing.getCoordinates();
        assertEquals(coords[0], coords[coords.length - 1]);
    }

    @Test
    void shouldGenerateLinearRingWithinBounds() {
        var envelope = new org.locationtech.jts.geom.Envelope(-10, 10, -5, 5);
        var linearRing = GenLocationtechJtsCore.linearRing()
                .within(envelope)
                .generate(null);

        assertNotNull(linearRing);
        assertTrue(linearRing.isClosed());
        assertTrue(envelope.contains(linearRing.getEnvelopeInternal()));
    }

    @Test
    void shouldGenerateLinearRingWithCustomGeometryFactory() {
        var factory = new org.locationtech.jts.geom.GeometryFactory();
        var linearRing = GenLocationtechJtsCore.linearRing()
                .geometryFactory(factory)
                .generate(null);

        assertNotNull(linearRing);
        assertTrue(linearRing.isClosed());
        assertSame(factory, linearRing.getFactory());
    }
}
