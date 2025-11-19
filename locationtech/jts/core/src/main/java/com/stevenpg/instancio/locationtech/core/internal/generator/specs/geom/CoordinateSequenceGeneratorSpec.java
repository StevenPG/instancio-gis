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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.CoordinateArraySequenceGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.impl.CoordinateArraySequenceGeneratorSpec;
import org.instancio.generator.GeneratorSpec;
import org.instancio.generator.specs.NullableGeneratorSpec;
import org.locationtech.jts.geom.CoordinateSequence;

/**
 * Spec for generating a CoordinateSequence.
 */
public interface CoordinateSequenceGeneratorSpec extends GeneratorSpec<CoordinateSequence> {

    /**
     * Provide a coordinate sequence to generate.
     *
     * @param coordinateSequence the coordinate sequence to generate
     * @return spec builder
     * @since 1.0.0
     */
    CoordinateSequenceGeneratorSpec coordinateSequence(CoordinateSequence coordinateSequence);

    /**
     * Set the length of the generated coordinate sequence.
     * @param length the number of coordinates must be >= 1
     * @return spec builder
     */
    CoordinateSequenceGeneratorSpec length(int length);

    /**
     * Set a range for the number of coordinates generated.
     * @param min minimum number of coordinates, must be >= 1
     * @param max maximum number of coordinates, must be >= min
     * @return spec builder
     */
    CoordinateSequenceGeneratorSpec length(int min, int max);

}
