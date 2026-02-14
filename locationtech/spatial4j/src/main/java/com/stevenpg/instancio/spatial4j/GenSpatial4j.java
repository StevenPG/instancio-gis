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
 * Facade for accessing Spatial4j shape generators.
 *
 * <p>This facade offers static factory methods for all supported Spatial4j shape
 * generators, enabling fluent configuration and generation of test data.
 *
 * <p>Usage examples:
 * <pre>{@code
 * // Generate a random point in the SF area
 * Point point = GenSpatial4j.point()
 *     .xRange(-122.5, -122.3)
 *     .yRange(37.7, 37.8)
 *     .generate(random);
 *
 * // Generate a circle with custom radius near London
 * Circle circle = GenSpatial4j.circle()
 *     .xRange(-0.15, -0.05)
 *     .yRange(51.49, 51.53)
 *     .radiusRange(1.0, 5.0)
 *     .generate(random);
 *
 * // Generate a bounding rectangle
 * Rectangle rect = GenSpatial4j.rectangle()
 *     .xRange(-74.1, -73.9)
 *     .yRange(40.7, 40.8)
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenSpatial4j {

    /**
     * Access to the Generator for {@link org.locationtech.spatial4j.shape.Point}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a point near Tokyo
     * Point point = GenSpatial4j.point()
     *     .xRange(139.6, 139.8)
     *     .yRange(35.6, 35.7)
     *     .generate(random);
     * }</pre>
     *
     * @return a new point generator
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator for {@link org.locationtech.spatial4j.shape.Rectangle}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a bounding rectangle in the NYC area
     * Rectangle rect = GenSpatial4j.rectangle()
     *     .xRange(-74.05, -73.90)
     *     .yRange(40.70, 40.80)
     *     .generate(random);
     * }</pre>
     *
     * @return a new rectangle generator
     */
    public static RectangleGenerator rectangle() {
        return new RectangleGenerator();
    }

    /**
     * Access to the Generator for {@link org.locationtech.spatial4j.shape.Circle}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a circle with a 1-5 degree radius near Paris
     * Circle circle = GenSpatial4j.circle()
     *     .xRange(2.2, 2.4)
     *     .yRange(48.8, 48.9)
     *     .radiusRange(1.0, 5.0)
     *     .generate(random);
     * }</pre>
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
