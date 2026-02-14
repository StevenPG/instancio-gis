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

package com.stevenpg.instancio.h3.internal.generator;

import com.uber.h3core.util.LatLng;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LatLngGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var result = Instancio.create(LatLng.class);
        assertNotNull(result);
        assertTrue(result.lat >= -90 && result.lat <= 90,
                "Latitude " + result.lat + " should be within [-90, 90]");
        assertTrue(result.lng >= -180 && result.lng <= 180,
                "Longitude " + result.lng + " should be within [-180, 180]");
    }

    @Test
    void shouldGenerateWithinRanges() {
        var generator = new LatLngGenerator()
                .latRange(10.0, 20.0)
                .lngRange(30.0, 40.0);

        var result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.lat >= 10.0 && result.lat <= 20.0,
                "Latitude " + result.lat + " should be within [10.0, 20.0]");
        assertTrue(result.lng >= 30.0 && result.lng <= 40.0,
                "Longitude " + result.lng + " should be within [30.0, 40.0]");
    }

    @Test
    void generate() {
        var generator = new LatLngGenerator();
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertTrue(result.lat >= -90 && result.lat <= 90,
                "Latitude " + result.lat + " should be within [-90, 90]");
        assertTrue(result.lng >= -180 && result.lng <= 180,
                "Longitude " + result.lng + " should be within [-180, 180]");
    }
}
