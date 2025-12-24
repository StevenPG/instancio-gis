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
package com.stevenpg.instancio.postgis.geometry;

import net.postgis.jdbc.geometry.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeavyGeometryTest {

    private static final int ITERATIONS = 100;

    @RepeatedTest(ITERATIONS)
    void heavyPointTest() {
        Point p = Instancio.create(Point.class);
        assertNotNull(p);
        assertTrue(p.x >= -180 && p.x <= 180);
        assertTrue(p.y >= -90 && p.y <= 90);
    }

    @RepeatedTest(ITERATIONS)
    void heavyLineStringTest() {
        LineString ls = Instancio.create(LineString.class);
        assertNotNull(ls);
        assertTrue(ls.numPoints() >= 2);
    }

    @RepeatedTest(ITERATIONS)
    void heavyPolygonTest() {
        Polygon poly = Instancio.create(Polygon.class);
        assertNotNull(poly);
        assertTrue(poly.numPoints() >= 3);
    }

    @RepeatedTest(ITERATIONS)
    void heavyMultiPointTest() {
        MultiPoint mp = Instancio.create(MultiPoint.class);
        assertNotNull(mp);
        assertTrue(mp.numPoints() >= 1);
    }

    @RepeatedTest(ITERATIONS)
    void heavyMultiLineStringTest() {
        MultiLineString mls = Instancio.create(MultiLineString.class);
        assertNotNull(mls);
        assertTrue(mls.numGeoms() >= 1);
    }

    @RepeatedTest(ITERATIONS)
    void heavyMultiPolygonTest() {
        MultiPolygon mp = Instancio.create(MultiPolygon.class);
        assertNotNull(mp);
        assertTrue(mp.numGeoms() >= 1);
    }

    @RepeatedTest(ITERATIONS)
    void heavyGeometryCollectionTest() {
        GeometryCollection gc = Instancio.create(GeometryCollection.class);
        assertNotNull(gc);
        assertTrue(gc.numGeoms() >= 1);
    }

    @RepeatedTest(ITERATIONS)
    void heavyPGgeometryTest() {
        net.postgis.jdbc.PGgeometry pg = Instancio.create(net.postgis.jdbc.PGgeometry.class);
        assertNotNull(pg);
        assertNotNull(pg.getGeometry());
    }
}
