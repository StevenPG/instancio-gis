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

package com.stevenpg.app.h3;

import com.stevenpg.instancio.h3.GenH3;
import com.uber.h3core.util.LatLng;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class H3GeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGenerateLatLngWithInstancio() {
        LatLng ll = Instancio.create(LatLng.class);
        assertNotNull(ll);
        assertTrue(ll.lat >= -90 && ll.lat <= 90);
        assertTrue(ll.lng >= -180 && ll.lng <= 180);
    }

    @Test
    void shouldGenerateLatLngWithGenerator() {
        LatLng ll = GenH3.latLng()
                .latRange(40, 41)
                .lngRange(-74, -73)
                .generate(random);
        assertNotNull(ll);
        assertTrue(ll.lat >= 40 && ll.lat <= 41);
        assertTrue(ll.lng >= -74 && ll.lng <= -73);
    }

    @Test
    void shouldGenerateH3IndexWithGenerator() {
        Long index = GenH3.h3Index()
                .resolution(5)
                .generate(random);
        assertNotNull(index);
        assertTrue(index > 0);
    }
}
