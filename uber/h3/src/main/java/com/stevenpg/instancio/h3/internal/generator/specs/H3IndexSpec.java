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
 * Specification for configuring H3 cell index generation,
 * extending {@link LatLngRangeSpec} with resolution control.
 *
 * @param <T> the concrete type returned for method chaining
 * @since 1.0.0
 */
public interface H3IndexSpec<T> extends LatLngRangeSpec<T> {

    /**
     * Sets the H3 resolution level for cell index generation.
     *
     * @param resolution the H3 resolution (0-15)
     * @return this instance for method chaining
     */
    T resolution(int resolution);
}
