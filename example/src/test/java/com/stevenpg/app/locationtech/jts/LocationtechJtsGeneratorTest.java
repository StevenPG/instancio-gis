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

package com.stevenpg.app.locationtech.jts;

import com.stevenpg.instancio.locationtech.core.GenLocationtechJtsCore;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocationtechJtsGeneratorTest {

    @Test
    void shouldGeneratePointWithInstancio() {
        Point p = Instancio.create(Point.class);
        assertNotNull(p);
    }

    @Test
    void shouldGenerateLineStringWithInstancio() {
        LineString ls = Instancio.create(LineString.class);
        assertNotNull(ls);
    }

    @Test
    void shouldGeneratePolygonWithInstancio() {
        Polygon poly = Instancio.create(Polygon.class);
        assertNotNull(poly);
    }

    @Test
    void shouldGenerateMultiPointWithInstancio() {
        MultiPoint mp = Instancio.create(MultiPoint.class);
        assertNotNull(mp);
    }

    @Test
    void shouldGenerateMultiLineStringWithInstancio() {
        MultiLineString mls = Instancio.create(MultiLineString.class);
        assertNotNull(mls);
    }

    @Test
    void shouldGenerateMultiPolygonWithInstancio() {
        MultiPolygon mp = Instancio.create(MultiPolygon.class);
        assertNotNull(mp);
    }

    @Test
    void shouldGenerateGeometryCollectionWithInstancio() {
        GeometryCollection gc = Instancio.create(GeometryCollection.class);
        assertNotNull(gc);
    }

    @Test
    void shouldGeneratePointWithGenerator() {
        Point p = GenLocationtechJtsCore.point().generate(null);
        assertNotNull(p);
    }

    @Test
    void shouldGeneratePolygonWithGenerator() {
        Polygon p = GenLocationtechJtsCore.polygon().generate(null);
        assertNotNull(p);
    }
}
