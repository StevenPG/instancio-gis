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

package com.stevenpg.instancio.locationtech.core;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.CoordinateGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.geom.PointGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.CoordinateArraySequenceGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.PointGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.impl.CoordinateArraySequenceGeneratorSpec;

/**
 * Provides access to the Locationtech JTS core generator provider.
 *
 * @since 1.0.0
 */
public class GenLocationtechJtsCore {

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.Point.
     *
     * @return generator spec
     * @since 1.0.0
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator Spec for org.locationtech.jts.core.geom.CoordinateArraySequence.
     * @return generator spec
     */
    public static CoordinateArraySequenceGenerator coordinateArraySequence() {
        return new CoordinateArraySequenceGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.Coordinate.
     * @return generator
     */
    public static CoordinateGenerator coordinate() {
        return new CoordinateGenerator();
    }

    private GenLocationtechJtsCore() {
        // private constructor to prevent instantiation
    }

}
