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
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

/**
 * Spec for generating a MultiPolygon.
 *
 * @since 1.0.0
 */
public interface MultiPolygonGeneratorSpec extends GeneratorSpec<MultiPolygon> {

    /**
     * Provide a list of polygons to load into a multi-polygon.
     * @param polygons the polygons to load
     * @return spec builder
     */
    MultiPolygonGeneratorSpec polygons(List<Polygon> polygons);

    /**
     * Provide an optional GeometryFactory to use to generate the MultiPolygon.
     * Cannot be provided alongside the polygon list.
     * @param geometryFactory the geometry factory to use
     * @return spec builder
     */
    MultiPolygonGeneratorSpec geometryFactory(GeometryFactory geometryFactory);

    /**
     * Set the length of the generated MultiPolygon.
     * @param length the number of polygons must be >= 1
     * @return spec builder
     */
    MultiPolygonGeneratorSpec length(int length);
}
