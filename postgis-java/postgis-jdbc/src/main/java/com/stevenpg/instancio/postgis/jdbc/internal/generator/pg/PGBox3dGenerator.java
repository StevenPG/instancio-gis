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
package com.stevenpg.instancio.postgis.jdbc.internal.generator.pg;

import com.stevenpg.instancio.postgis.jdbc.internal.generator.specs.NumericRangeSpec;
import net.postgis.jdbc.PGbox3d;
import net.postgis.jdbc.geometry.Point;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for {@link PGbox3d}.
 */
public class PGBox3dGenerator implements Generator<PGbox3d>, NumericRangeSpec<PGBox3dGenerator> {

    /**
     * Default constructor.
     */
    public PGBox3dGenerator() {
        // No custom instantiations needed
    }

    private double minX = -180d;
    private double maxX = 180d;
    private double minY = -90d;
    private double maxY = 90d;
    private double minZ = -1000d;
    private double maxZ = 1000d;

    @Override
    public PGBox3dGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public PGBox3dGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    @Override
    public PGBox3dGenerator zRange(double minZ, double maxZ) {
        this.minZ = minZ;
        this.maxZ = maxZ;
        return this;
    }

    @Override
    public PGbox3d generate(Random random) {
        double x1 = random.doubleRange(minX, maxX);
        double y1 = random.doubleRange(minY, maxY);
        double z1 = random.doubleRange(minZ, maxZ);
        double x2 = random.doubleRange(minX, maxX);
        double y2 = random.doubleRange(minY, maxY);
        double z2 = random.doubleRange(minZ, maxZ);

        return new PGbox3d(new Point(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2)),
                           new Point(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2)));
    }
}
