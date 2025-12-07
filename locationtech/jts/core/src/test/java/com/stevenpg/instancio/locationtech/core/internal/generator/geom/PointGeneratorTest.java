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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Point;

import static org.junit.jupiter.api.Assertions.*;

class PointGeneratorTest {

    @RepeatedTest(5)
    void create() {
        var point = Instancio.create(Point.class);
        assertNotNull(point);
    }

    @Test
    void coordinate() {
        var result = new PointGenerator()
                .coordinate(-75d, 40d)
                .generate(new DefaultRandom());

        assertNotNull(result);
    }

    @Test
    void within() {
        var result = new PointGenerator()
                .within(new Envelope(0, 10, 0, 10))
                .generate(new DefaultRandom());

        assertNotNull(result);
        assertTrue(result.getCoordinateSequence().getCoordinate(0).getX() >= 0 &&
                result.getCoordinateSequence().getCoordinate(0).getX() <= 10);
        assertTrue(result.getCoordinateSequence().getCoordinate(0).getY() >= 0 &&
                result.getCoordinateSequence().getCoordinate(0).getY() <= 10);
    }

    @Test
    void generate() {
        var result = new PointGenerator().generate(new DefaultRandom());

        assertNotNull(result);
    }
}