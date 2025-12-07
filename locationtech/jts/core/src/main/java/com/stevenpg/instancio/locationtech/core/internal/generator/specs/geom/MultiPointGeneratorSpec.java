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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.MultiPointGenerator;
import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;

import java.util.List;

/**
 * Spec for generating a MultiPoint.
 */
public interface MultiPointGeneratorSpec extends GeneratorSpec<MultiPoint> {

    /**
     * Provide a list of points to load into a multi-point.
     * @param points the points to load
     * @return spec builder
     */
    MultiPointGenerator points(List<Point> points);

    /**
     * Provide an optional GeometryFactory to use to generate the LineString.
     * Cannot be provided alongside the CoordinateSequence.
     * @param geometryFactory the geometry factory to use
     * @return spec builder
     */
    MultiPointGenerator geometryFactory(GeometryFactory geometryFactory);

    /**
     * Set the length of the generated LineString.
     * @param length the number of coordinates must be >= 2
     * @return spec builder
     */
    MultiPointGenerator length(int length);
}
