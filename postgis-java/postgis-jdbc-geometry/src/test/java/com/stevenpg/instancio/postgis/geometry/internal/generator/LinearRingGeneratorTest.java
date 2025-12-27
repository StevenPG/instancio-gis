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

import net.postgis.jdbc.geometry.LinearRing;
import org.instancio.Random;
import org.instancio.generator.Hints;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearRingGeneratorTest {

    private final LinearRingGenerator generator = new LinearRingGenerator();
    private final Random random = new DefaultRandom();

    @Test
    void hints() {
        Hints hints = generator.hints();
        assertNotNull(hints);
    }

    @Test
    void generateWithDefaults() {
        LinearRing lr = generator.generate(random);
        assertNotNull(lr);
        assertTrue(lr.numPoints() >= 4); // 3 points + 1 closing
        assertEquals(lr.getPoint(0).x, lr.getPoint(lr.numPoints() - 1).x, 0.0001);
        assertEquals(lr.getPoint(0).y, lr.getPoint(lr.numPoints() - 1).y, 0.0001);
    }

    @Test
    void generateWithNullRandom() {
        LinearRing lr = generator.generate(null);
        assertNotNull(lr);
    }

    @Test
    void xRange() {
        generator.xRange(10.0, 10.0);
        LinearRing lr = generator.generate(random);
        for (int i = 0; i < lr.numPoints(); i++) {
            assertEquals(10.0, lr.getPoint(i).x);
        }
    }

    @Test
    void yRange() {
        generator.yRange(20.0, 20.0);
        LinearRing lr = generator.generate(random);
        for (int i = 0; i < lr.numPoints(); i++) {
            assertEquals(20.0, lr.getPoint(i).y);
        }
    }

    @Test
    void pointsRange() {
        generator.pointsRange(4, 4);
        LinearRing lr = generator.generate(random);
        // n=4 in loop + 1 closing = 5 points
        assertEquals(5, lr.numPoints());
    }

    @Test
    void zRangeAndSridShouldReturnThis() {
        // These methods have default implementations in NumericRangeSpec
        assertSame(generator, generator.zRange(0, 10));
        assertSame(generator, generator.srid(4326));
    }

    @Test
    void shouldThrowExceptionOnInvalidWKT() {
        // By setting pointsRange(0, 0), n will be 0.
        // The generator loop won't run, resulting in an invalid WKT: "LINESTRING(, 0.0 0.0)"
        // This triggers an exception in the LinearRing constructor.
        generator.pointsRange(0, 0);
        assertThrows(IllegalStateException.class, () -> generator.generate(random));
    }
}
