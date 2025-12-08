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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.LinearRingGenerator;
import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;

/**
 * Spec for generating a LinearRing.
 */
public interface LinearRingGeneratorSpec extends GeneratorSpec<LinearRing> {

    /**
     * Generate a valid LinearRing based on the provided coordinate sequence. The sequence
     * will be automatically closed if it's not already closed. Not compatible with providing a GeometryFactory.
     * @param coordinateSequence the coordinate sequence to generate
     * @return spec builder
     */
    LinearRingSpec coordinateSequence(CoordinateSequence coordinateSequence);

    /**
     * Provide an optional GeometryFactory to use to generate the LinearRing.
     * Cannot be provided alongside the CoordinateSequence.
     * @param geometryFactory the geometry factory to use
     * @return spec builder
     */
    LinearRingSpec geometryFactory(GeometryFactory geometryFactory);

    /**
     * Set the number of unique coordinates in the generated LinearRing. The actual ring will have
     * this many unique coordinates plus one additional coordinate to close the ring (first coordinate repeated at the end).
     * @param length the number of unique coordinates must be >= 3 (minimum for a valid LinearRing)
     * @return spec builder
     */
    LinearRingGenerator length(int length);

}
