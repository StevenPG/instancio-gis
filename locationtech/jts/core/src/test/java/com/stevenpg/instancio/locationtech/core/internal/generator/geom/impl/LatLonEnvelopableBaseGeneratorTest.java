package com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LatLonEnvelopableBaseGeneratorTest {

    LatLonEnvelopableBaseGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new LatLonEnvelopableBaseGenerator();
    }

    @Test
    void coordinateMissing() {
        assertTrue(generator.coordinateMissing());
    }

    @Test
    void coordinatePresent() {
        var latitudeProvidedGenerator = new LatLonEnvelopableBaseGenerator();
        var longitudeProvidedGenerator = new LatLonEnvelopableBaseGenerator();
        var latitudeLongitudeProvidedGenerator = new LatLonEnvelopableBaseGenerator();
        
        latitudeProvidedGenerator.setInputLatitude(10d);
        longitudeProvidedGenerator.setInputLatitude(20d);

        latitudeLongitudeProvidedGenerator.setInputLatitude(20d);
        latitudeLongitudeProvidedGenerator.setInputLongitude(30d);

        assertTrue(latitudeProvidedGenerator.coordinateMissing());
        assertTrue(longitudeProvidedGenerator.coordinateMissing());
        assertFalse(latitudeLongitudeProvidedGenerator.coordinateMissing());
    }
}