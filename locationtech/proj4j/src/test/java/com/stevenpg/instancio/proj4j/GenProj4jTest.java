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

package com.stevenpg.instancio.proj4j;

import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;

import static org.junit.jupiter.api.Assertions.*;

class GenProj4jTest {

    @RepeatedTest(10)
    void projCoordinate() {
        var generator = GenProj4j.projCoordinate();
        assertNotNull(generator);

        var result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertInstanceOf(ProjCoordinate.class, result);
        assertTrue(result.x >= -180 && result.x <= 180,
                "x should be within [-180, 180] but was " + result.x);
        assertTrue(result.y >= -90 && result.y <= 90,
                "y should be within [-90, 90] but was " + result.y);
    }

    @RepeatedTest(10)
    void crs() {
        var generator = GenProj4j.crs();
        assertNotNull(generator);

        var result = generator.generate(new DefaultRandom());

        assertNotNull(result);
        assertInstanceOf(CoordinateReferenceSystem.class, result);
        assertNotNull(result.getProjection(), "CRS projection should not be null");
    }
}
