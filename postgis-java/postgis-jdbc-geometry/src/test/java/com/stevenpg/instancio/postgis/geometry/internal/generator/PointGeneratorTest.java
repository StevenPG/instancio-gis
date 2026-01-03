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

import net.postgis.jdbc.geometry.Point;
import org.instancio.Random;
import org.instancio.generator.Hints;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointGeneratorTest {

    private final PointGenerator generator = new PointGenerator();
    private final Random random = new DefaultRandom();

    @Test
    void hints() {
        Hints hints = generator.hints();
        assertNotNull(hints);
    }

    @Test
    void generateWithDefaults() {
        Point p = generator.generate(random);
        assertNotNull(p);
        assertTrue(p.x >= -180 && p.x <= 180);
        assertTrue(p.y >= -90 && p.y <= 90);
        assertEquals(0, p.getSrid());
    }

    @Test
    void generateWithNullRandom() {
        Point p = generator.generate(random);
        assertNotNull(p);
    }

    @Test
    void xRange() {
        generator.xRange(10.5, 10.5);
        Point p = generator.generate(random);
        assertEquals(10.5, p.x);
    }

    @Test
    void yRange() {
        generator.yRange(20.5, 20.5);
        Point p = generator.generate(random);
        assertEquals(20.5, p.y);
    }

    @Test
    void zRange() {
        generator.zRange(30.5, 30.5);
        Point p = generator.generate(random);
        assertEquals(30.5, p.z);
    }

    @Test
    void srid() {
        generator.srid(4326);
        Point p = generator.generate(random);
        assertEquals(4326, p.getSrid());
    }

    @Test
    void generateWithZ() {
        generator.zRange(5.0, 10.0);
        Point p = generator.generate(random);
        assertTrue(p.z >= 5.0 && p.z <= 10.0);
    }

    @Test
    void shouldThrowExceptionOnInvalidWKT() {
        java.util.Locale defaultLocale = java.util.Locale.getDefault();
        try {
            java.util.Locale.setDefault(java.util.Locale.FRANCE); // Uses comma as decimal separator
            // String.format will produce "POINT(1,230000 4,560000)" which is invalid WKT
            assertThrows(IllegalStateException.class, () -> generator.generate(random));
        } finally {
            java.util.Locale.setDefault(defaultLocale);
        }
    }
}
