package com.stevenpg.app.postgis.geometry;
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

import com.stevenpg.instancio.postgis.geometry.GenPostgisGeometry;
import net.postgis.jdbc.geometry.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PostgisGeometryGeneratorTest {

    @Test
    void shouldGeneratePostgisGeometryWithInstancio() {
        Geometry g = Instancio.create(Geometry.class);
        assertNotNull(g);
    }

    @Test
    void shouldGeneratePostgisPointWithInstancio() {
        Point p = Instancio.create(Point.class);
        assertNotNull(p);
        assertNotEquals(0, p.x);
        assertNotEquals(0, p.y);
    }

    @Test
    void shouldGeneratePostgisLineStringWithInstancio() {
        LineString ls = Instancio.create(LineString.class);
        assertNotNull(ls);
        assertTrue(ls.numPoints() >= 2);
    }

    @Test
    void shouldGeneratePostgisPolygonWithInstancio() {
        Polygon poly = Instancio.create(Polygon.class);
        assertNotNull(poly);
        assertTrue(poly.numPoints() >= 3);
    }

    @Test
    void shouldGeneratePostgisMultiPointWithInstancio() {
        MultiPoint mp = Instancio.create(MultiPoint.class);
        assertNotNull(mp);
        assertTrue(mp.numPoints() >= 1);
    }

    @Test
    void shouldGeneratePostgisMultiLineStringWithInstancio() {
        MultiLineString mls = Instancio.create(MultiLineString.class);
        assertNotNull(mls);
        assertTrue(mls.numGeoms() >= 1);
    }

    @Test
    void shouldGeneratePostgisMultiPolygonWithInstancio() {
        MultiPolygon mp = Instancio.create(MultiPolygon.class);
        assertNotNull(mp);
        assertTrue(mp.numGeoms() >= 1);
    }

    @Test
    void shouldGeneratePostgisGeometryCollectionWithInstancio() {
        GeometryCollection gc = Instancio.create(GeometryCollection.class);
        assertNotNull(gc);
        assertTrue(gc.numGeoms() >= 1);
    }

    @Test
    void shouldGeneratePostgisPointWithGenerator() {
        Point p = GenPostgisGeometry.point().xRange(10, 10).yRange(20, 20).generate(null);
        assertNotNull(p);
        assertEquals(10, p.x);
        assertEquals(20, p.y);
    }
}
