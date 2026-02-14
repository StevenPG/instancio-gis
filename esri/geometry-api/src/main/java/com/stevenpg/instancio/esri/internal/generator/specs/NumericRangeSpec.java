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

package com.stevenpg.instancio.esri.internal.generator.specs;

/**
 * Specification for configuring numeric X and Y coordinate ranges
 * used by ESRI geometry generators.
 *
 * @param <T> the concrete generator type, for fluent method chaining
 * @since 1.0.0
 */
public interface NumericRangeSpec<T> {

    /**
     * Configure the X coordinate range for generated geometries.
     *
     * @param minX the minimum X value (longitude)
     * @param maxX the maximum X value (longitude)
     * @return the generator instance for fluent chaining
     */
    T xRange(double minX, double maxX);

    /**
     * Configure the Y coordinate range for generated geometries.
     *
     * @param minY the minimum Y value (latitude)
     * @param maxY the maximum Y value (latitude)
     * @return the generator instance for fluent chaining
     */
    T yRange(double minY, double maxY);
}
