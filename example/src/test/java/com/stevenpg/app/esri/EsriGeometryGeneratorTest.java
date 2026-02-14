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

package com.stevenpg.app.esri;

import com.esri.core.geometry.*;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.stevenpg.instancio.esri.GenEsriGeometry;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EsriGeometryGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGeneratePointWithInstancio() {
        Point p = Instancio.create(Point.class);
        assertNotNull(p);
        assertTrue(p.getX() >= -180 && p.getX() <= 180);
        assertTrue(p.getY() >= -90 && p.getY() <= 90);
    }

    @Test
    void shouldGenerateEnvelopeWithInstancio() {
        Envelope e = Instancio.create(Envelope.class);
        assertNotNull(e);
        assertTrue(e.getXMin() >= -180 && e.getXMin() <= 180);
        assertTrue(e.getXMax() >= -180 && e.getXMax() <= 180);
        assertTrue(e.getYMin() >= -90 && e.getYMin() <= 90);
        assertTrue(e.getYMax() >= -90 && e.getYMax() <= 90);
        assertTrue(e.getXMin() <= e.getXMax());
        assertTrue(e.getYMin() <= e.getYMax());
    }

    @Test
    void shouldGeneratePolylineWithInstancio() {
        Polyline pl = Instancio.create(Polyline.class);
        assertNotNull(pl);
        assertTrue(pl.getPointCount() >= 2);
    }

    @Test
    void shouldGeneratePolygonWithInstancio() {
        Polygon pg = Instancio.create(Polygon.class);
        assertNotNull(pg);
        assertTrue(pg.getPointCount() >= 3);
    }

    @Test
    void shouldGenerateMultiPointWithInstancio() {
        MultiPoint mp = Instancio.create(MultiPoint.class);
        assertNotNull(mp);
        assertTrue(mp.getPointCount() >= 2);
    }

    @Test
    void shouldGeneratePointWithGenerator() {
        Point p = GenEsriGeometry.point()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .generate(random);
        assertNotNull(p);
    }

    @Test
    void shouldGenerateEnvelopeWithGenerator() {
        Envelope e = GenEsriGeometry.envelope()
                .xRange(-100, 100)
                .yRange(-50, 50)
                .generate(random);
        assertNotNull(e);
    }
}
