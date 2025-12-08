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

import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

/**
 * Spec for generating a Polygon.
 */
public interface PolygonGeneratorSpec extends GeneratorSpec<Polygon> {

    /**
     * Generate a Polygon based on the provided exterior ring. Not compatible
     * with providing a GeometryFactory.
     * @param exteriorRing the exterior ring to generate the polygon from
     * @return spec builder
     */
    PolygonSpec exteriorRing(LinearRing exteriorRing);

    /**
     * Generate a Polygon based on the provided exterior ring and holes. Not compatible
     * with providing a GeometryFactory.
     * @param exteriorRing the exterior ring to generate the polygon from
     * @param holes the holes (interior rings) to include in the polygon
     * @return spec builder
     */
    PolygonSpec rings(LinearRing exteriorRing, LinearRing... holes);

    /**
     * Provide an optional GeometryFactory to use to generate the Polygon.
     * Cannot be provided alongside the LinearRing specifications.
     * @param geometryFactory the geometry factory to use
     * @return spec builder
     */
    PolygonSpec geometryFactory(GeometryFactory geometryFactory);

    /**
     * Set the number of vertices for the exterior ring of the generated Polygon.
     * @param vertices the number of vertices must be >= 3
     * @return spec builder
     */
    PolygonGeneratorSpec vertices(int vertices);

    /**
     * Set the number of holes (interior rings) to generate in the Polygon.
     * @param holes the number of holes to generate
     * @return spec builder
     */
    PolygonGeneratorSpec holes(int holes);

}
