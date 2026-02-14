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
 * Provides access to the H3 generator providers.
 *
 * @since 1.0.0
 */
public final class GenH3 {

    /**
     * Access to the Generator for {@link com.uber.h3core.util.LatLng}.
     *
     * @return generator for LatLng instances
     */
    public static LatLngGenerator latLng() {
        return new LatLngGenerator();
    }

    /**
     * Access to the Generator for H3 cell indices ({@link Long}).
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
