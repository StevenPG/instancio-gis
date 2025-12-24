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
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateList;
import org.locationtech.jts.geom.Envelope;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateListGeneratorTest {

    @Test
    void shouldGenerateCoordinateListUsingInstancio() {
        var list = Instancio.create(CoordinateList.class);
        assertNotNull(list);
        assertTrue(list.size() >= 1);
    }

    @Test
    void shouldGenerateCoordinateListUsingGenerator() {
        var gen = new com.stevenpg.instancio.locationtech.core.internal.generator.geom.CoordinateListGenerator()
                .length(3);
        var list = gen.generate(null);
        assertNotNull(list);
        assertEquals(3, list.size());
    }

    @Test
    void shouldGenerateCoordinateListWithinEnvelope() {
        var env = new Envelope(-10, 10, -5, 5);
        var list = new com.stevenpg.instancio.locationtech.core.internal.generator.geom.CoordinateListGenerator()
                .length(5)
                .within(env)
                .generate(null);

        assertEquals(5, list.size());
        for (Coordinate c : list) {
            assertTrue(env.contains(c));
        }
    }
}
