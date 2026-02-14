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

package com.stevenpg.instancio.geolatte.internal.generator.specs;

/**
 * Spec interface for generators that support configurable X and Y coordinate ranges.
 *
 * @param <T> the concrete generator type, returned for fluent chaining
 * @since 1.0.0
 */
public interface NumericRangeSpec<T> {

    /**
     * Configure the X (longitude) range for generated geometries.
     *
     * @param minX minimum X value (inclusive)
     * @param maxX maximum X value (inclusive)
     * @return this generator for fluent chaining
     */
    T xRange(double minX, double maxX);

    /**
     * Configure the Y (latitude) range for generated geometries.
     *
     * @param minY minimum Y value (inclusive)
     * @param maxY maximum Y value (inclusive)
     * @return this generator for fluent chaining
     */
    T yRange(double minY, double maxY);
}
