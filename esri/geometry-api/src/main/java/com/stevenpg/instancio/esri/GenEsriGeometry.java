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

package com.stevenpg.instancio.esri;

import com.stevenpg.instancio.esri.internal.generator.*;

/**
 * Facade providing convenient static factory methods for creating
 * ESRI geometry generators.
 *
 * <p>Each method returns a generator that can be further configured via
 * fluent API methods (e.g., coordinate ranges) before generating values.</p>
 *
 * <p>Usage examples:
 * <pre>{@code
 * // Generate a random point in the SF area
 * Point point = GenEsriGeometry.point()
 *     .xRange(-122.5, -122.3)
 *     .yRange(37.7, 37.8)
 *     .generate(random);
 *
 * // Generate a polyline with 5 to 10 vertices near NYC
 * Polyline polyline = GenEsriGeometry.polyline()
 *     .xRange(-74.05, -73.90)
 *     .yRange(40.70, 40.80)
 *     .length(5, 10)
 *     .generate(random);
 *
 * // Generate a polygon with 4 to 8 vertices
 * Polygon polygon = GenEsriGeometry.polygon()
 *     .xRange(-0.2, 0.0)
 *     .yRange(51.4, 51.6)
 *     .vertices(4, 8)
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenEsriGeometry {

    /**
     * Access to the Generator for {@link com.esri.core.geometry.Point}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a point near London
     * Point point = GenEsriGeometry.point()
     *     .xRange(-0.15, -0.05)
     *     .yRange(51.49, 51.53)
     *     .generate(random);
     * }</pre>
     *
     * @return a new point generator
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator for {@link com.esri.core.geometry.MultiPoint}.
     *
     * @return a new multi-point generator
     */
    public static MultiPointGenerator multiPoint() {
        return new MultiPointGenerator();
    }

    /**
     * Access to the Generator for {@link com.esri.core.geometry.Envelope}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate an envelope covering part of Tokyo
     * Envelope env = GenEsriGeometry.envelope()
     *     .xRange(139.6, 139.8)
     *     .yRange(35.6, 35.7)
     *     .generate(random);
     * }</pre>
     *
     * @return a new envelope generator
     */
    public static EnvelopeGenerator envelope() {
        return new EnvelopeGenerator();
    }

    /**
     * Access to the Generator for {@link com.esri.core.geometry.Polyline}.
     *
     * @return a new polyline generator
     */
    public static PolylineGenerator polyline() {
        return new PolylineGenerator();
    }

    /**
     * Access to the Generator for {@link com.esri.core.geometry.Polygon}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a polygon with 6 to 12 vertices near Paris
     * Polygon polygon = GenEsriGeometry.polygon()
     *     .xRange(2.2, 2.4)
     *     .yRange(48.8, 48.9)
     *     .vertices(6, 12)
     *     .generate(random);
     * }</pre>
     *
     * @return a new polygon generator
     */
    public static PolygonGenerator polygon() {
        return new PolygonGenerator();
    }

    private GenEsriGeometry() {
        // private constructor to prevent instantiation
    }
}
