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

import org.instancio.Random;
import org.instancio.support.DefaultRandom;

import net.postgis.jdbc.geometry.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometryGeneratorsTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGeneratePoint() {
        var gen = new PointGenerator().xRange(10, 10).yRange(20, 20);
        Point p = gen.generate(random);
        assertNotNull(p);
        assertEquals(10, p.x);
        assertEquals(20, p.y);
    }

    @Test
    void shouldGenerateLineString() {
        var gen = new LineStringGenerator().xRange(10, 10).yRange(20, 20);
        LineString ls = gen.generate(random);
        assertNotNull(ls);
        assertTrue(ls.numPoints() >= 2);
        for (int i = 0; i < ls.numPoints(); i++) {
            assertEquals(10, ls.getPoint(i).x);
            assertEquals(20, ls.getPoint(i).y);
        }
    }

    @Test
    void shouldGeneratePolygon() {
        var gen = new PolygonGenerator().xRange(10, 10).yRange(20, 20);
        Polygon poly = gen.generate(random);
        assertNotNull(poly);
        assertTrue(poly.numRings() >= 1);
        LinearRing ring = poly.getRing(0);
        for (int i = 0; i < ring.numPoints(); i++) {
            assertEquals(10, ring.getPoint(i).x);
            assertEquals(20, ring.getPoint(i).y);
        }
    }

    @Test
    void shouldGenerateMultiPoint() {
        var gen = new MultiPointGenerator().xRange(10, 10).yRange(20, 20);
        MultiPoint mp = gen.generate(random);
        assertNotNull(mp);
        assertTrue(mp.numPoints() >= 1);
        for (int i = 0; i < mp.numPoints(); i++) {
            assertEquals(10, mp.getPoint(i).x);
            assertEquals(20, mp.getPoint(i).y);
        }
    }

    @Test
    void shouldGenerateMultiLineString() {
        var gen = new MultiLineStringGenerator().xRange(10, 10).yRange(20, 20);
        MultiLineString mls = gen.generate(random);
        assertNotNull(mls);
        assertTrue(mls.numLines() >= 1);
        for (int i = 0; i < mls.numLines(); i++) {
            LineString ls = mls.getLine(i);
            for (int j = 0; j < ls.numPoints(); j++) {
                assertEquals(10, ls.getPoint(j).x);
                assertEquals(20, ls.getPoint(j).y);
            }
        }
    }

    @Test
    void shouldGenerateMultiPolygon() {
        var gen = new MultiPolygonGenerator().xRange(10, 10).yRange(20, 20);
        MultiPolygon mp = gen.generate(random);
        assertNotNull(mp);
        assertTrue(mp.numPolygons() >= 1);
    }

    @Test
    void shouldGenerateGeometryCollection() {
        var gen = new GeometryCollectionGenerator().xRange(10, 10).yRange(20, 20);
        GeometryCollection gc = gen.generate(random);
        assertNotNull(gc);
        assertTrue(gc.numGeoms() >= 1);
    }

    @Test
    void shouldGenerateGeometry() {
        var gen = new GeometryGenerator().xRange(10, 10).yRange(20, 20).zRange(0, 0);
        Geometry g = gen.generate(random);
        assertNotNull(g);
    }

    @Test
    void instancioShouldCreateGeometryAndPointViaSPI() {
        Geometry g = Instancio.create(Geometry.class);
        assertNotNull(g);
        Point p = Instancio.create(Point.class);
        assertNotNull(p);
    }
}
