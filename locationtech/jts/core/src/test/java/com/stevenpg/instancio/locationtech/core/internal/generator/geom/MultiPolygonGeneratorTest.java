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

import org.instancio.Random;
import org.instancio.support.DefaultRandom;

import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiPolygonGeneratorTest {

    private final Random random = new DefaultRandom();

    @RepeatedTest(5)
    void create() {
        var multiPolygon = Instancio.create(MultiPolygon.class);
        assertNotNull(multiPolygon);
        assertTrue(multiPolygon.getNumGeometries() >= 1);
        assertTrue(multiPolygon.getNumGeometries() <= 5);
    }

    @Test
    void polygons() {
        var geometryFactory = new GeometryFactory();
        var coords1 = new Coordinate[]{
                new Coordinate(0, 0), new Coordinate(1, 0),
                new Coordinate(1, 1), new Coordinate(0, 1), new Coordinate(0, 0)
        };
        var coords2 = new Coordinate[]{
                new Coordinate(2, 2), new Coordinate(3, 2),
                new Coordinate(3, 3), new Coordinate(2, 3), new Coordinate(2, 2)
        };
        var ring1 = geometryFactory.createLinearRing(coords1);
        var ring2 = geometryFactory.createLinearRing(coords2);
        var polygon1 = geometryFactory.createPolygon(ring1);
        var polygon2 = geometryFactory.createPolygon(ring2);
        List<Polygon> polygons = Arrays.asList(polygon1, polygon2);

        var result = new MultiPolygonGenerator()
                .polygons(polygons)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(2, result.getNumGeometries());
        assertEquals(polygon1, result.getGeometryN(0));
        assertEquals(polygon2, result.getGeometryN(1));
    }

    @Test
    void length() {
        var result = new MultiPolygonGenerator()
                .length(3)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(3, result.getNumGeometries());
    }

    @Test
    void geometryFactory() {
        var customGeometryFactory = new GeometryFactory();
        var result = new MultiPolygonGenerator()
                .geometryFactory(customGeometryFactory)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(customGeometryFactory, result.getFactory());
    }

    @Test
    void within() {
        var result = new MultiPolygonGenerator()
                .within(new Envelope(0, 10, 0, 10))
                .generate(new DefaultRandom());

        assertNotNull(result);
        for (int i = 0; i < result.getNumGeometries(); i++) {
            var polygon = (Polygon) result.getGeometryN(i);
            var envelope = polygon.getEnvelopeInternal();
            assertTrue(envelope.getMinX() >= 0 && envelope.getMaxX() <= 10);
            assertTrue(envelope.getMinY() >= 0 && envelope.getMaxY() <= 10);
        }
    }

    @Test
    void generate() {
        var result = new MultiPolygonGenerator().generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getNumGeometries() >= 1);
        assertTrue(result.getNumGeometries() <= 5);
    }

    @Test
    void generateWithNullRandom() {
        var result = new MultiPolygonGenerator().generate(random);

        assertNotNull(result);
        assertTrue(result.getNumGeometries() >= 1);
        assertTrue(result.getNumGeometries() <= 5);
    }

    @Test
    void generateWithLengthAndNullRandom() {
        var result = new MultiPolygonGenerator()
                .length(2)
                .generate(random);

        assertNotNull(result);
        assertEquals(2, result.getNumGeometries());
    }

    @Test
    void generateWithEnvelopeAndNullRandom() {
        var result = new MultiPolygonGenerator()
                .within(new Envelope(0, 5, 0, 5))
                .generate(random);

        assertNotNull(result);
        for (int i = 0; i < result.getNumGeometries(); i++) {
            var polygon = (Polygon) result.getGeometryN(i);
            var envelope = polygon.getEnvelopeInternal();
            assertTrue(envelope.getMinX() >= 0 && envelope.getMaxX() <= 5);
            assertTrue(envelope.getMinY() >= 0 && envelope.getMaxY() <= 5);
        }
    }

    @Test
    void generateWithPolygonsAndGeometryFactory() {
        var customGeometryFactory = new GeometryFactory();
        var geometryFactory = new GeometryFactory();
        var coords = new Coordinate[]{
                new Coordinate(0, 0), new Coordinate(1, 0),
                new Coordinate(1, 1), new Coordinate(0, 1), new Coordinate(0, 0)
        };
        var ring = geometryFactory.createLinearRing(coords);
        var polygon = geometryFactory.createPolygon(ring);
        List<Polygon> polygons = List.of(polygon);

        var result = new MultiPolygonGenerator()
                .polygons(polygons)
                .geometryFactory(customGeometryFactory)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(1, result.getNumGeometries());
        assertEquals(customGeometryFactory, result.getFactory());
    }

    @Test
    void chainedMethods() {
        var customGeometryFactory = new GeometryFactory();

        var result = new MultiPolygonGenerator()
                .length(2)
                .geometryFactory(customGeometryFactory)
                .within(new Envelope(0, 10, 0, 10))
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(2, result.getNumGeometries());
        assertEquals(customGeometryFactory, result.getFactory());

        for (int i = 0; i < result.getNumGeometries(); i++) {
            var polygon = (Polygon) result.getGeometryN(i);
            var envelope = polygon.getEnvelopeInternal();
            assertTrue(envelope.getMinX() >= 0 && envelope.getMaxX() <= 10);
            assertTrue(envelope.getMinY() >= 0 && envelope.getMaxY() <= 10);
        }
    }

    @Test
    void constructorTest() {
        var generator = new MultiPolygonGenerator();
        assertNotNull(generator);
    }

    @Test
    void generateMinLength() {
        var result = new MultiPolygonGenerator()
                .length(1)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(1, result.getNumGeometries());
    }
}
