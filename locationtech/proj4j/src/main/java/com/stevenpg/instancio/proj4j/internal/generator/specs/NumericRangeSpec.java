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

package com.stevenpg.instancio.proj4j.internal.generator.specs;

/**
 * Specification for generators that support numeric range constraints
 * on x, y, and optionally z coordinates.
 *
 * @param <T> the concrete generator type for fluent method chaining
 * @since 1.0.0
 */
public interface NumericRangeSpec<T> {

    /**
     * Sets the minimum and maximum bounds for the x coordinate.
     *
     * @param minX the minimum x value (inclusive)
     * @param maxX the maximum x value (exclusive)
     * @return this generator instance for method chaining
     */
    T xRange(double minX, double maxX);

    /**
     * Sets the minimum and maximum bounds for the y coordinate.
     *
     * @param minY the minimum y value (inclusive)
     * @param maxY the maximum y value (exclusive)
     * @return this generator instance for method chaining
     */
    T yRange(double minY, double maxY);

    /**
     * Sets the minimum and maximum bounds for the z coordinate.
     * The default implementation is a no-op, returning the current instance unchanged.
     *
     * @param minZ the minimum z value (inclusive)
     * @param maxZ the maximum z value (exclusive)
     * @return this generator instance for method chaining
     */
    @SuppressWarnings("unchecked")
    default T zRange(double minZ, double maxZ) {
        return (T) this;
    }
}
