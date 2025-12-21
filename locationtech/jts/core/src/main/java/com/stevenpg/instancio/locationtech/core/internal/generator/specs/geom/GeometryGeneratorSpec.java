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
import org.locationtech.jts.geom.*;

/**
 * Spec for generating a Geometry. The generator can return any JTS {@link Geometry}
 * subtype supported by this module. Optionally, a specific instance of a subtype may be
 * provided to construct the resulting {@link Geometry}.
 *
 * @since 1.0.0
 */
public interface GeometryGeneratorSpec extends GeneratorSpec<Geometry> {

    /**
     * Use the provided Point as the Geometry result.
     * @param point the point to use as the geometry
     * @return spec builder
     */
    GeometryGeneratorSpec point(Point point);

    /**
     * Use the provided LineString as the Geometry result.
     * @param lineString the point to use as the geometry
     * @return spec builder
     */
    GeometryGeneratorSpec lineString(LineString lineString);

    /**
     * Use the provided LinearRing as the Geometry result.
     * @param linearRing the point to use as the geometry
     * @return spec builder
     */
    GeometryGeneratorSpec linearRing(LinearRing linearRing);

    /**
     * Use the provided Polygon as the Geometry result.
     * @param polygon the point to use as the geometry
     * @return spec builder
     */
    GeometryGeneratorSpec polygon(Polygon polygon);

    /**
     * Use the provided GeometryCollection as the Geometry result.
     * @param geometryCollection the collection to use as the geometry
     * @return spec builder
     */
    GeometryGeneratorSpec geometryCollection(GeometryCollection geometryCollection);
}
