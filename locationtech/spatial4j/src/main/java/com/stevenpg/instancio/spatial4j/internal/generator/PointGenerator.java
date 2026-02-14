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

package com.stevenpg.instancio.spatial4j.internal.generator;

import com.stevenpg.instancio.spatial4j.internal.generator.specs.NumericRangeSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;

/**
 * Generator for creating Spatial4j {@link Point} instances using the
 * {@link SpatialContext#GEO} shape factory.
 *
 * <p>By default, generates points within the full geographic coordinate range
 * (longitude -180 to 180, latitude -90 to 90). The ranges can be customized
 * via the {@link NumericRangeSpec} methods.</p>
 *
 * @since 1.0.0
 */
public class PointGenerator implements Generator<Point>, NumericRangeSpec<PointGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;

    /**
     * Default constructor.
     */
    public PointGenerator() {
        // No custom instantiations needed
    }

    @Override
    public PointGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public PointGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    @Override
    public Point generate(Random random) {
        double x = random.doubleRange(minX, maxX);
        double y = random.doubleRange(minY, maxY);
        return SpatialContext.GEO.getShapeFactory().pointXY(x, y);
    }
}
