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
 * Provides access to the PostGIS geometry generator provider.
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
     * @return generator
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.LineString}.
     * @return generator
     */
    public static LineStringGenerator lineString() {
        return new LineStringGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.geometry.Polygon}.
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
