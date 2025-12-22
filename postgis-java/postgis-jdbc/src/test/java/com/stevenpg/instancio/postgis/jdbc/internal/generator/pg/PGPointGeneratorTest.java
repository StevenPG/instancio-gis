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

package com.stevenpg.instancio.postgis.jdbc.internal.generator.pg;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.postgresql.geometric.PGpoint;

import static org.junit.jupiter.api.Assertions.*;

class PGPointGeneratorTest {

    @Test
    void shouldGenerateWithinRanges() {
        var gen = new PGPointGenerator()
                .xRange(-10, 10)
                .yRange(-5, 5);

        PGpoint p = gen.generate(null);
        assertNotNull(p);
        assertTrue(p.x >= -10 && p.x <= 10);
        assertTrue(p.y >= -5 && p.y <= 5);
    }

    @Test
    void instancioShouldCreatePGpointViaSPI() {
        PGpoint p = Instancio.create(PGpoint.class);
        assertNotNull(p);
    }
}
