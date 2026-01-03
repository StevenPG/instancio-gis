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
import org.locationtech.jts.geom.CoordinateXYZM;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateXYZMGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void coordinateXYZMGenerator() {
        assertInstanceOf(CoordinateXYZM.class, GenLocationtechJtsCore.coordinateXYZM().generate(random));
    }

    @Test
    void coordinateXYZMGeneratorLatLng() {
        var coordinate = GenLocationtechJtsCore.coordinateXYZM()
                .latitude(30)
                .longitude(-60)
                .altitude(26)
                .measure(10)
                .generate(random);
        assertInstanceOf(CoordinateXYZM.class, coordinate);
        assertEquals(30, coordinate.y);
        assertEquals(-60, coordinate.x);
        assertEquals(26, coordinate.z);
        assertEquals(10, coordinate.getM());
    }

    @Test
    void coordinateXYZMGeneratorWithRandom() {
        assertInstanceOf(CoordinateXYZM.class, GenLocationtechJtsCore.coordinateXYZM().generate(new DefaultRandom()));
    }

    @Test
    void coordinateXYZMGeneratorSpi() {
        var coordinate = Instancio.create(CoordinateXYZM.class);
        assertInstanceOf(CoordinateXYZM.class, coordinate);
        assertNotEquals(0, coordinate.x);
        assertNotEquals(0, coordinate.y);
        assertNotEquals(0, coordinate.z);
        assertNotEquals(0, coordinate.getM());
    }

}