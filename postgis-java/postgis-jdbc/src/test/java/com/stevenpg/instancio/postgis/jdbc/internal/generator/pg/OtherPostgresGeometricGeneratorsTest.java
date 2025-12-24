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

package com.stevenpg.instancio.postgis.jdbc.internal.generator.pg;

import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.geometric.*;

import static org.junit.jupiter.api.Assertions.*;

class OtherPostgresGeometricGeneratorsTest {

    @Test
    void shouldGenerateLseg() {
        var lseg = new PGLsegGenerator()
                .xRange(10, 10)
                .yRange(20, 20)
                .generate(null);
        assertNotNull(lseg);
        assertEquals(10, lseg.point[0].x);
        assertEquals(10, lseg.point[1].x);
        assertEquals(20, lseg.point[0].y);
        assertEquals(20, lseg.point[1].y);
    }

    @Test
    void shouldGenerateLine() {
        var line = new PGLineGenerator()
                .xRange(10, 10)
                .yRange(20, 20)
                .generate(null);
        assertNotNull(line);
        // PGline is represented as Ax + By + C = 0
        // The generator creates it from two points
    }

    @Test
    void shouldGeneratePath() {
        var path = new PGPathGenerator()
                .vertices(5, 5)
                .xRange(10, 10)
                .yRange(20, 20)
                .open(false)
                .generate(null);
        assertNotNull(path);
        assertEquals(5, path.points.length);
        assertFalse(path.open);
        for (var p : path.points) {
            assertEquals(10, p.x);
            assertEquals(20, p.y);
        }
    }

    @Test
    void shouldGeneratePolygon() {
        var poly = new PGPolygonGenerator()
                .vertices(3, 3)
                .xRange(10, 10)
                .yRange(20, 20)
                .generate(null);
        assertNotNull(poly);
        assertEquals(3, poly.points.length);
        for (var p : poly.points) {
            assertEquals(10, p.x);
            assertEquals(20, p.y);
        }
    }

    @Test
    void shouldGenerateBox() {
        var box = new PGBoxGenerator()
                .xRange(10, 10)
                .yRange(20, 20)
                .generate(null);
        assertNotNull(box);
        assertEquals(10, box.point[0].x);
        assertEquals(10, box.point[1].x);
        assertEquals(20, box.point[0].y);
        assertEquals(20, box.point[1].y);
    }

    @Test
    void shouldGenerateCircle() {
        var circle = new PGCircleGenerator()
                .radiusRange(5, 5)
                .xRange(10, 10)
                .yRange(20, 20)
                .generate(null);
        assertNotNull(circle);
        assertEquals(5, circle.radius);
        assertEquals(10, circle.center.x);
        assertEquals(20, circle.center.y);
    }

    @RepeatedTest(2)
    void instancioShouldCreateViaSPI() {
        assertNotNull(Instancio.create(PGpoint.class));
        assertNotNull(Instancio.create(PGlseg.class));
        assertNotNull(Instancio.create(PGline.class));
        assertNotNull(Instancio.create(PGpath.class));
        assertNotNull(Instancio.create(PGpolygon.class));
        assertNotNull(Instancio.create(PGbox.class));
        assertNotNull(Instancio.create(PGcircle.class));
    }
}
