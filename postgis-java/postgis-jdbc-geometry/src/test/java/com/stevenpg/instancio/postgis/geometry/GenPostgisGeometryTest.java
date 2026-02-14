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

import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class GenPostgisGeometryTest {

    @RepeatedTest(10)
    void geometry() {
        var generator = GenPostgisGeometry.geometry();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.geometry.Geometry.class, result);
    }

    @RepeatedTest(10)
    void point() {
        var generator = GenPostgisGeometry.point();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.geometry.Point.class, result);
    }

    @RepeatedTest(10)
    void lineString() {
        var generator = GenPostgisGeometry.lineString();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.geometry.LineString.class, result);
    }

    @RepeatedTest(10)
    void polygon() {
        var generator = GenPostgisGeometry.polygon();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.geometry.Polygon.class, result);
    }

    @RepeatedTest(10)
    void multiPoint() {
        var generator = GenPostgisGeometry.multiPoint();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.geometry.MultiPoint.class, result);
    }

    @RepeatedTest(10)
    void multiLineString() {
        var generator = GenPostgisGeometry.multiLineString();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.geometry.MultiLineString.class, result);
    }

    @RepeatedTest(10)
    void multiPolygon() {
        var generator = GenPostgisGeometry.multiPolygon();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.geometry.MultiPolygon.class, result);
    }

    @RepeatedTest(10)
    void geometryCollection() {
        var generator = GenPostgisGeometry.geometryCollection();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.geometry.GeometryCollection.class, result);
    }

    @RepeatedTest(10)
    void pgGeometry() {
        var generator = GenPostgisGeometry.pgGeometry();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.PGgeometry.class, result);
    }
}
