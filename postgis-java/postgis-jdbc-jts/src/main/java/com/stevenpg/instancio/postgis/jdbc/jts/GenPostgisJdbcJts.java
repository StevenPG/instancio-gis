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

package com.stevenpg.instancio.postgis.jdbc.jts;

import com.stevenpg.instancio.postgis.jdbc.jts.internal.generator.JtsGeometryGenerator;

/**
 * Provides access to the PostGIS JDBC JTS generator provider.
 *
 * @since 1.0.0
 */
public final class GenPostgisJdbcJts {

    /**
     * Access to the Generator for {@link net.postgis.jdbc.jts.JtsGeometry}.
     * @return generator
     */
    public static JtsGeometryGenerator jtsGeometry() {
        return new JtsGeometryGenerator();
    }

    private GenPostgisJdbcJts() {
        // private constructor to prevent instantiation
    }
}
