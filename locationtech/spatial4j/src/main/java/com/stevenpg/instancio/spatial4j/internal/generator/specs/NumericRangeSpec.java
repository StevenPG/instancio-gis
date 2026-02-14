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

package com.stevenpg.instancio.spatial4j.internal.generator.specs;

/**
 * Specification for generators that support configurable X and Y numeric ranges.
 *
 * @param <T> the concrete generator type, used for fluent method chaining
 * @since 1.0.0
 */
public interface NumericRangeSpec<T> {

    /**
     * Sets the X-axis range for generated values (typically longitude).
     *
     * @param minX the minimum X value (inclusive)
     * @param maxX the maximum X value (inclusive)
     * @return the generator instance for fluent chaining
     */
    T xRange(double minX, double maxX);

    /**
     * Sets the Y-axis range for generated values (typically latitude).
     *
     * @param minY the minimum Y value (inclusive)
     * @param maxY the maximum Y value (inclusive)
     * @return the generator instance for fluent chaining
     */
    T yRange(double minY, double maxY);
}
