package com.stevenpg.instancio.locationtech.core.internal.generator.geom;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

import static org.junit.jupiter.api.Assertions.*;

class LineStringGeneratorTest {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    void shouldGenerateLineString() {
        var generator = new LineStringGenerator();
        var lineString = generator.generate(null);
        assertNotNull(lineString);
        assertTrue(lineString.getCoordinates().length > 0);
    }

    @Test
    void shouldGenerateLineStringWithCustomCoordinateSequence() {
        var coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(1, 1)
        };
        var sequence = geometryFactory.getCoordinateSequenceFactory().create(coordinates);

        var generator = new LineStringGenerator().coordinateSequence(sequence);
        var lineString = generator.generate(null);

        assertArrayEquals(coordinates, lineString.getCoordinates());
    }

    @Test
    void shouldGenerateLineStringWithCustomLength() {
        int length = 5;
        var generator = new LineStringGenerator().length(length);
        var lineString = generator.generate(null);

        assertEquals(length, lineString.getCoordinates().length);
    }

    @Test
    void shouldGenerateLineStringWithCustomGeometryFactory() {
        var customFactory = new GeometryFactory();
        var generator = new LineStringGenerator().geometryFactory(customFactory);
        var lineString = generator.generate(null);

        assertSame(customFactory, lineString.getFactory());
    }

    @Test
    void shouldGenerateLineStringWithinEnvelope() {
        var envelope = new Envelope(0, 10, 0, 10);
        var generator = new LineStringGenerator().within(envelope);
        var lineString = generator.generate(null);

        assertTrue(envelope.contains(lineString.getEnvelopeInternal()));
    }

    @Test
    void shouldHandleLengthOneEdgeCase() {
        var generator = new LineStringGenerator().length(1);
        assertThrows(IllegalArgumentException.class, () ->
                generator.generate(null));
    }
}