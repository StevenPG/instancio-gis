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

class LinearRingGeneratorTest {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    void shouldGenerateLinearRing() {
        var generator = new LinearRingGenerator();
        var linearRing = generator.generate(null);

        assertNotNull(linearRing);
        assertTrue(linearRing.getCoordinates().length >= 4);
        assertTrue(linearRing.isClosed());

        // Verify first and last coordinates are the same
        Coordinate[] coords = linearRing.getCoordinates();
        assertEquals(coords[0], coords[coords.length - 1]);
    }

    @Test
    void shouldGenerateLinearRingWithCustomCoordinateSequenceAlreadyClosed() {
        var coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(0, 1),
                new Coordinate(0, 0)  // Already closed
        };
        var sequence = geometryFactory.getCoordinateSequenceFactory().create(coordinates);

        var generator = new LinearRingGenerator().coordinateSequence(sequence);
        var linearRing = generator.generate(null);

        assertArrayEquals(coordinates, linearRing.getCoordinates());
        assertTrue(linearRing.isClosed());
    }

    @Test
    void shouldGenerateLinearRingWithCustomCoordinateSequenceNotClosed() {
        var coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(0, 1)  // Not closed
        };
        var sequence = geometryFactory.getCoordinateSequenceFactory().create(coordinates);

        var generator = new LinearRingGenerator().coordinateSequence(sequence);
        var linearRing = generator.generate(null);

        assertTrue(linearRing.isClosed());
        assertEquals(5, linearRing.getCoordinates().length); // Original 4 + 1 closing coordinate

        Coordinate[] result = linearRing.getCoordinates();
        assertEquals(result[0], result[result.length - 1]); // First equals last
    }

    @Test
    void shouldGenerateLinearRingWithCustomLength() {
        int uniquePoints = 5;
        var generator = new LinearRingGenerator().length(uniquePoints);
        var linearRing = generator.generate(null);

        assertEquals(uniquePoints + 1, linearRing.getCoordinates().length); // +1 for closing coordinate
        assertTrue(linearRing.isClosed());
    }

    @Test
    void shouldGenerateLinearRingWithMinimumLengthWhenTooSmall() {
        int length = 2; // Too small for LinearRing
        var generator = new LinearRingGenerator().length(length);
        var linearRing = generator.generate(null);

        assertEquals(4, linearRing.getCoordinates().length); // Minimum: 3 unique + 1 closing = 4 total
        assertTrue(linearRing.isClosed());
    }

    @Test
    void shouldGenerateLinearRingWithCustomGeometryFactory() {
        var customFactory = new GeometryFactory();
        var generator = new LinearRingGenerator().geometryFactory(customFactory);
        var linearRing = generator.generate(null);

        assertSame(customFactory, linearRing.getFactory());
        assertTrue(linearRing.isClosed());
    }

    @Test
    void shouldGenerateLinearRingWithinEnvelope() {
        var envelope = new Envelope(0, 10, 0, 10);
        var generator = new LinearRingGenerator().within(envelope);
        var linearRing = generator.generate(null);

        assertTrue(envelope.contains(linearRing.getEnvelopeInternal()));
        assertTrue(linearRing.isClosed());
    }

    @Test
    void shouldGenerateLinearRingWithLengthAndEnvelope() {
        var envelope = new Envelope(0, 10, 0, 10);
        int uniquePoints = 4;
        var generator = new LinearRingGenerator().length(uniquePoints).within(envelope);
        var linearRing = generator.generate(null);

        assertEquals(uniquePoints + 1, linearRing.getCoordinates().length);
        assertTrue(envelope.contains(linearRing.getEnvelopeInternal()));
        assertTrue(linearRing.isClosed());
    }

    @Test
    void shouldThrowExceptionForTooSmallCoordinateSequence() {
        var coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(1, 1)  // Only 2 coordinates
        };
        var sequence = geometryFactory.getCoordinateSequenceFactory().create(coordinates);

        var generator = new LinearRingGenerator().coordinateSequence(sequence);

        assertThrows(IllegalArgumentException.class, () ->
                generator.generate(null));
    }

    @Test
    void shouldThrowExceptionForExactly3Coordinates() {
        var coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(1, 0),
                new Coordinate(1, 1)  // Only 3 coordinates
        };
        var sequence = geometryFactory.getCoordinateSequenceFactory().create(coordinates);

        var generator = new LinearRingGenerator().coordinateSequence(sequence);

        assertThrows(IllegalArgumentException.class, () ->
                generator.generate(null));
    }

    @Test
    void shouldHandleCoordinateWith3DValues() {
        var coordinates = new Coordinate[]{
                new Coordinate(0, 0, 5),
                new Coordinate(1, 0, 6),
                new Coordinate(1, 1, 7),
                new Coordinate(0, 1, 8)  // Not closed, has Z values
        };
        var sequence = geometryFactory.getCoordinateSequenceFactory().create(coordinates);

        var generator = new LinearRingGenerator().coordinateSequence(sequence);
        var linearRing = generator.generate(null);

        assertTrue(linearRing.isClosed());
        assertEquals(5, linearRing.getCoordinates().length);

        Coordinate[] result = linearRing.getCoordinates();
        assertEquals(0, result[0].x, 0.001);
        assertEquals(0, result[0].y, 0.001);
        assertEquals(5, result[0].getZ(), 0.001); // Z value preserved
        assertEquals(result[0], result[result.length - 1]); // First equals last including Z
    }

    @Test
    void shouldGenerateValidLinearRingWithRandomGenerator() {
        var generator = new LinearRingGenerator();
        org.instancio.Random random = new org.instancio.support.DefaultRandom();

        var linearRing = generator.generate(random);

        assertNotNull(linearRing);
        assertTrue(linearRing.isClosed());
        assertTrue(linearRing.getCoordinates().length >= 4);

        // Verify coordinates are within expected bounds (based on CoordinateSequenceGenerator defaults)
        for (Coordinate coord : linearRing.getCoordinates()) {
            assertTrue(coord.x >= -180 && coord.x <= 180);
            assertTrue(coord.y >= -90 && coord.y <= 90);
        }
    }
}
