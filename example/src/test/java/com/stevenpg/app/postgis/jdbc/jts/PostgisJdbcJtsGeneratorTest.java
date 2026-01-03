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

package com.stevenpg.app.postgis.jdbc.jts;

import org.instancio.Random;
import org.instancio.support.DefaultRandom;

import com.stevenpg.instancio.postgis.jdbc.jts.GenPostgisJdbcJts;
import net.postgis.jdbc.jts.JtsGeometry;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostgisJdbcJtsGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGenerateJtsGeometryWithInstancio() {
        JtsGeometry jtsGeom = Instancio.create(JtsGeometry.class);
        assertNotNull(jtsGeom);
        assertNotNull(jtsGeom.getGeometry());
    }

    @Test
    void shouldGenerateJtsGeometryWithGenerator() {
        JtsGeometry jtsGeom = GenPostgisJdbcJts.jtsGeometry().generate(random);
        assertNotNull(jtsGeom);
        assertNotNull(jtsGeom.getGeometry());
    }
}
