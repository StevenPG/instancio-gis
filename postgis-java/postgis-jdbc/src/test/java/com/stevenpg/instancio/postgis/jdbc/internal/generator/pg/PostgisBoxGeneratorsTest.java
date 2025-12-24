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

import net.postgis.jdbc.PGbox2d;
import net.postgis.jdbc.PGbox3d;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostgisBoxGeneratorsTest {

    @Test
    void shouldGeneratePGbox2d() {
        PGbox2d box = Instancio.create(PGbox2d.class);
        assertNotNull(box);
        assertNotNull(box.getLLB());
        assertNotNull(box.getURT());
        assertTrue(box.getLLB().x <= box.getURT().x);
        assertTrue(box.getLLB().y <= box.getURT().y);
    }

    @Test
    void shouldGeneratePGbox2dWithRange() {
        PGbox2d box = new PGBox2dGenerator()
                .xRange(10, 10)
                .yRange(20, 20)
                .generate(null);
        assertNotNull(box);
        assertEquals(10, box.getLLB().x);
        assertEquals(10, box.getURT().x);
        assertEquals(20, box.getLLB().y);
        assertEquals(20, box.getURT().y);
    }

    @Test
    void shouldGeneratePGbox3d() {
        PGbox3d box = Instancio.create(PGbox3d.class);
        assertNotNull(box);
        assertNotNull(box.getLLB());
        assertNotNull(box.getURT());
        assertTrue(box.getLLB().x <= box.getURT().x);
        assertTrue(box.getLLB().y <= box.getURT().y);
        assertTrue(box.getLLB().z <= box.getURT().z);
    }

    @Test
    void shouldGeneratePGbox3dWithRange() {
        PGbox3d box = new PGBox3dGenerator()
                .xRange(10, 10)
                .yRange(20, 20)
                .zRange(30, 30)
                .generate(null);
        assertNotNull(box);
        assertEquals(10, box.getLLB().x);
        assertEquals(10, box.getURT().x);
        assertEquals(20, box.getLLB().y);
        assertEquals(20, box.getURT().y);
        assertEquals(30, box.getLLB().z);
        assertEquals(30, box.getURT().z);
    }
}
