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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiPointGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var multiPoint = Instancio.create(MultiPoint.class);
        assertNotNull(multiPoint);
        assertTrue(multiPoint.getNumGeometries() >= 2);
        assertTrue(multiPoint.getNumGeometries() <= 10);
    }

    @Test
    void points() {
        var geometryFactory = new GeometryFactory();
        var point1 = geometryFactory.createPoint(new Coordinate(-75d, 40d));
        var point2 = geometryFactory.createPoint(new Coordinate(-74d, 41d));
        List<Point> points = Arrays.asList(point1, point2);

        var result = new MultiPointGenerator()
                .points(points)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(2, result.getNumGeometries());
        assertEquals(-75d, result.getGeometryN(0).getCoordinate().getX());
        assertEquals(40d, result.getGeometryN(0).getCoordinate().getY());
        assertEquals(-74d, result.getGeometryN(1).getCoordinate().getX());
        assertEquals(41d, result.getGeometryN(1).getCoordinate().getY());
    }

    @Test
    void length() {
        var result = new MultiPointGenerator()
                .length(5)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(5, result.getNumGeometries());
    }

    @Test
    void geometryFactory() {
        var customGeometryFactory = new GeometryFactory();
        var result = new MultiPointGenerator()
                .geometryFactory(customGeometryFactory)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(customGeometryFactory, result.getFactory());
    }

    @Test
    void within() {
        var result = new MultiPointGenerator()
                .within(new Envelope(0, 10, 0, 10))
                .generate(new DefaultRandom());

        assertNotNull(result);
        for (int i = 0; i < result.getNumGeometries(); i++) {
            var point = (Point) result.getGeometryN(i);
            assertTrue(point.getX() >= 0 && point.getX() <= 10);
            assertTrue(point.getY() >= 0 && point.getY() <= 10);
        }
    }

    @Test
    void generate() {
        var result = new MultiPointGenerator().generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getNumGeometries() >= 2);
        assertTrue(result.getNumGeometries() <= 10);
    }
}