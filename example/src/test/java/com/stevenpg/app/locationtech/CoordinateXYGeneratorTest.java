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
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.CoordinateXY;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CoordinateXYGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void coordinateXYGenerator() {
        assertInstanceOf(CoordinateXY.class, GenLocationtechJtsCore.coordinateXY().generate(random));
    }

    @Test
    void coordinateXYGeneratorLatLng() {
        var coordinate = GenLocationtechJtsCore.coordinateXY()
                .latitude(30)
                .longitude(-60)
                .generate(random);
        assertInstanceOf(CoordinateXY.class, coordinate);
        assertEquals(30, coordinate.y);
        assertEquals(-60, coordinate.x);
        assertEquals(Double.NaN, coordinate.z);
    }

    @Test
    void coordinateXYGeneratorWithRandom() {
        assertInstanceOf(CoordinateXY.class, GenLocationtechJtsCore.coordinateXY().generate(new DefaultRandom()));
    }

    @Test
    void coordinateXYGeneratorSpi() {
        var coordinate = Instancio.create(CoordinateXY.class);
        assertInstanceOf(CoordinateXY.class, coordinate);
        assertNotEquals(0, coordinate.x);
        assertNotEquals(0, coordinate.y);
    }

}