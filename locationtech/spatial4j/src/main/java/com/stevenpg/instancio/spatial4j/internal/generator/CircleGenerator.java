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
import org.locationtech.spatial4j.shape.Circle;

/**
 * Generator for creating Spatial4j {@link Circle} instances using the
 * {@link SpatialContext#GEO} shape factory.
 *
 * <p>Generates a circle with a random center point within the configured X/Y ranges
 * and a random radius within the configured radius range. The radius is measured in
 * degrees of distance.</p>
 *
 * @since 1.0.0
 */
public class CircleGenerator implements Generator<Circle>, NumericRangeSpec<CircleGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private double minRadius = 0.1;
    private double maxRadius = 10.0;

    /**
     * Default constructor.
     */
    public CircleGenerator() {
        // No custom instantiations needed
    }

    @Override
    public CircleGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    @Override
    public CircleGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Sets the radius range for the generated circle.
     *
     * @param minRadius the minimum radius (inclusive), in degrees
     * @param maxRadius the maximum radius (inclusive), in degrees
     * @return this generator for fluent chaining
     */
    public CircleGenerator radiusRange(double minRadius, double maxRadius) {
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        return this;
    }

    @Override
    public Circle generate(Random random) {
        double x = random.doubleRange(minX, maxX);
        double y = random.doubleRange(minY, maxY);
        double distance = random.doubleRange(minRadius, maxRadius);
        return SpatialContext.GEO.getShapeFactory().circle(x, y, distance);
    }
}
