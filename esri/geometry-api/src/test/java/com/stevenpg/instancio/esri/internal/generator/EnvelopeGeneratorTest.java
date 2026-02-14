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

package com.stevenpg.instancio.esri.internal.generator;

import com.esri.core.geometry.Envelope;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvelopeGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var envelope = Instancio.create(Envelope.class);
        assertNotNull(envelope);
        assertTrue(envelope.getXMin() >= -180 && envelope.getXMin() <= 180,
                "XMin should be within WGS84 longitude bounds [-180, 180], was: " + envelope.getXMin());
        assertTrue(envelope.getXMax() >= -180 && envelope.getXMax() <= 180,
                "XMax should be within WGS84 longitude bounds [-180, 180], was: " + envelope.getXMax());
        assertTrue(envelope.getYMin() >= -90 && envelope.getYMin() <= 90,
                "YMin should be within WGS84 latitude bounds [-90, 90], was: " + envelope.getYMin());
        assertTrue(envelope.getYMax() >= -90 && envelope.getYMax() <= 90,
                "YMax should be within WGS84 latitude bounds [-90, 90], was: " + envelope.getYMax());
        assertTrue(envelope.getXMin() <= envelope.getXMax(),
                "XMin should be <= XMax, but XMin=" + envelope.getXMin() + " XMax=" + envelope.getXMax());
        assertTrue(envelope.getYMin() <= envelope.getYMax(),
                "YMin should be <= YMax, but YMin=" + envelope.getYMin() + " YMax=" + envelope.getYMax());
    }

    @Test
    void shouldGenerateWithinRanges() {
        var result = new EnvelopeGenerator()
                .xRange(0, 50)
                .yRange(0, 50)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getXMin() >= 0 && result.getXMin() <= 50);
        assertTrue(result.getXMax() >= 0 && result.getXMax() <= 50);
        assertTrue(result.getYMin() >= 0 && result.getYMin() <= 50);
        assertTrue(result.getYMax() >= 0 && result.getYMax() <= 50);
        assertTrue(result.getXMin() <= result.getXMax());
        assertTrue(result.getYMin() <= result.getYMax());
    }

    @Test
    void generate() {
        var result = new EnvelopeGenerator().generate(new DefaultRandom());
        assertNotNull(result);
        assertTrue(result.getXMin() >= -180 && result.getXMin() <= 180,
                "XMin should be within WGS84 longitude bounds [-180, 180], was: " + result.getXMin());
        assertTrue(result.getXMax() >= -180 && result.getXMax() <= 180,
                "XMax should be within WGS84 longitude bounds [-180, 180], was: " + result.getXMax());
        assertTrue(result.getYMin() >= -90 && result.getYMin() <= 90,
                "YMin should be within WGS84 latitude bounds [-90, 90], was: " + result.getYMin());
        assertTrue(result.getYMax() >= -90 && result.getYMax() <= 90,
                "YMax should be within WGS84 latitude bounds [-90, 90], was: " + result.getYMax());
        assertTrue(result.getXMin() <= result.getXMax(),
                "XMin should be <= XMax, but XMin=" + result.getXMin() + " XMax=" + result.getXMax());
        assertTrue(result.getYMin() <= result.getYMax(),
                "YMin should be <= YMax, but YMin=" + result.getYMin() + " YMax=" + result.getYMax());
    }
}
