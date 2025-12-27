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

import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class GenLocationtechJtsCoreTest {

    @RepeatedTest(10)
    void coordinateArraySequence() {
        var generator = GenLocationtechJtsCore.coordinateArraySequence();
        assertNotNull(generator);

        var result = generator.generate(null);

        assertNotNull(result);
        for (int i = 0; i < result.size(); i++) {
            var coordinate = result.getCoordinate(i);
            assertNotNull(coordinate);
            assertInstanceOf(org.locationtech.jts.geom.Coordinate.class, coordinate);
            assertTrue(coordinate.x >= -180 && coordinate.x <= 180);
            assertTrue(coordinate.y >= -90 && coordinate.y <= 90);
        }
    }

    @RepeatedTest(10)
    void coordinate() {
        var generator = GenLocationtechJtsCore.coordinate();
        assertNotNull(generator);

        var result = generator.generate(null);

        assertInstanceOf(org.locationtech.jts.geom.Coordinate.class, result);
        assertNotNull(result);
        assertTrue(result.x >= -180 && result.x <= 180);
        assertTrue(result.y >= -90 && result.y <= 90);
    }

    @RepeatedTest(10)
    void coordinateXY() {
        var generator = GenLocationtechJtsCore.coordinateXY();
        assertNotNull(generator);

        var result = generator.generate(null);

        assertInstanceOf(org.locationtech.jts.geom.CoordinateXY.class, result);
        assertNotNull(result);
        assertTrue(result.x >= -180 && result.x <= 180);
        assertTrue(result.y >= -90 && result.y <= 90);
    }

    @RepeatedTest(10)
    void coordinateXYM() {
        var generator = GenLocationtechJtsCore.coordinateXYM();
        assertNotNull(generator);

        var result = generator.generate(null);

        assertInstanceOf(org.locationtech.jts.geom.CoordinateXYM.class, result);
        assertNotNull(result);
        assertTrue(result.x >= -180 && result.x <= 180);
        assertTrue(result.y >= -90 && result.y <= 90);
    }

    @RepeatedTest(10)
    void coordinateXYZM() {
        var generator = GenLocationtechJtsCore.coordinateXYZM();
        assertNotNull(generator);

        var result = generator.generate(null);

        assertInstanceOf(org.locationtech.jts.geom.CoordinateXYZM.class, result);
        assertNotNull(result);
        assertTrue(result.x >= -180 && result.x <= 180);
        assertTrue(result.y >= -90 && result.y <= 90);
    }

    @RepeatedTest(10)
    void lineString() {
        var generator = GenLocationtechJtsCore.lineString();
        assertNotNull(generator);

        var linestring = generator.generate(new DefaultRandom());

        assertNotNull(linestring);
        assertInstanceOf(org.locationtech.jts.geom.LineString.class, linestring);
        for( var coordinate : linestring.getCoordinates()) {
            assertTrue(coordinate.x >= -180 && coordinate.x <= 180);
            assertTrue(coordinate.y >= -90 && coordinate.y <= 90);
        }
    }

    @RepeatedTest(10)
    void linearRing() {
        var generator = GenLocationtechJtsCore.linearRing();
        assertNotNull(generator);

        var linearRing = generator.generate(new DefaultRandom());

        assertNotNull(linearRing);
        assertInstanceOf(org.locationtech.jts.geom.LinearRing.class, linearRing);
        assertTrue(linearRing.isClosed());
        assertTrue(linearRing.getCoordinates().length >= 4);
        for( var coordinate : linearRing.getCoordinates()) {
            assertTrue(coordinate.x >= -180 && coordinate.x <= 180);
            assertTrue(coordinate.y >= -90 && coordinate.y <= 90);
        }
    }

    @RepeatedTest(10)
    void point() {
        var generator = GenLocationtechJtsCore.point();
        assertNotNull(generator);

        var coordinate = generator.generate(new DefaultRandom());

        assertNotNull(coordinate);
        assertInstanceOf(org.locationtech.jts.geom.Point.class, coordinate);
        assertTrue(coordinate.getCoordinate().x >= -180 && coordinate.getCoordinate().x <= 180);
        assertTrue(coordinate.getCoordinate().y >= -90 && coordinate.getCoordinate().y <= 90);
    }

    @RepeatedTest(10)
    void multiPoint() {
        var generator = GenLocationtechJtsCore.multiPoint();
        assertNotNull(generator);

        var multipoint = generator.generate(new DefaultRandom());

        assertNotNull(multipoint);
        assertInstanceOf(org.locationtech.jts.geom.MultiPoint.class, multipoint);
        for (int i = 0; i < multipoint.getNumGeometries(); i++) {
            var point = (org.locationtech.jts.geom.Point) multipoint.getGeometryN(i);
            assertTrue(point.getCoordinate().x >= -180 && point.getCoordinate().x <= 180);
            assertTrue(point.getCoordinate().y >= -90 && point.getCoordinate().y <= 90);
        }
    }

    @RepeatedTest(10)
    void multiLineString() {
        var generator = GenLocationtechJtsCore.multiLineString();
        assertNotNull(generator);

        var multilinestring = generator.generate(new DefaultRandom());

        assertNotNull(multilinestring);
        assertInstanceOf(org.locationtech.jts.geom.MultiLineString.class, multilinestring);
        for (int i = 0; i < multilinestring.getNumGeometries(); i++) {
            var linestring = (org.locationtech.jts.geom.LineString) multilinestring.getGeometryN(i);
            for (var coordinate : linestring.getCoordinates()) {
                assertTrue(coordinate.x >= -180 && coordinate.x <= 180);
                assertTrue(coordinate.y >= -90 && coordinate.y <= 90);
            }
        }
    }

    @RepeatedTest(10)
    void polygon() {
        var generator = GenLocationtechJtsCore.polygon();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.locationtech.jts.geom.Polygon.class, result);
    }

    @RepeatedTest(10)
    void multiPolygon() {
        var generator = GenLocationtechJtsCore.multiPolygon();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.locationtech.jts.geom.MultiPolygon.class, result);
    }

    @RepeatedTest(10)
    void geometryCollection() {
        var generator = GenLocationtechJtsCore.geometryCollection();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.locationtech.jts.geom.GeometryCollection.class, result);
    }

    @RepeatedTest(10)
    void envelope() {
        var generator = GenLocationtechJtsCore.envelope();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.locationtech.jts.geom.Envelope.class, result);
    }

    @RepeatedTest(10)
    void geometry() {
        var generator = GenLocationtechJtsCore.geometry();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.locationtech.jts.geom.Geometry.class, result);
    }
}