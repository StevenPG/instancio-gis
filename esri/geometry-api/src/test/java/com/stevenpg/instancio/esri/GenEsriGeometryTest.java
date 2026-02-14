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

package com.stevenpg.instancio.esri;

import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.MultiPoint;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class GenEsriGeometryTest {

    @RepeatedTest(10)
    void point() {
        var generator = GenEsriGeometry.point();
        assertNotNull(generator);

        var result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertInstanceOf(Point.class, result);
        assertTrue(result.getX() >= -180 && result.getX() <= 180,
                "X coordinate should be within WGS84 longitude bounds [-180, 180], was: " + result.getX());
        assertTrue(result.getY() >= -90 && result.getY() <= 90,
                "Y coordinate should be within WGS84 latitude bounds [-90, 90], was: " + result.getY());
    }

    @RepeatedTest(10)
    void multiPoint() {
        var generator = GenEsriGeometry.multiPoint();
        assertNotNull(generator);

        var result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertInstanceOf(MultiPoint.class, result);
        assertTrue(result.getPointCount() >= 2,
                "MultiPoint should contain at least 2 points, had: " + result.getPointCount());
    }

    @RepeatedTest(10)
    void envelope() {
        var generator = GenEsriGeometry.envelope();
        assertNotNull(generator);

        var result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertInstanceOf(Envelope.class, result);
        assertTrue(result.getXMin() <= result.getXMax(),
                "XMin should be <= XMax, but XMin=" + result.getXMin() + " XMax=" + result.getXMax());
        assertTrue(result.getYMin() <= result.getYMax(),
                "YMin should be <= YMax, but YMin=" + result.getYMin() + " YMax=" + result.getYMax());
    }

    @RepeatedTest(10)
    void polyline() {
        var generator = GenEsriGeometry.polyline();
        assertNotNull(generator);

        var result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertInstanceOf(Polyline.class, result);
        assertTrue(result.getPointCount() >= 2,
                "Polyline should contain at least 2 points, had: " + result.getPointCount());
    }

    @RepeatedTest(10)
    void polygon() {
        var generator = GenEsriGeometry.polygon();
        assertNotNull(generator);

        var result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertInstanceOf(Polygon.class, result);
        assertTrue(result.getPointCount() >= 3,
                "Polygon should contain at least 3 vertices, had: " + result.getPointCount());
    }
}
