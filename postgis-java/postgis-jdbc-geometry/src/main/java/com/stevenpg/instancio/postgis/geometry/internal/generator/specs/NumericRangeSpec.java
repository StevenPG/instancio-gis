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
package com.stevenpg.instancio.postgis.geometry.internal.generator.specs;

public interface NumericRangeSpec<T> {

    /**
     * Sets the xRange of the spec
     * @param minX min X value for xRange
     * @param maxX max X value for xRange
     * @return reference to incoming spec
     */
    T xRange(double minX, double maxX);

    /**
     * Sets the yRange of the spec
     * @param minY min Y value for yRange
     * @param maxY max Y value for yRange
     * @return reference to incoming spec
     */
    T yRange(double minY, double maxY);

    /**
     * Sets the zRange
     * @param minZ min Z value
     * @param maxZ max Z value
     * @return reference to incoming spec
     */
    default T zRange(double minZ, double maxZ) {
        return (T) this;
    }

    /**
     * Sets the SRID
     * @param srid SRID
     * @return reference to incoming spec
     */
    default T srid(int srid) {
        return (T) this;
    }
}
