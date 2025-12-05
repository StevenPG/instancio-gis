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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.CoordinateXYMGenerator;
import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYM;

/**
 * Generator Spec for generating a CoordinateXYM.
 *
 * @since 1.0.0
 */
public interface CoordinateXYMGeneratorSpec extends GeneratorSpec<CoordinateXYM> {

    /**
     * Specifies the latitude for the generated coordinate.
     *
     * @param latitude the latitude value
     * @return spec builder
     * @since 1.0.0
     */
    CoordinateXYMGeneratorSpec latitude(double latitude);

    /**
     * Specifies the longitude for the generated coordinate.
     *
     * @param longitude the longitude value
     * @return spec builder
     * @since 1.0.0
     */
    CoordinateXYMGeneratorSpec longitude(double longitude);

    /**
     * Specifies the measure for the generated coordinate.
     * @param measure the measure value
     * @return spec builder
     * @since 1.0.0
     */
    CoordinateXYMGenerator measure(double measure);

}
