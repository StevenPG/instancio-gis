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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.MultiLineStringGenerator;
import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.*;

import java.util.List;

/**
 * Spec for generating a MultiLineString.
 */
public interface MultiLineStringGeneratorSpec extends GeneratorSpec<MultiLineString> {

    /**
     * Provide a list of linestrings to load into a multi-linestring.
     * @param lineStrings the linestrings to load
     * @return spec builder
     */
    MultiLineStringGenerator lineStrings(List<LineString> lineStrings);

    /**
     * Provide an optional GeometryFactory to use to generate the LineString.
     * Cannot be provided alongside the CoordinateSequence.
     * @param geometryFactory the geometry factory to use
     * @return spec builder
     */
    MultiLineStringGenerator geometryFactory(GeometryFactory geometryFactory);

    /**
     * Set the length of the generated MultiLineString.
     * @param length the number of coordinates must be >= 2
     * @return spec builder
     */
    MultiLineStringGenerator length(int length);
}
