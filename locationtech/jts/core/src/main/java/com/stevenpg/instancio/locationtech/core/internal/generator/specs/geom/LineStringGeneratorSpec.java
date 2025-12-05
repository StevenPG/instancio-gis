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
import org.instancio.generator.specs.NullableGeneratorSpec;
import org.locationtech.jts.geom.LineString;

/**
 * Spec for generating a LineString.
 */
public interface LineStringGeneratorSpec extends GeneratorSpec<LineString>, Envelopable {

    // /**
    //  * Generate a valid LineString.
    //  *
    //  * @param latitude the latitude of the point
    //  * @param longitude the longitude of the point
    //  * @return spec builder
    //  * @since 4.4.0
    //  */
    // PointGeneratorSpec coordinate(double longitude, double latitude);

    // CoordinateSequence

    // GeometryFactory (can be provided, otherwise default is used

    /**
     * Generate a valid LineString based on the provided coordinate sequence. Not compatible
     * with providing a GeometryFactory.
     */
    CoordinateSequenceSpec coordinateSequence(CoordinateSequence coordinateSequence);

    
    /**
     * Provide an optional GeometryFactory to utilize to generate the LineString.
     * Cannot be provided alongside the CoordinateSequence.
     */
    CoordinateSequenceSpec geometryFactory(GeometryFactory geometryFactory);

}
