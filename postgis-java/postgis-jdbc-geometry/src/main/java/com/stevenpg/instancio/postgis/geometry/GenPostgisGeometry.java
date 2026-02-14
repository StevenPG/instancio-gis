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

package com.stevenpg.instancio.postgis.geometry;

import com.stevenpg.instancio.postgis.geometry.internal.generator.*;

/**
 * Facade for accessing PostGIS JDBC geometry type generators.
 *
 * <p>Provides factory methods for creating configurable generators for PostGIS
 * geometry types such as {@code Point}, {@code LineString}, {@code Polygon},
 * and their multi-geometry counterparts.
 *
 * <p>Usage examples:
 * <pre>{@code
 * // Generate a random PostGIS point in the SF area
 * Point point = GenPostgisGeometry.point()
 *     .xRange(-122.5, -122.3)
 *     .yRange(37.7, 37.8)
 *     .generate(random);
 *
 * // Generate a line string with coordinate ranges
 * LineString line = GenPostgisGeometry.lineString()
 *     .xRange(-74.05, -73.90)
 *     .yRange(40.70, 40.80)
 *     .generate(random);
 *
 * // Generate a polygon with coordinate ranges
 * Polygon polygon = GenPostgisGeometry.polygon()
 *     .xRange(-0.2, 0.0)
 *     .yRange(51.4, 51.6)
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenPostgisGeometry {

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.Geometry}.
     * @return generator
     */
    public static GeometryGenerator geometry() {
        return new GeometryGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.Point}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a 3D point near Tokyo
     * Point point = GenPostgisGeometry.point()
     *     .xRange(139.6, 139.8)
     *     .yRange(35.6, 35.7)
     *     .zRange(0, 100)
     *     .generate(random);
     * }</pre>
     *
     * @return generator
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.LineString}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a line string in central London
     * LineString line = GenPostgisGeometry.lineString()
     *     .xRange(-0.15, -0.05)
     *     .yRange(51.49, 51.53)
     *     .generate(random);
     * }</pre>
     *
     * @return generator
     */
    public static LineStringGenerator lineString() {
        return new LineStringGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.Polygon}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a polygon in the NYC area
     * Polygon polygon = GenPostgisGeometry.polygon()
     *     .xRange(-74.05, -73.90)
     *     .yRange(40.70, 40.80)
     *     .generate(random);
     * }</pre>
     *
     * @return generator
     */
    public static PolygonGenerator polygon() {
        return new PolygonGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.MultiPoint}.
     * @return generator
     */
    public static MultiPointGenerator multiPoint() {
        return new MultiPointGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.MultiLineString}.
     * @return generator
     */
    public static MultiLineStringGenerator multiLineString() {
        return new MultiLineStringGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.MultiPolygon}.
     * @return generator
     */
    public static MultiPolygonGenerator multiPolygon() {
        return new MultiPolygonGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.GeometryCollection}.
     * @return generator
     */
    public static GeometryCollectionGenerator geometryCollection() {
        return new GeometryCollectionGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.PGgeometry}.
     * @return generator
     */
    public static PGgeometryGenerator pgGeometry() {
        return new PGgeometryGenerator();
    }

    private GenPostgisGeometry() {
        // private constructor to prevent instantiation
    }
}
