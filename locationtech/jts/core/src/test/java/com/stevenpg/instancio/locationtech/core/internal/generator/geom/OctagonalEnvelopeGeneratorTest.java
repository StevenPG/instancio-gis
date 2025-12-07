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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.OctagonalEnvelope;
import org.locationtech.jts.geom.Point;

import static org.junit.jupiter.api.Assertions.*;

class OctagonalEnvelopeGeneratorTest {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @RepeatedTest(5)
    void create() {
        var octagonalEnvelope = Instancio.create(OctagonalEnvelope.class);
        assertNotNull(octagonalEnvelope);
        // Verify it has some valid envelope bounds
        assertTrue(octagonalEnvelope.getMinX() <= octagonalEnvelope.getMaxX());
        assertTrue(octagonalEnvelope.getMinY() <= octagonalEnvelope.getMaxY());
        assertTrue(octagonalEnvelope.getMinA() <= octagonalEnvelope.getMaxA());
        assertTrue(octagonalEnvelope.getMinB() <= octagonalEnvelope.getMaxB());
    }

    @Test
    void coordinateSingle() {
        var coordinate = new Coordinate(5.0, 15.0);
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .coordinate(coordinate);
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        // Single coordinate creates a point-like envelope
        assertEquals(5.0, result.getMinX());
        assertEquals(5.0, result.getMaxX());
        assertEquals(15.0, result.getMinY());
        assertEquals(15.0, result.getMaxY());
        assertEquals(20.0, result.getMinA());
        assertEquals(20.0, result.getMaxA());
        assertEquals(-10, result.getMinB());
        assertEquals(-10, result.getMaxB());
    }

    @Test
    void coordinateDouble() {
        var coordinate0 = new Coordinate(5.0, 15.0);
        var coordinate1 = new Coordinate(25.0, 35.0);
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .coordinate(coordinate0, coordinate1);
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(5.0, result.getMinX());
        assertEquals(25.0, result.getMaxX());
        assertEquals(15.0, result.getMinY());
        assertEquals(35.0, result.getMaxY());
        assertEquals(20.0, result.getMinA());
        assertEquals(60.0, result.getMaxA());
        assertEquals(-10.0, result.getMinB());
        assertEquals(-10.0, result.getMaxB());
    }

    @Test
    void coordinateDouble_withSwappedCoordinates() {
        var coordinate0 = new Coordinate(25.0, 35.0); // larger values first
        var coordinate1 = new Coordinate(5.0, 15.0);  // smaller values second
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .coordinate(coordinate0, coordinate1);
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(5.0, result.getMinX());
        assertEquals(25.0, result.getMaxX());
        assertEquals(15.0, result.getMinY());
        assertEquals(35.0, result.getMaxY());
        assertEquals(20.0, result.getMinA());
        assertEquals(60.0, result.getMaxA());
        assertEquals(-10.0, result.getMinB());
        assertEquals(-10.0, result.getMaxB());
    }

    @Test
    void envelope() {
        var inputEnvelope = new Envelope(10.0, 50.0, 20.0, 60.0);
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .envelope(inputEnvelope);
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(10.0, result.getMinX());
        assertEquals(50.0, result.getMaxX());
        assertEquals(20.0, result.getMinY());
        assertEquals(60.0, result.getMaxY());
        assertEquals(30.0, result.getMinA());
        assertEquals(110.0, result.getMaxA());
        assertEquals(-50.0, result.getMinB());
        assertEquals(30.0, result.getMaxB());
    }

    @Test
    void octagonalEnvelope() {
        var inputEnvelope = new Envelope(10.0, 50.0, 20.0, 60.0);
        var inputOctagonalEnvelope = new OctagonalEnvelope(inputEnvelope);
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .envelope(inputOctagonalEnvelope);
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(10.0, result.getMinX());
        assertEquals(50.0, result.getMaxX());
        assertEquals(20.0, result.getMinY());
        assertEquals(60.0, result.getMaxY());

        // Ensure it's a copy, not the same instance
        assertNotSame(inputOctagonalEnvelope, result);
        assertEquals(inputOctagonalEnvelope.getMaxA(), result.getMaxA());
        assertEquals(inputOctagonalEnvelope.getMaxB(), result.getMaxB());
        assertEquals(inputOctagonalEnvelope.getMinA(), result.getMinA());
        assertEquals(inputOctagonalEnvelope.getMinB(), result.getMinB());
        assertEquals(inputOctagonalEnvelope.getMinX(), result.getMinX());
        assertEquals(inputOctagonalEnvelope.getMinY(), result.getMinY());
        assertEquals(inputOctagonalEnvelope.getMaxX(), result.getMaxX());
        assertEquals(inputOctagonalEnvelope.getMaxY(), result.getMaxY());

    }

    @Test
    void geometry() {
        Point point = geometryFactory.createPoint(new Coordinate(30.0, 40.0));
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .geometry(point);
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(30.0, result.getMinX());
        assertEquals(30.0, result.getMaxX());
        assertEquals(40.0, result.getMinY());
        assertEquals(40.0, result.getMaxY());
        assertEquals(70.0, result.getMinA());
        assertEquals(70.0, result.getMaxA());
        assertEquals(-10.0, result.getMinB());
        assertEquals(-10.0, result.getMaxB());
    }

    @Test
    void geometry_withLineString() {
        var coordinates = new Coordinate[] {
            new Coordinate(0.0, 0.0),
            new Coordinate(10.0, 10.0),
            new Coordinate(20.0, 0.0)
        };
        var lineString = geometryFactory.createLineString(coordinates);
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .geometry(lineString);
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(0.0, result.getMinX());
        assertEquals(20.0, result.getMaxX());
        assertEquals(0.0, result.getMinY());
        assertEquals(10.0, result.getMaxY());
        assertEquals(0.0, result.getMinA());
        assertEquals(20.0, result.getMaxA());
        assertEquals(0.0, result.getMinB());
        assertEquals(20.0, result.getMaxB());
    }

