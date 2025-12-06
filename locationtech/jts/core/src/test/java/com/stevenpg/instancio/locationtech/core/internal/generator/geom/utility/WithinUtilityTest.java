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

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Envelope;

import static org.junit.jupiter.api.Assertions.*;

class WithinUtilityTest {

    @Test
    void getBounds() {
        var testEnvelope = new Envelope(-70, -80, 38, 42);
        var bounds = WithinUtility.getBounds(testEnvelope);
        assertEquals(-80, bounds.minLon());
        assertEquals(-70, bounds.maxLon());
        assertEquals(38, bounds.minLat());
        assertEquals(42, bounds.maxLat());
    }

    @RepeatedTest(10)
    void randomLongitudeInBounds() {
        var testEnvelope = new Envelope(-70, -80, 38, 42);
        var record = WithinUtility.randomLonLatInBounds(testEnvelope);
        var lon = record.longitude();
        var lat = record.latitude();

        assertTrue(lon >= -80 && lon <= -70);
        assertTrue(lat >= 38 && lat <= 42);
    }

    @RepeatedTest(10)
    void randomLongitudeNoBounds() {
        var record = WithinUtility.randomLonLatInBounds();
        var lon = record.longitude();
        var lat = record.latitude();

        assertTrue(lon >= -180 && lon <= 180);
        assertTrue(lat >= -90 && lat <= 90);
    }
}