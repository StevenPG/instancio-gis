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

import com.stevenpg.instancio.locationtech.core.GenLocationtechJtsCore;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Envelope;

import static org.junit.jupiter.api.Assertions.*;

class EnvelopeGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGenerateEnvelopeUsingInstancio() {
        var env = Instancio.create(Envelope.class);
        assertNotNull(env);
        assertTrue(env.getWidth() >= 0);
        assertTrue(env.getHeight() >= 0);
    }

    @Test
    void shouldGenerateEnvelopeUsingGeneratorWithBounds() {
        var env = GenLocationtechJtsCore.envelope()
                .bounds(0, 5, 1, 6)
                .generate(random);
        assertEquals(0.0, env.getMinX());
        assertEquals(5.0, env.getMaxX());
        assertEquals(1.0, env.getMinY());
        assertEquals(6.0, env.getMaxY());
    }
}
