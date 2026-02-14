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

package com.stevenpg.instancio.spatial4j.internal.generator;

import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Shape;
import org.locationtech.spatial4j.shape.ShapeCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for creating Spatial4j {@link ShapeCollection} instances containing
 * a random number of {@link org.locationtech.spatial4j.shape.Point} shapes.
 *
 * <p>The number of shapes in the collection is controlled by the {@link #length(int, int)}
 * method. By default, collections contain between 2 and 5 points.</p>
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class ShapeCollectionGenerator implements Generator<ShapeCollection> {

    private int minLength = 2;
    private int maxLength = 5;

    /**
     * Default constructor.
     */
    public ShapeCollectionGenerator() {
        // No custom instantiations needed
    }

    /**
     * Sets the range for the number of shapes in the generated collection.
     *
     * @param minLength the minimum number of shapes (inclusive)
     * @param maxLength the maximum number of shapes (inclusive)
     * @return this generator for fluent chaining
     */
    public ShapeCollectionGenerator length(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        return this;
    }

    @Override
    public ShapeCollection generate(Random random) {
        int size = random.intRange(minLength, maxLength);
        PointGenerator pointGenerator = new PointGenerator();

        List<Shape> shapes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            shapes.add(pointGenerator.generate(random));
        }

        return new ShapeCollection<>(shapes, SpatialContext.GEO);
    }
}
