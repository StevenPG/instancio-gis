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
 * Provides access to the Geolatte Geom generator provider.
 * This is the main entry point for obtaining generators for Geolatte geometry types.
 *
 * <p>Example usage:
 * <pre>{@code
 * PointGenerator gen = GenGeolatte.point().xRange(-10, 10).yRange(-5, 5);
 * Point result = gen.generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenGeolatte {

    /**
     * Access to the Generator for {@link org.geolatte.geom.Point}.
     *
     * @return a new PointGenerator instance
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator for {@link org.geolatte.geom.LineString}.
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
