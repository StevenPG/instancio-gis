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
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.OctagonalEnvelope;

import static org.junit.jupiter.api.Assertions.*;

class OctagonalEnvelopeGeneratorTest {

    @Test
    void shouldGenerateOctagonalEnvelopeUsingInstancio() {
        var oe = Instancio.create(OctagonalEnvelope.class);
        assertNotNull(oe);
        assertFalse(oe.isNull());
    }

    @Test
    void shouldGenerateOctagonalEnvelopeUsingGenerator() {
        var oe = GenLocationtechJtsCore.octagonalEnvelope()
                .envelope(new Envelope(-1, 2, -3, 4))
                .generate(null);
        assertNotNull(oe);
        assertFalse(oe.isNull());
    }
}
