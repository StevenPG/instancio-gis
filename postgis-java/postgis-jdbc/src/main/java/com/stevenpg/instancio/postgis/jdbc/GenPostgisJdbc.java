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

package com.stevenpg.instancio.postgis.jdbc;

import com.stevenpg.instancio.postgis.jdbc.internal.generator.pg.*;

/**
 * Facade for accessing PostGIS JDBC geometry generators.
 *
 * <p>Provides factory methods for creating configurable generators for PostgreSQL
 * geometric types and PostGIS bounding box types. These generators produce
 * random instances suitable for testing database-backed spatial applications.
 *
 * <p>Usage examples:
 * <pre>{@code
 * // Generate a PGpoint within the San Francisco area
 * PGpoint point = GenPostgisJdbc.pgPoint()
 *     .xRange(-122.5, -122.3)
 *     .yRange(37.7, 37.8)
 *     .generate(random);
 *
 * // Generate a PGpolygon with 6 vertices
 * PGpolygon polygon = GenPostgisJdbc.pgPolygon()
 *     .vertices(4, 8)
 *     .xRange(-0.2, 0.0)
 *     .yRange(51.4, 51.6)
 *     .generate(random);
 *
 * // Generate a PostGIS 2D bounding box
 * PGbox2d box = GenPostgisJdbc.pgBox2d()
 *     .xRange(-74.1, -73.9)
 *     .yRange(40.7, 40.8)
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenPostgisJdbc {

    /**
     * Access to the Generator for {@link org.postgresql.geometric.PGbox}.
     * @return generator
     */
    public static PGBoxGenerator pgBox() {
        return new PGBoxGenerator();
    }

    /**
     * Access to the Generator for {@link org.postgresql.geometric.PGcircle}.
     * @return generator
     */
    public static PGCircleGenerator pgCircle() {
        return new PGCircleGenerator();
    }

    /**
     * Access to the Generator for {@link org.postgresql.geometric.PGline}.
     * @return generator
     */
    public static PGLineGenerator pgLine() {
        return new PGLineGenerator();
    }

    /**
     * Access to the Generator for {@link org.postgresql.geometric.PGlseg}.
     * @return generator
     */
    public static PGLsegGenerator pgLseg() {
        return new PGLsegGenerator();
    }

    /**
     * Access to the Generator for {@link org.postgresql.geometric.PGpath}.
     * @return generator
     */
    public static PGPathGenerator pgPath() {
        return new PGPathGenerator();
    }

    /**
     * Access to the Generator for {@link org.postgresql.geometric.PGpoint}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a PGpoint near NYC
     * PGpoint point = GenPostgisJdbc.pgPoint()
     *     .xRange(-74.05, -73.90)
     *     .yRange(40.70, 40.80)
     *     .generate(random);
     * }</pre>
     *
     * @return generator
     */
    public static PGPointGenerator pgPoint() {
        return new PGPointGenerator();
    }

    /**
     * Access to the Generator for {@link org.postgresql.geometric.PGpolygon}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a PGpolygon with 5 to 10 vertices in central London
     * PGpolygon polygon = GenPostgisJdbc.pgPolygon()
     *     .vertices(5, 10)
     *     .xRange(-0.15, -0.05)
     *     .yRange(51.49, 51.53)
     *     .generate(random);
     * }</pre>
     *
     * @return generator
     */
    public static PGPolygonGenerator pgPolygon() {
        return new PGPolygonGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.PGbox2d}.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a 2D bounding box covering part of Tokyo
     * PGbox2d box = GenPostgisJdbc.pgBox2d()
     *     .xRange(139.6, 139.8)
     *     .yRange(35.6, 35.7)
     *     .generate(random);
     * }</pre>
     *
     * @return generator
     */
    public static PGBox2dGenerator pgBox2d() {
        return new PGBox2dGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.PGbox3d}.
     * @return generator
     */
    public static PGBox3dGenerator pgBox3d() {
        return new PGBox3dGenerator();
    }

    private GenPostgisJdbc() {
        // private constructor to prevent instantiation
    }
}
