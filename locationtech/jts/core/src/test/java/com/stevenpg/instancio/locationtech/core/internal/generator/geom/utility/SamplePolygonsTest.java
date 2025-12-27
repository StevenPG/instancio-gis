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

package com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SamplePolygonsTest {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    void pointShouldBeWithinCentralPark() {
        Point point = geometryFactory.createPoint(new Coordinate(-73.965355, 40.782865));
        assertTrue(SamplePolygons.CENTRAL_PARK_NYC_USA.contains(point), "Point should be within Central Park");
    }

    @Test
    void pointShouldBeWithinNiihau() {
        Point point = geometryFactory.createPoint(new Coordinate(-160.15, 21.9));
        assertTrue(SamplePolygons.NIIHAU_HAWAII_USA.contains(point), "Point should be within Niihau");
    }

    @Test
    void pointShouldBeWithinBuckinghamPalace() {
        Point point = geometryFactory.createPoint(new Coordinate(-0.1425, 51.501));
        assertTrue(SamplePolygons.BUCKINGHAM_PALACE_UK.contains(point), "Point should be within Buckingham Palace");
    }

    @Test
    void pointShouldBeWithinRomanColosseum() {
        Point point = geometryFactory.createPoint(new Coordinate(12.4923, 41.8902));
        assertTrue(SamplePolygons.ROMAN_COLOSSEUM_ITALY.contains(point), "Point should be within Roman Colosseum");
    }
}