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

import org.instancio.Random;
import org.instancio.support.DefaultRandom;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Triangle;

import static org.junit.jupiter.api.Assertions.*;

class TriangleGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGenerateTriangleUsingInstancio() {
        var tri = Instancio.create(Triangle.class);
        assertNotNull(tri);
        assertNotNull(tri.p0);
        assertNotNull(tri.p1);
        assertNotNull(tri.p2);
    }

    @Test
    void shouldGenerateTriangleUsingGenerator() {
        var tri = new com.stevenpg.instancio.locationtech.core.internal.generator.geom.TriangleGenerator()
                .generate(random);
        assertNotNull(tri);
    }
}
