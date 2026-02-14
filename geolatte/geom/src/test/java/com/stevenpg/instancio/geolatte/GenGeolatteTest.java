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

package com.stevenpg.instancio.geolatte;

import org.geolatte.geom.G2D;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenGeolatteTest {

    @RepeatedTest(10)
    void point() {
        var generator = GenGeolatte.point();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.geolatte.geom.Point.class, result);

        // Validate coordinates fall within default WGS84 bounds
        var position = (G2D) result.getPosition();
        assertTrue(position.getLon() >= -180 && position.getLon() <= 180,
                "Longitude must be within [-180, 180], was: " + position.getLon());
        assertTrue(position.getLat() >= -90 && position.getLat() <= 90,
                "Latitude must be within [-90, 90], was: " + position.getLat());
    }

    @RepeatedTest(10)
    void lineString() {
        var generator = GenGeolatte.lineString();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.geolatte.geom.LineString.class, result);

        // A valid line string must have at least 2 positions
        assertTrue(result.getNumPositions() >= 2,
                "LineString must have >= 2 positions, had: " + result.getNumPositions());
    }

    @RepeatedTest(10)
    void linearRing() {
        var generator = GenGeolatte.linearRing();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.geolatte.geom.LinearRing.class, result);

        // A valid linear ring must have at least 4 positions
        assertTrue(result.getNumPositions() >= 4,
                "LinearRing must have >= 4 positions, had: " + result.getNumPositions());
    }

    @RepeatedTest(10)
    void polygon() {
        var generator = GenGeolatte.polygon();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.geolatte.geom.Polygon.class, result);

        // A polygon must have an exterior ring
        assertNotNull(result.getExteriorRing(), "Polygon must have an exterior ring");
        assertTrue(result.getExteriorRing().getNumPositions() >= 4,
                "Exterior ring must have >= 4 positions, had: " + result.getExteriorRing().getNumPositions());
    }

    @RepeatedTest(10)
    void multiPoint() {
        var generator = GenGeolatte.multiPoint();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.geolatte.geom.MultiPoint.class, result);

        // A multi-point must contain at least 2 geometries
        assertTrue(result.getNumGeometries() >= 2,
                "MultiPoint must have >= 2 geometries, had: " + result.getNumGeometries());
    }

    @RepeatedTest(10)
    void multiLineString() {
        var generator = GenGeolatte.multiLineString();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.geolatte.geom.MultiLineString.class, result);

        // A multi-line string must contain at least 2 geometries
        assertTrue(result.getNumGeometries() >= 2,
                "MultiLineString must have >= 2 geometries, had: " + result.getNumGeometries());
    }

    @RepeatedTest(10)
    void multiPolygon() {
        var generator = GenGeolatte.multiPolygon();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.geolatte.geom.MultiPolygon.class, result);

        // A multi-polygon must contain at least 2 geometries
        assertTrue(result.getNumGeometries() >= 2,
                "MultiPolygon must have >= 2 geometries, had: " + result.getNumGeometries());
    }

    @RepeatedTest(10)
    void geometryCollection() {
        var generator = GenGeolatte.geometryCollection();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.geolatte.geom.GeometryCollection.class, result);

        // A geometry collection must contain at least 2 geometries
        assertTrue(result.getNumGeometries() >= 2,
                "GeometryCollection must have >= 2 geometries, had: " + result.getNumGeometries());
    }
}
