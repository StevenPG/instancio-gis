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

package com.stevenpg.instancio.proj4j.internal.generator;

import com.stevenpg.instancio.proj4j.internal.generator.specs.NumericRangeSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.proj4j.ProjCoordinate;

/**
 * Generator for creating random {@link ProjCoordinate} instances.
 * <p>
 * By default, generates coordinates within standard geographic bounds:
 * x in [-180, 180], y in [-90, 90]. The z coordinate is not included
 * unless {@link #zRange(double, double)} is called.
 *
 * @since 1.0.0
 */
public class ProjCoordinateGenerator implements Generator<ProjCoordinate>, NumericRangeSpec<ProjCoordinateGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private double minZ = 0;
    private double maxZ = 0;
    private boolean useZ = false;

    /**
     * Default constructor.
     */
    public ProjCoordinateGenerator() {
        // No custom instantiations needed
    }

    @Override
    public ProjCoordinateGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public ProjCoordinateGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Sets the z coordinate range and enables z coordinate generation.
     *
     * @param minZ the minimum z value (inclusive)
     * @param maxZ the maximum z value (exclusive)
     * @return this generator instance for method chaining
     */
    @Override
    public ProjCoordinateGenerator zRange(double minZ, double maxZ) {
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.useZ = true;
        return this;
    }

    /**
     * Generates a random {@link ProjCoordinate} within the configured bounds.
     *
     * @param random the random instance provided by Instancio
     * @return a new ProjCoordinate with random x, y, and optionally z values
     */
    @Override
    public ProjCoordinate generate(Random random) {
        double x = random.doubleRange(minX, maxX);
        double y = random.doubleRange(minY, maxY);

        if (useZ) {
            double z = random.doubleRange(minZ, maxZ);
            return new ProjCoordinate(x, y, z);
        }
        return new ProjCoordinate(x, y);
    }
}
