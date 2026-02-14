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

package com.stevenpg.instancio.proj4j;

import com.stevenpg.instancio.proj4j.internal.generator.CoordinateReferenceSystemGenerator;
import com.stevenpg.instancio.proj4j.internal.generator.ProjCoordinateGenerator;

/**
 * Facade for accessing Proj4J generator instances.
 * <p>
 * Provides factory methods for creating generators of Proj4J types,
 * allowing direct use or fluent configuration before generation.
 *
 * <pre>{@code
 * // Generate a random ProjCoordinate with default bounds
 * ProjCoordinate coord = GenProj4j.projCoordinate().generate(random);
 *
 * // Generate a CRS from a specific EPSG code
 * CoordinateReferenceSystem crs = GenProj4j.crs()
 *     .epsgCodes("EPSG:4326")
 *     .generate(random);
 * }</pre>
 *
 * @since 1.0.0
 */
public final class GenProj4j {

    /**
     * Creates a new {@link ProjCoordinateGenerator} for generating
     * random {@link org.locationtech.proj4j.ProjCoordinate} instances.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a projected coordinate in the SF area with elevation
     * ProjCoordinate coord = GenProj4j.projCoordinate()
     *     .xRange(-122.5, -122.3)
     *     .yRange(37.7, 37.8)
     *     .zRange(0, 500)
     *     .generate(random);
     * }</pre>
     *
     * @return a new ProjCoordinateGenerator with default bounds
     */
    public static ProjCoordinateGenerator projCoordinate() {
        return new ProjCoordinateGenerator();
    }

    /**
     * Creates a new {@link CoordinateReferenceSystemGenerator} for generating
     * random {@link org.locationtech.proj4j.CoordinateReferenceSystem} instances.
     *
     * <p>Example:
     * <pre>{@code
     * // Generate a CRS from a set of common EPSG codes
     * CoordinateReferenceSystem crs = GenProj4j.crs()
     *     .epsgCodes("EPSG:4326", "EPSG:3857", "EPSG:32632")
     *     .generate(random);
     * }</pre>
     *
     * @return a new CoordinateReferenceSystemGenerator with default EPSG codes
     */
    public static CoordinateReferenceSystemGenerator crs() {
        return new CoordinateReferenceSystemGenerator();
    }

    private GenProj4j() {
        // Utility class, not intended for instantiation
    }
}
