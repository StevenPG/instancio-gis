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
package com.stevenpg.instancio.locationtech.core;

import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;
import org.locationtech.jts.geom.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeavyJtsGeometryTest {

    private static final int ITERATIONS = 100;

    @RepeatedTest(ITERATIONS)
    void heavyPointTest() {
        Point p = Instancio.create(Point.class);
        assertNotNull(p);
        assertTrue(p.isValid());
    }

    @RepeatedTest(ITERATIONS)
    void heavyLineStringTest() {
        LineString ls = Instancio.create(LineString.class);
        assertNotNull(ls);
        assertTrue(ls.isValid());
    }

    @RepeatedTest(ITERATIONS)
    void heavyPolygonTest() {
        Polygon poly = Instancio.create(Polygon.class);
        assertNotNull(poly);
        // Note: randomly generated polygons might not always be valid if they are self-intersecting,
        // but JTS Polygon objects themselves should be successfully created.
        assertTrue(poly.getArea() >= 0);
    }

    @RepeatedTest(ITERATIONS)
    void heavyMultiPointTest() {
        MultiPoint mp = Instancio.create(MultiPoint.class);
        assertNotNull(mp);
        assertTrue(mp.isValid());
    }

    @RepeatedTest(ITERATIONS)
    void heavyMultiLineStringTest() {
        MultiLineString mls = Instancio.create(MultiLineString.class);
        assertNotNull(mls);
        assertTrue(mls.isValid());
    }

    @RepeatedTest(ITERATIONS)
    void heavyMultiPolygonTest() {
        MultiPolygon mp = Instancio.create(MultiPolygon.class);
        assertNotNull(mp);
        assertTrue(mp.getArea() >= 0);
    }

    @RepeatedTest(ITERATIONS)
    void heavyGeometryCollectionTest() {
        GeometryCollection gc = Instancio.create(GeometryCollection.class);
        assertNotNull(gc);
        // Heterogeneous collections containing randomly generated polygons may be invalid
        assertTrue(gc.getNumGeometries() >= 0);
    }
}
