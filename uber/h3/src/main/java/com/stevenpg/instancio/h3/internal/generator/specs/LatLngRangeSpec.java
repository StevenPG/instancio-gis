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

package com.stevenpg.instancio.h3.internal.generator.specs;

/**
 * Specification for constraining the latitude and longitude ranges
 * used during random geographic coordinate generation.
 *
 * @param <T> the concrete type returned for method chaining
 * @since 1.0.0
 */
public interface LatLngRangeSpec<T> {

    /**
     * Sets the latitude range for generation.
     *
     * @param minLat minimum latitude (must be &gt;= -90)
     * @param maxLat maximum latitude (must be &lt;= 90)
     * @return this instance for method chaining
     */
    T latRange(double minLat, double maxLat);

    /**
     * Sets the longitude range for generation.
     *
     * @param minLng minimum longitude (must be &gt;= -180)
     * @param maxLng maximum longitude (must be &lt;= 180)
     * @return this instance for method chaining
     */
    T lngRange(double minLng, double maxLng);
}
