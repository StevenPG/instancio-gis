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
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateArraySequenceGeneratorTest {

    @Test
    void shouldGenerateCoordinateArraySequenceUsingInstancio() {
        var seq = Instancio.create(CoordinateArraySequence.class);
        assertNotNull(seq);
        assertTrue(seq.size() >= 1);
    }

    @Test
    void shouldGenerateCoordinateArraySequenceUsingGenerator() {
        var seq = GenLocationtechJtsCore.coordinateArraySequence()
                .length(4)
                .generate(null);
        assertEquals(4, seq.size());
    }
}
