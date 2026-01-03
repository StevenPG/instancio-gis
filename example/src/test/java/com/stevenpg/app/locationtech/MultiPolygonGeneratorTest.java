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

package com.stevenpg.app.locationtech;

import org.instancio.Random;
import org.instancio.support.DefaultRandom;

import com.stevenpg.instancio.locationtech.core.GenLocationtechJtsCore;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.MultiPolygon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiPolygonGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void multiPolygonGenerator() {
        assertInstanceOf(MultiPolygon.class, GenLocationtechJtsCore.multiPolygon().generate(random));
    }

    @Test
    void multiPolygonGeneratorWithRandom() {
        assertInstanceOf(MultiPolygon.class, GenLocationtechJtsCore.multiPolygon().generate(new DefaultRandom()));
    }

    @Test
    void instancioCreateMultiPolygon() {
        var multiPolygon = Instancio.create(MultiPolygon.class);
        assertInstanceOf(MultiPolygon.class, multiPolygon);
        assertTrue(multiPolygon.getNumGeometries() >= 1);
    }

    @Test
    void multiPolygonGeneratorWithLength() {
        var multiPolygon = GenLocationtechJtsCore.multiPolygon()
                .length(3)
                .generate(new DefaultRandom());
        assertInstanceOf(MultiPolygon.class, multiPolygon);
        assertEquals(3, multiPolygon.getNumGeometries());
    }
}
