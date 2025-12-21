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

package com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.GeometryCollectionGenerator;
import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.*;

import java.util.List;

/**
 * Spec for generating a GeometryCollection.
 *
 * @since 1.0.0
 */
public interface GeometryCollectionGeneratorSpec extends GeneratorSpec<GeometryCollection> {

    /**
     * Provide an optional GeometryFactory to use to generate the GeometryCollection.
     * @param geometryFactory the geometry factory
     * @return spec builder
     */
    GeometryCollectionGenerator geometryFactory(GeometryFactory geometryFactory);

    /**
     * Specify the number of members for a heterogeneous GeometryCollection.
     * For Multi* subtypes selected during random generation, member counts are
     * controlled by their respective generators.
     * @param length the number of members in the collection
     * @return spec builder
     */
    GeometryCollectionGenerator length(int length);

    /**
     * Provide an explicit list of geometries to include in the collection (heterogeneous).
     * @param geometries the geometries to include
     * @return spec builder
     */
    GeometryCollectionGenerator geometries(List<Geometry> geometries);

    /**
     * Use the provided MultiPoint as the GeometryCollection result.
     * @param multiPoint the multi-point to use as the geometry
     * @return spec builder
     */
    GeometryCollectionGenerator multiPoint(MultiPoint multiPoint);

    /**
     * Use the provided MultiLineString as the GeometryCollection result.
     * @param multiLineString the multi-line-string to use as the geometry
     * @return spec builder
     */
    GeometryCollectionGenerator multiLineString(MultiLineString multiLineString);

    /**
     * Use the provided MultiPolygon as the GeometryCollection result.
     * @param multiPolygon the multi-polygon to use as the geometry
     * @return spec builder
     */
    GeometryCollectionGenerator multiPolygon(MultiPolygon multiPolygon);

    /**
     * Use the provided GeometryCollection as the result.
     * @param geometryCollection the collection to use as the geometry
     * @return spec builder
     */
    GeometryCollectionGenerator geometryCollection(GeometryCollection geometryCollection);
}
