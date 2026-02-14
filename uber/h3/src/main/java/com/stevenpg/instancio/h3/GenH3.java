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

package com.stevenpg.instancio.h3;

import com.stevenpg.instancio.h3.internal.generator.H3IndexGenerator;
import com.stevenpg.instancio.h3.internal.generator.LatLngGenerator;

/**
 * Facade for accessing Uber H3 generators.
 *
 * <p>Provides factory methods for creating generators that produce H3-related
 * types, including geographic coordinates ({@code LatLng}) and H3 cell indices.
 *
 * <p>Usage examples:
 * <pre>{@code
 * // Generate a random LatLng in the NYC area
 * LatLng latLng = GenH3.latLng()
 *     .latRange(40.70, 40.80)
 *     .lngRange(-74.05, -73.90)
 *     .generate(random);
 *
 * // Generate an H3 cell index at resolution 7
 * Long h3Index = GenH3.h3Index()
 *     .resolution(7)
 *     .latRange(51.49, 51.53)
 *     .lngRange(-0.15, -0.05)
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenH3 {

    /**
     * Access to the Generator for {@link com.uber.h3core.util.LatLng}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a LatLng within the San Francisco area
     * LatLng latLng = GenH3.latLng()
     *     .latRange(37.70, 37.80)
     *     .lngRange(-122.50, -122.30)
     *     .generate(random);
     * }</pre>
     *
     * @return generator for LatLng instances
     */
    public static LatLngGenerator latLng() {
        return new LatLngGenerator();
    }

    /**
     * Access to the Generator for H3 cell indices ({@link Long}).
     *
     * <p>Example:
     * <pre>{@code
     * // Generate an H3 index at resolution 9 near Tokyo
     * Long index = GenH3.h3Index()
     *     .resolution(9)
     *     .latRange(35.60, 35.70)
     *     .lngRange(139.60, 139.80)
     *     .generate(random);
     * }</pre>
     *
     * @return generator for H3 index values
     */
    public static H3IndexGenerator h3Index() {
        return new H3IndexGenerator();
    }

    private GenH3() {
        // private constructor to prevent instantiation
    }
}
