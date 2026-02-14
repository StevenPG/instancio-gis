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

package com.stevenpg.app.geolatte;

import com.stevenpg.instancio.geolatte.GenGeolatte;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Example tests for Geolatte geometry generators.
 *
 * <p>Note: {@code Instancio.create()} tests for Geolatte types (Point, LineString, etc.)
 * are intentionally omitted from this example project because the raw class names conflict
 * with JTS, PostGIS, and Spatial4j types that are also on the classpath via their respective
 * SPI providers. The individual geolatte-geom module tests verify that SPI auto-loading
 * works correctly when there are no classpath conflicts.</p>
 */
class GeolatteGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGeneratePointWithGenerator() {
        var p = GenGeolatte.point()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .generate(random);
        assertNotNull(p);
    }

    @Test
    void shouldGenerateLineStringWithGenerator() {
        var ls = GenGeolatte.lineString()
                .xRange(-100, 100)
                .yRange(-50, 50)
                .generate(random);
        assertNotNull(ls);
    }
}
