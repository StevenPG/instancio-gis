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
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometryCollectionGeneratorTest {

    @RepeatedTest(3)
    void createWithInstancio() {
        var gc = Instancio.create(GeometryCollection.class);
        assertNotNull(gc);
    }

    @Test
    void withinEnvelope() {
        var env = new Envelope(0, 10, 0, 10);
        var gc = new GeometryCollectionGenerator()
                .within(env)
                .generate(new DefaultRandom());

        assertNotNull(gc);
        var cEnv = gc.getEnvelopeInternal();
        assertTrue(cEnv.getMinX() >= env.getMinX());
        assertTrue(cEnv.getMaxX() <= env.getMaxX());
        assertTrue(cEnv.getMinY() >= env.getMinY());
        assertTrue(cEnv.getMaxY() <= env.getMaxY());

        for (int i = 0; i < gc.getNumGeometries(); i++) {
            var g = gc.getGeometryN(i);
            var gEnv = g.getEnvelopeInternal();
            assertTrue(gEnv.getMinX() >= env.getMinX());
            assertTrue(gEnv.getMaxX() <= env.getMaxX());
            assertTrue(gEnv.getMinY() >= env.getMinY());
            assertTrue(gEnv.getMaxY() <= env.getMaxY());
        }
    }

    @Test
    void explicitMultiPoint() {
        var mp = new MultiPointGenerator().length(3).generate(new DefaultRandom());
        var result = new GeometryCollectionGenerator().multiPoint(mp).generate(new DefaultRandom());
        assertSame(mp, result);
    }

    @Test
    void componentsListBuildsHeterogeneousCollection() {
        var rand = new DefaultRandom();
        var p = new PointGenerator().generate(rand);
        var ls = new LineStringGenerator().length(2).generate(rand);
        var poly = new PolygonGenerator().generate(rand);

        var result = new GeometryCollectionGenerator()
                .geometryFactory(new GeometryFactory())
                .geometries(List.of(p, ls, poly))
                .generate(rand);

        assertNotNull(result);
        assertEquals(3, result.getNumGeometries());
    }

    @Test
    void lengthAppliesToHeterogeneousOnly() {
        var gc = new GeometryCollectionGenerator()
                .length(4)
                .generate(new DefaultRandom());

        // Should be a general GeometryCollection (not one of the Multi* subtypes)
        assertFalse(gc instanceof MultiPoint);
        assertFalse(gc instanceof MultiLineString);
        assertFalse(gc instanceof MultiPolygon);
        assertEquals(4, gc.getNumGeometries());
    }

    @Test
    void explicitGeometryCollectionReturnedAsIs() {
        var rand = new DefaultRandom();
        var p = new PointGenerator().generate(rand);
        var ls = new LineStringGenerator().length(2).generate(rand);
        var poly = new PolygonGenerator().generate(rand);
        var gf = new GeometryFactory();
        var provided = new GeometryCollection(new Geometry[]{p, ls, poly}, gf);

        var result = new GeometryCollectionGenerator().geometryCollection(provided).generate(rand);
        assertSame(provided, result);
    }
}
