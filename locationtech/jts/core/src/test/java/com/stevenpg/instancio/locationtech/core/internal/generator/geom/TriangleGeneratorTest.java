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

package com.stevenpg.instancio.locationtech.core.internal.generator.geom;

import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

import static org.junit.jupiter.api.Assertions.*;

class TriangleGeneratorTest {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    void shouldGenerateTriangleViaSPI() {
        // Verify that service provider wiring returns a Triangle instance
        var triangle = Instancio.create(Triangle.class);
        assertNotNull(triangle);
        var verts = vertices(triangle);
        assertEquals(3, verts.length);
        assertNotNull(verts[0]);
        assertNotNull(verts[1]);
        assertNotNull(verts[2]);
    }

    @Test
    void shouldGenerateTriangleDefault() {
        var triangle = new TriangleGenerator().generate(new DefaultRandom());
        assertNotNull(triangle);
        var verts = vertices(triangle);
        assertEquals(3, verts.length);
        assertNotNull(verts[0]);
        assertNotNull(verts[1]);
        assertNotNull(verts[2]);
    }

    @Test
    void shouldGenerateTriangleWithinEnvelope() {
        var env = new Envelope(0, 10, 0, 10);
        var triangle = new TriangleGenerator()
                .within(env)
                .generate(new DefaultRandom());

        assertNotNull(triangle);
        var verts = vertices(triangle);
        for (Coordinate c : verts) {
            assertTrue(c.getX() >= 0 && c.getX() <= 10);
            assertTrue(c.getY() >= 0 && c.getY() <= 10);
        }
    }

    @Test
    void shouldGenerateTriangleFromProvidedPoints() {
        Point p0 = geometryFactory.createPoint(new Coordinate(0, 0));
        Point p1 = geometryFactory.createPoint(new Coordinate(5, 0));
        Point p2 = geometryFactory.createPoint(new Coordinate(0, 5));

        var triangle = new TriangleGenerator()
                .points(p0, p1, p2)
                .generate(new DefaultRandom());

        var verts = vertices(triangle);
        assertEquals(p0.getCoordinate(), verts[0]);
        assertEquals(p1.getCoordinate(), verts[1]);
        assertEquals(p2.getCoordinate(), verts[2]);
    }

    private static Coordinate[] vertices(Triangle t) {
        try {
            // Try public fields a,b,c (common in JTS Triangle)
            var fa = t.getClass().getField("a");
            var fb = t.getClass().getField("b");
            var fc = t.getClass().getField("c");
            return new Coordinate[]{
                    (Coordinate) fa.get(t),
                    (Coordinate) fb.get(t),
                    (Coordinate) fc.get(t)
            };
        } catch (NoSuchFieldException e1) {
            try {
                // Alternate names p0,p1,p2
                var f0 = t.getClass().getField("p0");
                var f1 = t.getClass().getField("p1");
                var f2 = t.getClass().getField("p2");
                return new Coordinate[]{
                        (Coordinate) f0.get(t),
                        (Coordinate) f1.get(t),
                        (Coordinate) f2.get(t)
                };
            } catch (ReflectiveOperationException e2) {
                throw new AssertionError("Unable to access Triangle vertices via reflection", e2);
            }
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }
}