    @Test
    void generate_default() {
        OctagonalEnvelope result = new OctagonalEnvelopeGenerator().generate(new DefaultRandom());

        assertNotNull(result);
        // Verify it's a valid envelope (min <= max)
        assertTrue(result.getMinX() <= result.getMaxX());
        assertTrue(result.getMinY() <= result.getMaxY());

        // Verify coordinates are within expected longitude/latitude ranges
        // Instancio spatial generators produce lon: -180 to 180, lat: -90 to 90
        assertTrue(result.getMinX() >= -180.0 && result.getMinX() <= 180.0);
        assertTrue(result.getMaxX() >= -180.0 && result.getMaxX() <= 180.0);
        assertTrue(result.getMinY() >= -90.0 && result.getMinY() <= 90.0);
        assertTrue(result.getMaxY() >= -90.0 && result.getMaxY() <= 90.0);
    }

    @Test
    void generate_priorityTesting_octagonalEnvelope() {
        // Test that octagonal envelope takes priority over other configurations
        var inputEnv = new Envelope(100.0, 200.0, 300.0, 400.0);
        var inputOctagonalEnvelope = new OctagonalEnvelope(inputEnv);
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .coordinate(new Coordinate(1.0, 2.0))  // should be ignored
                .envelope(new Envelope(5.0, 6.0, 7.0, 8.0))  // should be ignored
                .envelope(inputOctagonalEnvelope);  // this should take priority
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(100.0, result.getMinX());
        assertEquals(200.0, result.getMaxX());
        assertEquals(300.0, result.getMinY());
        assertEquals(400.0, result.getMaxY());
        assertEquals(400.0, result.getMinA());
        assertEquals(600.0, result.getMaxA());
        assertEquals(-300.0, result.getMinB());
        assertEquals(-100.0, result.getMaxB());
    }

    @Test
    void generate_priorityTesting_geometry() {
        // Test that geometry takes priority over envelope and coordinates
        Point point = geometryFactory.createPoint(new Coordinate(100.0, 200.0));
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .coordinate(new Coordinate(1.0, 2.0))  // should be ignored
                .envelope(new Envelope(5.0, 6.0, 7.0, 8.0))  // should be ignored
                .geometry(point);  // this should take priority
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(100.0, result.getMinX());
        assertEquals(100.0, result.getMaxX());
        assertEquals(200.0, result.getMinY());
        assertEquals(200.0, result.getMaxY());
        assertEquals(300.0, result.getMinA());
        assertEquals(300.0, result.getMaxA());
        assertEquals(-100.0, result.getMinB());
        assertEquals(-100.0, result.getMaxB());
    }

    @Test
    void generate_priorityTesting_envelope() {
        // Test that envelope takes priority over coordinates
        var inputEnvelope = new Envelope(100.0, 200.0, 300.0, 400.0);
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .coordinate(new Coordinate(1.0, 2.0))  // should be ignored
                .envelope(inputEnvelope);  // this should take priority
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(100.0, result.getMinX());
        assertEquals(200.0, result.getMaxX());
        assertEquals(300.0, result.getMinY());
        assertEquals(400.0, result.getMaxY());
        assertEquals(400.0, result.getMinA());
        assertEquals(600.0, result.getMaxA());
        assertEquals(-300.0, result.getMinB());
        assertEquals(-100.0, result.getMaxB());
    }

    @Test
    void generate_priorityTesting_coordinateDouble() {
        // Test that two coordinates take priority over single coordinate
        var coordinate0 = new Coordinate(100.0, 200.0);
        var coordinate1 = new Coordinate(300.0, 400.0);
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .coordinate(new Coordinate(1.0, 2.0))  // this gets overwritten
                .coordinate(coordinate0, coordinate1);  // this should take priority
        OctagonalEnvelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(100.0, result.getMinX());
        assertEquals(300.0, result.getMaxX());
        assertEquals(200.0, result.getMinY());
        assertEquals(400.0, result.getMaxY());
    }

    @Test
    void constructor() {
        // Test that the default constructor works
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator();
        assertNotNull(generator);

        OctagonalEnvelope result = generator.generate(new DefaultRandom());
        assertNotNull(result);
    }

    @Test
    void methodChaining() {
        // Test that all methods return the generator instance for chaining
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator();

        var result1 = generator.coordinate(new Coordinate(1.0, 2.0));
        assertSame(generator, result1);

        var result2 = generator.coordinate(new Coordinate(3.0, 4.0), new Coordinate(5.0, 6.0));
        assertSame(generator, result2);

        var result3 = generator.envelope(new Envelope(7.0, 8.0, 9.0, 10.0));
        assertSame(generator, result3);

        var result4 = generator.envelope(new OctagonalEnvelope(new Envelope(11.0, 12.0, 13.0, 14.0)));
        assertSame(generator, result4);

        Point point = geometryFactory.createPoint(new Coordinate(15.0, 16.0));
        var result5 = generator.geometry(point);
        assertSame(generator, result5);
    }

    @Test
    void nullInputHandling() {
        // Test behavior with null inputs - should fall back to default generation
        OctagonalEnvelopeGenerator generator = new OctagonalEnvelopeGenerator()
                .coordinate(null)
                .envelope((Envelope) null)
                .envelope((OctagonalEnvelope) null)
                .geometry(null);

        OctagonalEnvelope result = generator.generate(new DefaultRandom());
        assertNotNull(result);

        assertTrue(result.getMinX() <= result.getMaxX());
        assertTrue(result.getMinY() <= result.getMaxY());
    }
}
