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
import net.postgis.jdbc.PGbox2d;
import net.postgis.jdbc.geometry.Point;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for {@link PGbox2d}.
 */
public class PGBox2dGenerator implements Generator<PGbox2d>, NumericRangeSpec<PGBox2dGenerator> {

    private double minX = -180d;
    private double maxX = 180d;
    private double minY = -90d;
    private double maxY = 90d;

    @Override
    public PGBox2dGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public PGBox2dGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    @Override
    public PGbox2d generate(Random random) {
        final java.util.Random r = random != null ? new java.util.Random(random.longRange(Long.MIN_VALUE, Long.MAX_VALUE)) : new java.util.Random();
        double x1 = minX + (maxX - minX) * r.nextDouble();
        double y1 = minY + (maxY - minY) * r.nextDouble();
        double x2 = minX + (maxX - minX) * r.nextDouble();
        double y2 = minY + (maxY - minY) * r.nextDouble();

        return new PGbox2d(new Point(Math.min(x1, x2), Math.min(y1, y2)),
                           new Point(Math.max(x1, x2), Math.max(y1, y2)));
    }
}
