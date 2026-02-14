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
import org.geolatte.geom.MultiPolygon;
import org.geolatte.geom.Polygon;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating a Geolatte {@link MultiPolygon} with WGS84 coordinates.
 * Produces a {@code MultiPolygon<G2D>} containing a configurable number of polygons
 * within configurable longitude/latitude bounds.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class MultiPolygonGenerator implements Generator<MultiPolygon>, NumericRangeSpec<MultiPolygonGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private int minLength = 2;
    private int maxLength = 3;

    /**
     * Default constructor.
     */
    public MultiPolygonGenerator() {
        // No custom instantiations needed
    }

    @Override
    public MultiPolygonGenerator xRange(final double minX, final double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public MultiPolygonGenerator yRange(final double minY, final double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the range of polygons in the generated multi-polygon.
     *
     * @param min minimum number of polygons
     * @param max maximum number of polygons
     * @return this generator for fluent chaining
     */
    public MultiPolygonGenerator length(final int min, final int max) {
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    /**
     * Generates a random {@link MultiPolygon} with WGS84 CRS.
     * Each constituent polygon is generated using {@link PolygonGenerator}.
     *
     * @param random the Instancio random provider
     * @return a randomly generated MultiPolygon
     */
    @Override
    @SuppressWarnings("unchecked")
    public MultiPolygon generate(final Random random) {
        int len = random.intRange(minLength, maxLength);
        PolygonGenerator polygonGen = new PolygonGenerator().xRange(minX, maxX).yRange(minY, maxY);
        Polygon<G2D>[] polygons = new Polygon[len];
        for (int i = 0; i < len; i++) {
            polygons[i] = polygonGen.generate(random);
        }
        return new MultiPolygon<>(polygons);
    }
}
