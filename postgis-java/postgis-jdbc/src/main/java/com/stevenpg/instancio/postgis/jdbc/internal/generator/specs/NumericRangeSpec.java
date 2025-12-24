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

package com.stevenpg.instancio.postgis.jdbc.internal.generator.specs;

/**
 * Simple numeric range configuration for generator specs.
 */
public interface NumericRangeSpec<T> {

    /** Set inclusive X range. */
    T xRange(double minX, double maxX);

    /** Set inclusive Y range. */
    T yRange(double minY, double maxY);

    /** Set inclusive Z range. */
    default T zRange(double minZ, double maxZ) {
        return (T) this;
    }
}
