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

package com.stevenpg.instancio.postgis.geometry.internal.generator;

import net.postgis.jdbc.geometry.Geometry;
import net.postgis.jdbc.geometry.Point;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometryGeneratorsTest {

    @Test
    void shouldGeneratePointWithinRange() {
        var gen = new PointGenerator().xRange(-1, 1).yRange(-2, 2);
        Point p = gen.generate(null);
        assertNotNull(p);
        assertTrue(p.x >= -1 && p.x <= 1);
        assertTrue(p.y >= -2 && p.y <= 2);
    }

    @Test
    void instancioShouldCreateGeometryAndPointViaSPI() {
        Geometry g = Instancio.create(Geometry.class);
        assertNotNull(g);
        Point p = Instancio.create(Point.class);
        assertNotNull(p);
    }
}
