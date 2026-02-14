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
import org.locationtech.spatial4j.shape.Rectangle;

/**
 * Generator for creating Spatial4j {@link Rectangle} instances using the
 * {@link SpatialContext#GEO} shape factory.
 *
 * <p>Generates two random X values and two random Y values within the configured
 * ranges, then sorts them to ensure minX &lt;= maxX and minY &lt;= maxY before
 * constructing the rectangle.</p>
 *
 * @since 1.0.0
 */
public class RectangleGenerator implements Generator<Rectangle>, NumericRangeSpec<RectangleGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;

    /**
     * Default constructor.
     */
    public RectangleGenerator() {
        // No custom instantiations needed
    }

    @Override
    public RectangleGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public RectangleGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    @Override
    public Rectangle generate(Random random) {
        double x1 = random.doubleRange(minX, maxX);
        double x2 = random.doubleRange(minX, maxX);
        double y1 = random.doubleRange(minY, maxY);
        double y2 = random.doubleRange(minY, maxY);

        // Sort to ensure the rectangle bounds are properly ordered
        return SpatialContext.GEO.getShapeFactory().rect(
                Math.min(x1, x2), Math.max(x1, x2),
                Math.min(y1, y2), Math.max(y1, y2)
        );
    }
}
