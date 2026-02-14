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
import org.geolatte.geom.Geometry;
import org.geolatte.geom.GeometryCollection;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating a Geolatte {@link GeometryCollection} with WGS84 coordinates.
 * Produces a {@code GeometryCollection<G2D>} containing a mix of points and line strings
 * within configurable longitude/latitude bounds.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class GeometryCollectionGenerator implements Generator<GeometryCollection>, NumericRangeSpec<GeometryCollectionGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private int minLength = 2;
    private int maxLength = 5;

    /**
     * Default constructor.
     */
    public GeometryCollectionGenerator() {
        // No custom instantiations needed
    }

    @Override
    public GeometryCollectionGenerator xRange(final double minX, final double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public GeometryCollectionGenerator yRange(final double minY, final double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the range of geometries in the generated collection.
     *
     * @param min minimum number of geometries
     * @param max maximum number of geometries
     * @return this generator for fluent chaining
     */
    public GeometryCollectionGenerator length(final int min, final int max) {
        this.minLength = min;
        this.maxLength = max;
        return this;
    }

    /**
     * Generates a random {@link GeometryCollection} with WGS84 CRS.
     * The collection contains a mix of points and line strings, randomly chosen
     * for each element in the collection.
     *
     * @param random the Instancio random provider
     * @return a randomly generated GeometryCollection
     */
    @Override
    @SuppressWarnings("unchecked")
    public GeometryCollection generate(final Random random) {
        int len = random.intRange(minLength, maxLength);
        PointGenerator pointGen = new PointGenerator().xRange(minX, maxX).yRange(minY, maxY);
        LineStringGenerator lineStringGen = new LineStringGenerator().xRange(minX, maxX).yRange(minY, maxY);

        Geometry<G2D>[] geometries = new Geometry[len];
        for (int i = 0; i < len; i++) {
            // Randomly alternate between points and line strings
            if (random.intRange(0, 1) == 0) {
                geometries[i] = pointGen.generate(random);
            } else {
                geometries[i] = lineStringGen.generate(random);
            }
        }
        return new GeometryCollection<>(geometries);
    }
}
