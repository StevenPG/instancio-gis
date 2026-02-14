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
 * <p>Example usage:</p>
 * <pre>{@code
 * Point point = GenEsriGeometry.point()
 *     .xRange(-100, -80)
 *     .yRange(30, 50)
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenEsriGeometry {

    /**
     * Access to the Generator for {@link com.esri.core.geometry.Point}.
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
     * @return a new polygon generator
     */
    public static PolygonGenerator polygon() {
        return new PolygonGenerator();
    }

    private GenEsriGeometry() {
        // private constructor to prevent instantiation
    }
}
