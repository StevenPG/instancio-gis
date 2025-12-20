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

class LineSegmentGeneratorTest {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    void shouldGenerateLineSegmentViaSPI() {
        var seg = Instancio.create(LineSegment.class);
        assertNotNull(seg);
        var verts = endpoints(seg);
        assertNotNull(verts[0]);
        assertNotNull(verts[1]);
    }

    @Test
    void shouldGenerateDefaultLineSegment() {
        var seg = new LineSegmentGenerator().generate(new DefaultRandom());
        assertNotNull(seg);
        var verts = endpoints(seg);
        assertNotNull(verts[0]);
        assertNotNull(verts[1]);
    }

    @Test
    void shouldGenerateLineSegmentWithinEnvelope() {
        var env = new Envelope(0, 10, 0, 10);
        var seg = new LineSegmentGenerator()
                .within(env)
                .generate(new DefaultRandom());

        var verts = endpoints(seg);
        for (Coordinate c : verts) {
            assertTrue(c.getX() >= 0 && c.getX() <= 10);
            assertTrue(c.getY() >= 0 && c.getY() <= 10);
        }
    }

    @Test
    void shouldGenerateFromProvidedPoints() {
        Point p0 = geometryFactory.createPoint(new Coordinate(1, 2));
        Point p1 = geometryFactory.createPoint(new Coordinate(3, 4));

        var seg = new LineSegmentGenerator()
                .points(p0, p1)
                .generate(new DefaultRandom());

        var verts = endpoints(seg);
        assertEquals(p0.getCoordinate(), verts[0]);
        assertEquals(p1.getCoordinate(), verts[1]);
    }

    private static Coordinate[] endpoints(LineSegment s) {
        try {
            var f0 = s.getClass().getField("p0");
            var f1 = s.getClass().getField("p1");
            return new Coordinate[]{ (Coordinate) f0.get(s), (Coordinate) f1.get(s) };
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Unable to access LineSegment endpoints via reflection", e);
        }
    }
}
