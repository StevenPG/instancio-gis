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
 * Provides access to the PostGIS JDBC generator provider.
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
     * @return generator
     */
    public static PGPointGenerator pgPoint() {
        return new PGPointGenerator();
    }

    /**
     * Access to the Generator for {@link org.postgresql.geometric.PGpolygon}.
     * @return generator
     */
    public static PGPolygonGenerator pgPolygon() {
        return new PGPolygonGenerator();
    }

    /**
     * Access to the Generator for {@link net.postgis.jdbc.PGbox2d}.
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
