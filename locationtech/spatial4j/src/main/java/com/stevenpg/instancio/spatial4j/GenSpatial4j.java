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

package com.stevenpg.instancio.spatial4j;

import com.stevenpg.instancio.spatial4j.internal.generator.CircleGenerator;
import com.stevenpg.instancio.spatial4j.internal.generator.PointGenerator;
import com.stevenpg.instancio.spatial4j.internal.generator.RectangleGenerator;
import com.stevenpg.instancio.spatial4j.internal.generator.ShapeCollectionGenerator;

/**
 * Provides access to the Spatial4j generator providers.
 *
 * <p>This facade offers static factory methods for all supported Spatial4j shape
 * generators, enabling fluent configuration and generation of test data.</p>
 *
 * @since 1.0.0
 */
public final class GenSpatial4j {

    /**
     * Access to the Generator for {@link org.locationtech.spatial4j.shape.Point}.
     *
     * @return a new point generator
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator for {@link org.locationtech.spatial4j.shape.Rectangle}.
     *
     * @return a new rectangle generator
     */
    public static RectangleGenerator rectangle() {
        return new RectangleGenerator();
    }

    /**
     * Access to the Generator for {@link org.locationtech.spatial4j.shape.Circle}.
     *
     * @return a new circle generator
     */
    public static CircleGenerator circle() {
        return new CircleGenerator();
    }

    /**
     * Access to the Generator for {@link org.locationtech.spatial4j.shape.ShapeCollection}.
     *
     * @return a new shape collection generator
     */
    public static ShapeCollectionGenerator shapeCollection() {
        return new ShapeCollectionGenerator();
    }

    private GenSpatial4j() {
        // private constructor to prevent instantiation
    }
}
