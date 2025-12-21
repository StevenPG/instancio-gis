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
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateList;

import java.util.List;

/**
 * Spec for generating a CoordinateList.
 */
public interface CoordinateListGeneratorSpec extends GeneratorSpec<CoordinateList> {

    /**
     * Provide coordinates to populate the generated CoordinateList.
     *
     * @param coordinates coordinates to include
     * @return spec builder
     * @since 1.0.0
     */
    CoordinateListGeneratorSpec coordinateList(List<Coordinate> coordinates);

    /**
     * Set the exact length of the generated list.
     * @param length number of coordinates, must be >= 1
     * @return spec builder
     */
    CoordinateListGeneratorSpec length(int length);

    /**
     * Set a range for the number of generated coordinates.
     * @param min minimum number of coordinates, must be >= 1
     * @param max maximum number of coordinates, must be >= min
     * @return spec builder
     */
    CoordinateListGeneratorSpec length(int min, int max);
}
