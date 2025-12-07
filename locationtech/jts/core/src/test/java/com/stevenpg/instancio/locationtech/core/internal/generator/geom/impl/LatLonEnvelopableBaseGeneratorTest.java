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
        longitudeProvidedGenerator.setInputLongitude(20d);

        latitudeLongitudeProvidedGenerator.setInputLatitude(20d);
        latitudeLongitudeProvidedGenerator.setInputLongitude(30d);

        assertTrue(latitudeProvidedGenerator.coordinateMissing());
        assertTrue(longitudeProvidedGenerator.coordinateMissing());
        assertFalse(latitudeLongitudeProvidedGenerator.coordinateMissing());
    }
}