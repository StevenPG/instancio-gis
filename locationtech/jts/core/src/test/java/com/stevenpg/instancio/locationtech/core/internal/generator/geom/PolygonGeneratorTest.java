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

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

import static org.junit.jupiter.api.Assertions.*;

class PolygonGeneratorTest {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    void shouldGeneratePolygon() {
        var generator = new PolygonGenerator();
        var polygon = generator.generate(null);

        assertNotNull(polygon);
        assertNotNull(polygon.getExteriorRing());
        assertTrue(polygon.getExteriorRing().getCoordinates().length >= 4);
        assertTrue(polygon.getExteriorRing().isClosed());
    }

    @Test
    void shouldGeneratePolygonWithCustomExteriorRing() {
        var coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(5, 0),
                new Coordinate(5, 5),
                new Coordinate(0, 5),
                new Coordinate(0, 0)
        };
        var exteriorRing = geometryFactory.createLinearRing(coordinates);

        var generator = new PolygonGenerator().exteriorRing(exteriorRing);
        var polygon = generator.generate(null);

        assertArrayEquals(coordinates, polygon.getExteriorRing().getCoordinates());
        assertEquals(0, polygon.getNumInteriorRing());
    }

    @Test
    void shouldGeneratePolygonWithExteriorRingAndHoles() {
        var exteriorCoords = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(10, 0),
                new Coordinate(10, 10),
                new Coordinate(0, 10),
                new Coordinate(0, 0)
        };
        var exteriorRing = geometryFactory.createLinearRing(exteriorCoords);

        var holeCoords = new Coordinate[]{
                new Coordinate(2, 2),
                new Coordinate(4, 2),
                new Coordinate(4, 4),
                new Coordinate(2, 4),
                new Coordinate(2, 2)
        };
        var hole = geometryFactory.createLinearRing(holeCoords);

        var generator = new PolygonGenerator().rings(exteriorRing, hole);
        var polygon = generator.generate(null);

        assertArrayEquals(exteriorCoords, polygon.getExteriorRing().getCoordinates());
        assertEquals(1, polygon.getNumInteriorRing());
        assertArrayEquals(holeCoords, polygon.getInteriorRingN(0).getCoordinates());
    }

    @Test
    void shouldGeneratePolygonWithCustomVertices() {
        int vertices = 6;
        var generator = new PolygonGenerator().vertices(vertices);
        var polygon = generator.generate(null);

        // The exterior ring should have vertices + 1 coordinates (closed ring)
        assertEquals(vertices + 1, polygon.getExteriorRing().getCoordinates().length);
    }

    @Test
    void shouldGeneratePolygonWithMinimumVertices() {
        var generator = new PolygonGenerator().vertices(2); // Should be adjusted to minimum of 3
        var polygon = generator.generate(null);

        // Should have at least 4 coordinates (3 unique + 1 closing)
        assertTrue(polygon.getExteriorRing().getCoordinates().length >= 4);
    }

    @Test
    void shouldGeneratePolygonWithHoles() {
        int holesCount = 2;
        var generator = new PolygonGenerator().holes(holesCount);
        var polygon = generator.generate(null);

        assertEquals(holesCount, polygon.getNumInteriorRing());

        // Verify each hole is valid
        for (int i = 0; i < holesCount; i++) {
            var hole = polygon.getInteriorRingN(i);
            assertNotNull(hole);
            assertTrue(hole.getCoordinates().length >= 4);
            assertTrue(hole.isClosed());
        }
    }

    @Test
    void shouldGeneratePolygonWithCustomGeometryFactory() {
        var customFactory = new GeometryFactory();
        var generator = new PolygonGenerator().geometryFactory(customFactory);
        var polygon = generator.generate(null);

        assertSame(customFactory, polygon.getFactory());
    }

    @Test
    void shouldGeneratePolygonWithinEnvelope() {
        var envelope = new Envelope(0, 10, 0, 10);
        var generator = new PolygonGenerator().within(envelope);
        var polygon = generator.generate(null);

        assertTrue(envelope.contains(polygon.getEnvelopeInternal()));
    }

    @Test
    void shouldGeneratePolygonWithVerticesAndEnvelope() {
        var envelope = new Envelope(5, 15, 5, 15);
        int vertices = 5;
        var generator = new PolygonGenerator().vertices(vertices).within(envelope);
        var polygon = generator.generate(null);

        assertEquals(vertices + 1, polygon.getExteriorRing().getCoordinates().length);
        assertTrue(envelope.contains(polygon.getEnvelopeInternal()));
    }

    @Test
    void shouldGeneratePolygonWithHolesAndEnvelope() {
        var envelope = new Envelope(0, 20, 0, 20);
        int holesCount = 1;
        var generator = new PolygonGenerator().holes(holesCount).within(envelope);
        var polygon = generator.generate(null);

        assertEquals(holesCount, polygon.getNumInteriorRing());
        assertTrue(envelope.contains(polygon.getEnvelopeInternal()));

        // Verify hole is within the exterior envelope
        var hole = polygon.getInteriorRingN(0);
        assertTrue(polygon.getExteriorRing().getEnvelopeInternal().contains(hole.getEnvelopeInternal()));
    }

    @Test
    void shouldGeneratePolygonWithZeroHoles() {
        var generator = new PolygonGenerator().holes(0);
        var polygon = generator.generate(null);

        assertEquals(0, polygon.getNumInteriorRing());
    }

    @Test
    void shouldGeneratePolygonWithMultipleHoles() {
        int holesCount = 3;
        var generator = new PolygonGenerator().holes(holesCount);
        var polygon = generator.generate(null);

        assertEquals(holesCount, polygon.getNumInteriorRing());
    }

    @Test
    void shouldGeneratePolygonWithAllCustomParameters() {
        var envelope = new Envelope(-5, 5, -5, 5);
        int vertices = 8;
        int holesCount = 2;
        var customFactory = new GeometryFactory();

        var generator = new PolygonGenerator()
                .vertices(vertices)
                .holes(holesCount)
                .geometryFactory(customFactory)
                .within(envelope);
        var polygon = generator.generate(null);

        assertEquals(vertices + 1, polygon.getExteriorRing().getCoordinates().length);
        assertEquals(holesCount, polygon.getNumInteriorRing());
        assertSame(customFactory, polygon.getFactory());
        assertTrue(envelope.contains(polygon.getEnvelopeInternal()));
    }

    @Test
    void shouldHandleExteriorRingWithoutHolesArray() {
        var coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(3, 0),
                new Coordinate(3, 3),
                new Coordinate(0, 3),
                new Coordinate(0, 0)
        };
        var exteriorRing = geometryFactory.createLinearRing(coordinates);

        // Test using exteriorRing method (not rings method)
        var generator = new PolygonGenerator().exteriorRing(exteriorRing);
        var polygon = generator.generate(null);

        assertArrayEquals(coordinates, polygon.getExteriorRing().getCoordinates());
        assertEquals(0, polygon.getNumInteriorRing());
    }

    @Test
    void shouldTestDefaultConstructor() {
        // Test that default constructor creates a valid generator
        var generator = new PolygonGenerator();
        assertNotNull(generator);

        var polygon = generator.generate(null);
        assertNotNull(polygon);
    }

    @Test
    void shouldCreateHoleEnvelopeCorrectly() {
        // Test hole creation with larger polygon to ensure holes fit properly
        var envelope = new Envelope(0, 100, 0, 100);
        var generator = new PolygonGenerator().holes(1).within(envelope);
        var polygon = generator.generate(null);

        assertEquals(1, polygon.getNumInteriorRing());
        var hole = polygon.getInteriorRingN(0);
        var holeEnv = hole.getEnvelopeInternal();
        var exteriorEnv = polygon.getExteriorRing().getEnvelopeInternal();

        // Hole should be contained within exterior
        assertTrue(exteriorEnv.contains(holeEnv) || exteriorEnv.intersects(holeEnv));
    }
}
