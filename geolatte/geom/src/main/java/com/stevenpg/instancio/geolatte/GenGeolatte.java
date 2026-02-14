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

package com.stevenpg.instancio.geolatte;

import com.stevenpg.instancio.geolatte.internal.generator.GeometryCollectionGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.LineStringGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.LinearRingGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.MultiLineStringGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.MultiPointGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.MultiPolygonGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.PointGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.PolygonGenerator;

/**
 * Facade for accessing Geolatte Geom geometry generators.
 *
 * <p>This is the main entry point for obtaining generators for Geolatte geometry
 * types. Each method returns a configurable generator that produces random
 * instances of the corresponding geometry type.
 *
 * <p>Usage examples:
 * <pre>{@code
 * // Generate a random point in the San Francisco area
 * Point point = GenGeolatte.point()
 *     .xRange(-122.5, -122.3)
 *     .yRange(37.7, 37.8)
 *     .generate(random);
 *
 * // Generate a line string with 3 to 8 coordinates near London
 * LineString line = GenGeolatte.lineString()
 *     .xRange(-0.15, -0.05)
 *     .yRange(51.49, 51.53)
 *     .length(3, 8)
 *     .generate(random);
 *
 * // Generate a polygon in the NYC area
 * Polygon polygon = GenGeolatte.polygon()
 *     .xRange(-74.05, -73.90)
 *     .yRange(40.70, 40.80)
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenGeolatte {

    /**
     * Access to the Generator for {@link org.geolatte.geom.Point}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a point near Paris
     * Point point = GenGeolatte.point()
     *     .xRange(2.2, 2.4)
     *     .yRange(48.8, 48.9)
     *     .generate(random);
     * }</pre>
     *
     * @return a new PointGenerator instance
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator for {@link org.geolatte.geom.LineString}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a line string with 5 to 10 coordinates
     * LineString line = GenGeolatte.lineString()
     *     .xRange(-122.5, -122.3)
     *     .yRange(37.7, 37.8)
     *     .length(5, 10)
     *     .generate(random);
     * }</pre>
     *
     * @return a new LineStringGenerator instance
     */
    public static LineStringGenerator lineString() {
        return new LineStringGenerator();
    }

    /**
     * Access to the Generator for {@link org.geolatte.geom.LinearRing}.
     *
     * @return a new LinearRingGenerator instance
     */
    public static LinearRingGenerator linearRing() {
        return new LinearRingGenerator();
    }

    /**
     * Access to the Generator for {@link org.geolatte.geom.Polygon}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a polygon in central Tokyo
     * Polygon polygon = GenGeolatte.polygon()
     *     .xRange(139.6, 139.8)
     *     .yRange(35.6, 35.7)
     *     .generate(random);
     * }</pre>
     *
     * @return a new PolygonGenerator instance
     */
    public static PolygonGenerator polygon() {
        return new PolygonGenerator();
    }

    /**
     * Access to the Generator for {@link org.geolatte.geom.MultiPoint}.
     *
     * @return a new MultiPointGenerator instance
     */
    public static MultiPointGenerator multiPoint() {
        return new MultiPointGenerator();
    }

    /**
     * Access to the Generator for {@link org.geolatte.geom.MultiLineString}.
     *
     * @return a new MultiLineStringGenerator instance
     */
    public static MultiLineStringGenerator multiLineString() {
        return new MultiLineStringGenerator();
    }

    /**
     * Access to the Generator for {@link org.geolatte.geom.MultiPolygon}.
     *
     * @return a new MultiPolygonGenerator instance
     */
    public static MultiPolygonGenerator multiPolygon() {
        return new MultiPolygonGenerator();
    }

    /**
     * Access to the Generator for {@link org.geolatte.geom.GeometryCollection}.
     *
     * @return a new GeometryCollectionGenerator instance
     */
    public static GeometryCollectionGenerator geometryCollection() {
        return new GeometryCollectionGenerator();
    }

    private GenGeolatte() {
        // private constructor to prevent instantiation
    }
}
