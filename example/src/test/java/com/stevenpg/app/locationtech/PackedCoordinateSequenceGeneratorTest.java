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

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.impl.PackedCoordinateSequence;

import static org.junit.jupiter.api.Assertions.*;

class PackedCoordinateSequenceGeneratorTest {

    @Test
    void shouldGeneratePackedCoordinateSequenceUsingInstancio() {
        var seq = Instancio.create(PackedCoordinateSequence.class);
        assertNotNull(seq);
        assertTrue(seq.size() >= 1);
    }

    @Test
    void shouldGeneratePackedCoordinateSequenceUsingGenerator() {
        var seq = new com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.PackedCoordinateSequenceGenerator()
                .length(3)
                .generate(null);
        assertEquals(3, seq.size());
    }
}
