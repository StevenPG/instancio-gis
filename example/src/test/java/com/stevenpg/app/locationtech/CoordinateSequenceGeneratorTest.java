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
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Envelope;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateSequenceGeneratorTest {

    @Test
    void shouldGenerateCoordinateSequenceUsingInstancio() {
        var seq = Instancio.create(CoordinateSequence.class);
        assertNotNull(seq);
        assertTrue(seq.size() >= 1);
    }

    @Test
    void shouldGenerateCoordinateSequenceUsingGenerator() {
        var seq = GenLocationtechJtsCore.coordinateSequence()
                .length(4)
                .generate(null);
        assertEquals(4, seq.size());
    }

    @Test
    void shouldGenerateCoordinateSequenceWithinEnvelope() {
        var env = new Envelope(-10, 10, -5, 5);
        var seq = GenLocationtechJtsCore.coordinateSequence()
                .length(3)
                .within(env)
                .generate(null);
        assertEquals(3, seq.size());
        for (int i = 0; i < seq.size(); i++) {
            var c = seq.getCoordinate(i);
            assertTrue(env.contains(c));
        }
    }
}
