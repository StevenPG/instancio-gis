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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiLineStringGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var multiLineString = Instancio.create(MultiLineString.class);
        assertNotNull(multiLineString);
        assertTrue(multiLineString.getNumGeometries() >= 2);
        assertTrue(multiLineString.getNumGeometries() <= 10);
    }

    @Test
    void lineStrings() {
        var geometryFactory = new GeometryFactory();
        var coordinates1 = new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 1)};
        var coordinates2 = new Coordinate[]{new Coordinate(2, 2), new Coordinate(3, 3)};
        var lineString1 = geometryFactory.createLineString(coordinates1);
        var lineString2 = geometryFactory.createLineString(coordinates2);
        List<LineString> lineStrings = Arrays.asList(lineString1, lineString2);

        var result = new MultiLineStringGenerator()
                .lineStrings(lineStrings)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(2, result.getNumGeometries());
        assertEquals(lineString1, result.getGeometryN(0));
        assertEquals(lineString2, result.getGeometryN(1));
    }

    @Test
    void length() {
        var result = new MultiLineStringGenerator()
                .length(3)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(3, result.getNumGeometries());
    }

    @Test
    void geometryFactory() {
        var customGeometryFactory = new GeometryFactory();
        var result = new MultiLineStringGenerator()
                .geometryFactory(customGeometryFactory)
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertEquals(customGeometryFactory, result.getFactory());
    }

    @Test
    void within() {
        var result = new MultiLineStringGenerator()
                .within(new Envelope(0, 10, 0, 10))
                .generate(new DefaultRandom());

        assertNotNull(result);
        for (int i = 0; i < result.getNumGeometries(); i++) {
            var lineString = (LineString) result.getGeometryN(i);
            var envelope = lineString.getEnvelopeInternal();
            assertTrue(envelope.getMinX() >= 0 && envelope.getMaxX() <= 10);
            assertTrue(envelope.getMinY() >= 0 && envelope.getMaxY() <= 10);
        }
    }

    @Test
    void generate() {
        var result = new MultiLineStringGenerator().generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getNumGeometries() >= 2);
        assertTrue(result.getNumGeometries() <= 10);
    }
}