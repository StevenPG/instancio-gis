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

package com.stevenpg.instancio.postgis.jdbc.internal.spi;

import net.postgis.jdbc.PGbox2d;
import net.postgis.jdbc.PGbox3d;
import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;
import org.postgresql.geometric.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostgisJdbcServiceProviderTest {

    @RepeatedTest(5)
    void shouldAutoGeneratePGpoint() {
        assertNotNull(Instancio.create(PGpoint.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePGline() {
        assertNotNull(Instancio.create(PGline.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePGlseg() {
        assertNotNull(Instancio.create(PGlseg.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePGpath() {
        assertNotNull(Instancio.create(PGpath.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePGpolygon() {
        assertNotNull(Instancio.create(PGpolygon.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePGbox() {
        assertNotNull(Instancio.create(PGbox.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePGcircle() {
        assertNotNull(Instancio.create(PGcircle.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePGbox2d() {
        assertNotNull(Instancio.create(PGbox2d.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePGbox3d() {
        assertNotNull(Instancio.create(PGbox3d.class));
    }
}
