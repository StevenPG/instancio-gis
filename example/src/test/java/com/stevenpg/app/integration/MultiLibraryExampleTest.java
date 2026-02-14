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

package com.stevenpg.app.integration;

import com.stevenpg.instancio.esri.GenEsriGeometry;
import com.stevenpg.instancio.geolatte.GenGeolatte;
import com.stevenpg.instancio.h3.GenH3;
import com.stevenpg.instancio.locationtech.core.GenLocationtechJtsCore;
import com.stevenpg.instancio.postgis.jdbc.GenPostgisJdbc;
import com.stevenpg.instancio.proj4j.GenProj4j;
import com.stevenpg.instancio.spatial4j.GenSpatial4j;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;
import org.locationtech.spatial4j.shape.Circle;
import org.postgresql.geometric.PGpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Demonstrates using generators from multiple GIS libraries in a single test class.
 *
 * <p>Each instancio-gis module provides a {@code Gen*} facade with static factory methods
 * that return fluent generator builders. All generators accept an {@link org.instancio.Random}
 * instance and produce fully populated GIS objects.</p>
 *
 * <p>This test shows common patterns across JTS, PostGIS JDBC, H3, ESRI, Spatial4j,
 * Proj4j, and Geolatte libraries.</p>
 */
class MultiLibraryExampleTest {

    private final Random random = new DefaultRandom();

    // -- JTS (Locationtech) --

    @Test
    void generateJtsPointWithCustomBounds() {
        // JTS point constrained to New York City area
        Envelope nycBounds = new Envelope(-74.26, -73.70, 40.49, 40.92);

        Point point = GenLocationtechJtsCore.point()
                .within(nycBounds)
                .generate(random);

        assertTrue(nycBounds.contains(point.getCoordinate()));
    }

    @Test
    void generateJtsPolygonWithHoles() {
        // Generate a polygon with explicit vertex count and interior holes
        Polygon polygon = GenLocationtechJtsCore.polygon()
                .vertices(6)
                .holes(2)
                .within(new Envelope(-10, 10, -10, 10))
                .generate(random);

        assertNotNull(polygon);
        assertEquals(2, polygon.getNumInteriorRing());
    }

    // -- PostGIS JDBC --

    @Test
    void generatePostgisPointWithRange() {
        // PostGIS PGpoint with longitude/latitude ranges for San Francisco
        PGpoint pgPoint = GenPostgisJdbc.pgPoint()
                .xRange(-122.5, -122.3)
                .yRange(37.7, 37.8)
                .generate(random);

        assertTrue(pgPoint.x >= -122.5 && pgPoint.x <= -122.3);
        assertTrue(pgPoint.y >= 37.7 && pgPoint.y <= 37.8);
    }

    // -- H3 (Uber) --

    @Test
    void generateH3IndexForArea() {
        // Generate an H3 hexagonal cell index for the London area
        Long h3Index = GenH3.h3Index()
                .latRange(51.4, 51.6)
                .lngRange(-0.2, 0.1)
                .resolution(7)
                .generate(random);

        assertNotNull(h3Index);
        assertTrue(h3Index > 0);
    }

    // -- ESRI --

    @Test
    void generateEsriPolygonWithVertices() {
        // ESRI polygon with a specific vertex count range
        com.esri.core.geometry.Polygon polygon = GenEsriGeometry.polygon()
                .xRange(-10, 10)
                .yRange(-10, 10)
                .vertices(4, 6)
                .generate(random);

        assertNotNull(polygon);
        assertTrue(polygon.getPointCount() >= 4);
    }

    // -- Spatial4j --

    @Test
    void generateSpatial4jCircle() {
        // Spatial4j circle with custom center range and radius
        Circle circle = GenSpatial4j.circle()
                .xRange(-5, 5)
                .yRange(-5, 5)
                .radiusRange(1.0, 5.0)
                .generate(random);

        assertNotNull(circle);
        assertTrue(circle.getRadius() >= 1.0 && circle.getRadius() <= 5.0);
    }

    // -- Proj4j --

    @Test
    void generateProj4jCoordinateAndCrs() {
        // Generate a coordinate with specific ranges
        ProjCoordinate coord = GenProj4j.projCoordinate()
                .xRange(-180, 180)
                .yRange(-90, 90)
                .generate(random);

        assertNotNull(coord);
        assertTrue(coord.x >= -180 && coord.x <= 180);
        assertTrue(coord.y >= -90 && coord.y <= 90);

        // Generate a CRS from a specific EPSG code
        CoordinateReferenceSystem crs = GenProj4j.crs()
                .epsgCodes("EPSG:4326")
                .generate(random);

        assertNotNull(crs);
        assertNotNull(crs.getProjection());
    }

    // -- Geolatte --

    @Test
    void generateGeolatteGeometries() {
        // Geolatte point with coordinate ranges for NYC
        var point = GenGeolatte.point()
                .xRange(-74.0, -73.9)
                .yRange(40.7, 40.8)
                .generate(random);
        assertNotNull(point);

        // Geolatte line string for San Francisco area
        var line = GenGeolatte.lineString()
                .xRange(-122.5, -122.3)
                .yRange(37.7, 37.8)
                .generate(random);
        assertNotNull(line);
        assertTrue(line.getNumPositions() >= 2);
    }
}
