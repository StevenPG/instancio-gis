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
 * Facade for accessing PostGIS JDBC JTS geometry generators.
 *
 * <p>Provides a factory method for creating a generator that produces
 * {@link net.postgis.jdbc.jts.JtsGeometry} instances, bridging PostGIS
 * JDBC types with LocationTech JTS geometries.
 *
 * <p>Usage example:
 * <pre>{@code
 * // Generate a random JtsGeometry wrapping a JTS geometry
 * JtsGeometry jtsGeom = GenPostgisJdbcJts.jtsGeometry()
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenPostgisJdbcJts {

    /**
     * Access to the Generator for {@link net.postgis.jdbc.jts.JtsGeometry}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a JtsGeometry for use with PostGIS JDBC
     * JtsGeometry geom = GenPostgisJdbcJts.jtsGeometry()
     *     .generate(random);
     * }</pre>
     *
     * @return generator
     */
    public static JtsGeometryGenerator jtsGeometry() {
        return new JtsGeometryGenerator();
    }

    private GenPostgisJdbcJts() {
        // private constructor to prevent instantiation
    }
}
