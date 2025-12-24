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
import org.locationtech.jts.geom.Point;

import static org.junit.jupiter.api.Assertions.*;

class PointGeneratorTest {

    @Test
    void shouldGeneratePointUsingInstancio() {
        var pt = Instancio.create(Point.class);
        assertNotNull(pt);
        assertFalse(pt.isEmpty());
    }

    @Test
    void shouldGeneratePointUsingGenerator() {
        var pt = GenLocationtechJtsCore.point()
                .coordinate(1.25, -2.5)
                .generate(null);
        assertNotNull(pt);
        assertEquals(1.25, pt.getX());
        assertEquals(-2.5, pt.getY());
    }
}
