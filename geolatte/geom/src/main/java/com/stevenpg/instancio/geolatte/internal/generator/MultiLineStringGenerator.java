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

package com.stevenpg.instancio.geolatte.internal.generator;

import com.stevenpg.instancio.geolatte.internal.generator.specs.NumericRangeSpec;
import org.geolatte.geom.G2D;
import org.geolatte.geom.LineString;
import org.geolatte.geom.MultiLineString;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating a Geolatte {@link MultiLineString} with WGS84 coordinates.
 * Produces a {@code MultiLineString<G2D>} containing a configurable number of line strings
 * within configurable longitude/latitude bounds.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class MultiLineStringGenerator implements Generator<MultiLineString>, NumericRangeSpec<MultiLineStringGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private int minLength = 2;
    private int maxLength = 3;

    /**
     * Default constructor.
     */
    public MultiLineStringGenerator() {
        // No custom instantiations needed
    }

    @Override
    public MultiLineStringGenerator xRange(final double minX, final double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public MultiLineStringGenerator yRange(final double minY, final double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the range of line strings in the generated multi-line-string.
     *
     * @param min minimum number of line strings
     * @param max maximum number of line strings
     * @return this generator for fluent chaining
     */
    public MultiLineStringGenerator length(final int min, final int max) {
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    /**
     * Generates a random {@link MultiLineString} with WGS84 CRS.
     * Each constituent line string is generated using {@link LineStringGenerator}.
     *
     * @param random the Instancio random provider
     * @return a randomly generated MultiLineString
     */
    @Override
    @SuppressWarnings("unchecked")
    public MultiLineString generate(final Random random) {
        int len = random.intRange(minLength, maxLength);
        LineStringGenerator lineStringGen = new LineStringGenerator().xRange(minX, maxX).yRange(minY, maxY);
        LineString<G2D>[] lineStrings = new LineString[len];
        for (int i = 0; i < len; i++) {
            lineStrings[i] = lineStringGen.generate(random);
        }
        return new MultiLineString<>(lineStrings);
    }
}
