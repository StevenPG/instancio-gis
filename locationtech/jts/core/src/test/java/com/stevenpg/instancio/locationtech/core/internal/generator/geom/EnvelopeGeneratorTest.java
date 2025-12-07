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

import static org.junit.jupiter.api.Assertions.*;

class EnvelopeGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var envelope = Instancio.create(Envelope.class);
        assertNotNull(envelope);
        // Verify it's a valid envelope (min <= max)
        assertTrue(envelope.getMinX() <= envelope.getMaxX());
        assertTrue(envelope.getMinY() <= envelope.getMaxY());
    }

    @Test
    void bounds() {
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .bounds(1.0, 10.0, 2.0, 20.0);
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(1.0, result.getMinX());
        assertEquals(10.0, result.getMaxX());
        assertEquals(2.0, result.getMinY());
        assertEquals(20.0, result.getMaxY());
    }

    @Test
    void bounds_withSwappedValues() {
        // Test that bounds handles swapped min/max correctly by using JTS Envelope constructor
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .bounds(10.0, 1.0, 20.0, 2.0); // x1 > x2, y1 > y2
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(1.0, result.getMinX()); // JTS Envelope constructor handles min/max ordering
        assertEquals(10.0, result.getMaxX());
        assertEquals(2.0, result.getMinY());
        assertEquals(20.0, result.getMaxY());
    }

    @Test
    void coordinateSingle() {
        var coordinate = new Coordinate(5.0, 15.0);
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .coordinate(coordinate);
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(5.0, result.getMinX());
        assertEquals(5.0, result.getMaxX());
        assertEquals(15.0, result.getMinY());
        assertEquals(15.0, result.getMaxY());
    }

    @Test
    void coordinateDouble() {
        var coordinate0 = new Coordinate(5.0, 15.0);
        var coordinate1 = new Coordinate(25.0, 35.0);
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .coordinate(coordinate0, coordinate1);
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(5.0, result.getMinX());
        assertEquals(25.0, result.getMaxX());
        assertEquals(15.0, result.getMinY());
        assertEquals(35.0, result.getMaxY());
    }

    @Test
    void coordinateDouble_withSwappedCoordinates() {
        var coordinate0 = new Coordinate(25.0, 35.0); // larger values first
        var coordinate1 = new Coordinate(5.0, 15.0);  // smaller values second
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .coordinate(coordinate0, coordinate1);
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(5.0, result.getMinX());
        assertEquals(25.0, result.getMaxX());
        assertEquals(15.0, result.getMinY());
        assertEquals(35.0, result.getMaxY());
    }

    @Test
    void envelope() {
        var inputEnvelope = new Envelope(10.0, 50.0, 20.0, 60.0);
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .envelope(inputEnvelope);
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(10.0, result.getMinX());
        assertEquals(50.0, result.getMaxX());
        assertEquals(20.0, result.getMinY());
        assertEquals(60.0, result.getMaxY());

        // Ensure it's a copy, not the same instance
        assertNotSame(inputEnvelope, result);
        assertEquals(inputEnvelope, result);
    }

    @Test
    void generate_default() {
        Envelope result = new EnvelopeGenerator().generate(new DefaultRandom());

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
    void generate_priorityTesting_envelope() {
        // Test that envelope takes priority over other configurations
        var inputEnvelope = new Envelope(100.0, 200.0, 300.0, 400.0);
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .bounds(1.0, 2.0, 3.0, 4.0)        // should be ignored
                .coordinate(new Coordinate(5.0, 6.0))  // should be ignored
                .envelope(inputEnvelope);               // should take priority
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(100.0, result.getMinX());
        assertEquals(200.0, result.getMaxX());
        assertEquals(300.0, result.getMinY());
        assertEquals(400.0, result.getMaxY());
    }

    @Test
    void generate_priorityTesting_bounds() {
        // Test that bounds takes priority over coordinates when envelope is not set
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .coordinate(new Coordinate(5.0, 6.0))  // should be ignored
                .bounds(1.0, 2.0, 3.0, 4.0);          // should take priority
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(1.0, result.getMinX());
        assertEquals(2.0, result.getMaxX());
        assertEquals(3.0, result.getMinY());
        assertEquals(4.0, result.getMaxY());
    }

    @Test
    void generate_priorityTesting_twoCoordinates() {
        // Test that two coordinates takes priority over one coordinate
        var coordinate0 = new Coordinate(10.0, 20.0);
        var coordinate1 = new Coordinate(30.0, 40.0);
        EnvelopeGenerator generator = new EnvelopeGenerator()
                .coordinate(new Coordinate(1.0, 2.0))     // should be overridden
                .coordinate(coordinate0, coordinate1);      // should take priority
        Envelope result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(10.0, result.getMinX());
        assertEquals(30.0, result.getMaxX());
        assertEquals(20.0, result.getMinY());
        assertEquals(40.0, result.getMaxY());
    }

    @Test
    void defaultConstructor() {
        var generator = new EnvelopeGenerator();
        assertNotNull(generator);

        Envelope result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertTrue(result.getMinX() <= result.getMaxX());
        assertTrue(result.getMinY() <= result.getMaxY());
    }
}
