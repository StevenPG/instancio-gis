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
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

import static org.junit.jupiter.api.Assertions.*;

class GeometryGeneratorTest {

    @RepeatedTest(3)
    void instancioCreateGeometry() {
        var geom = Instancio.create(Geometry.class);
        assertNotNull(geom);
    }

    @Test
    void withinEnvelopeRandomGeneration() {
        var env = new Envelope(0, 5, 0, 5);
        var result = new GeometryGenerator()
                .within(env)
                .generate(new DefaultRandom());

        assertNotNull(result);
        var rEnv = result.getEnvelopeInternal();
        assertTrue(rEnv.getMinX() >= env.getMinX());
        assertTrue(rEnv.getMaxX() <= env.getMaxX());
        assertTrue(rEnv.getMinY() >= env.getMinY());
        assertTrue(rEnv.getMaxY() <= env.getMaxY());
    }

    @Test
    void explicitPoint() {
        var point = new PointGenerator().coordinate(-75, 40).generate(new DefaultRandom());
        var result = new GeometryGenerator().point(point).generate(new DefaultRandom());
        assertSame(point, result);
    }

    @Test
    void explicitLineString() {
        var line = new LineStringGenerator().length(2).generate(new DefaultRandom());
        var result = new GeometryGenerator().lineString(line).generate(new DefaultRandom());
        assertSame(line, result);
    }

    @Test
    void explicitLinearRing() {
        var ring = new LinearRingGenerator().length(4).generate(new DefaultRandom());
        var result = new GeometryGenerator().linearRing(ring).generate(new DefaultRandom());
        assertSame(ring, result);
    }

    @Test
    void explicitPolygon() {
        var polygon = new PolygonGenerator().generate(new DefaultRandom());
        var result = new GeometryGenerator().polygon(polygon).generate(new DefaultRandom());
        assertSame(polygon, result);
    }

    @Test
    void explicitGeometryCollection() {
        var gc = new GeometryCollectionGenerator().length(3).generate(new DefaultRandom());
        var result = new GeometryGenerator().geometryCollection(gc).generate(new DefaultRandom());
        assertSame(gc, result);
    }
}
