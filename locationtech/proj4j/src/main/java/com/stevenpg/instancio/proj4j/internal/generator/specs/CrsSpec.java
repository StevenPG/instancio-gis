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
 * Specification for generators that support configurable EPSG coordinate
 * reference system codes.
 *
 * @param <T> the concrete generator type for fluent method chaining
 * @since 1.0.0
 */
public interface CrsSpec<T> {

    /**
     * Sets the EPSG codes from which the generator will randomly select
     * when creating a coordinate reference system.
     *
     * @param codes one or more EPSG code strings (e.g., "EPSG:4326")
     * @return this generator instance for method chaining
     */
    T epsgCodes(String... codes);
}
