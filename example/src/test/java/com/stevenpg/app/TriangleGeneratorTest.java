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

package com.stevenpg.app;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Triangle;

import static org.junit.jupiter.api.Assertions.*;

class TriangleGeneratorTest {

    @Test
    void shouldGenerateTriangleUsingInstancio() {
        var tri = Instancio.create(Triangle.class);
        assertNotNull(tri);
    }

    @Test
    void shouldGenerateTriangleUsingGenerator() {
        var tri = new com.stevenpg.instancio.locationtech.core.internal.generator.geom.TriangleGenerator()
                .generate(null);
        assertNotNull(tri);
    }
}
