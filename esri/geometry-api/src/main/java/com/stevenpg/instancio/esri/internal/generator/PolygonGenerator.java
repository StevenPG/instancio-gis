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

package com.stevenpg.instancio.esri.internal.generator;

import com.esri.core.geometry.Polygon;
import com.stevenpg.instancio.esri.internal.generator.specs.NumericRangeSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating ESRI {@link Polygon} instances with a random ring
 * of configurable vertex count within coordinate ranges.
 *
 * <p>The polygon is built by calling {@code startPath} for the first vertex
 * and {@code lineTo} for subsequent vertices. The ESRI Polygon automatically
 * closes the ring by connecting the last vertex back to the first.</p>
 *
 * <p>The number of vertices is randomly chosen between {@code minVertices}
 * and {@code maxVertices} (defaults: 3 to 8).</p>
 *
 * @since 1.0.0
 */
public class PolygonGenerator implements Generator<Polygon>, NumericRangeSpec<PolygonGenerator> {

    private double minX = -180;
    private double maxX = 180;
    private double minY = -90;
    private double maxY = 90;
    private int minVertices = 3;
    private int maxVertices = 8;

    /**
     * Default constructor.
     */
    public PolygonGenerator() {
        // No custom instantiations needed
    }

    /** {@inheritDoc} */
    @Override
    public PolygonGenerator xRange(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public PolygonGenerator yRange(double minY, double maxY) {
        this.minY = minY;
        this.maxY = maxY;
        return this;
    }

    /**
     * Configure the number of vertices for the polygon ring.
     *
     * @param min the minimum number of vertices (inclusive)
     * @param max the maximum number of vertices (inclusive)
     * @return this generator instance for fluent chaining
     */
    public PolygonGenerator vertices(int min, int max) {
        this.minVertices = min;
        this.maxVertices = max;
        return this;
    }

    /**
     * Generates a random ESRI Polygon with a single ring containing a random
     * number of vertices within the configured coordinate ranges.
     *
     * <p>The polygon auto-closes, so no explicit closing vertex is needed.</p>
     *
     * @param random the Instancio random instance used for value generation
     * @return a new {@link Polygon} with a randomly generated ring
     */
    @Override
    public Polygon generate(Random random) {
        Polygon pg = new Polygon();
        int count = random.intRange(minVertices, maxVertices);

        // Start the ring with the first vertex
        pg.startPath(
                random.doubleRange(minX, maxX),
                random.doubleRange(minY, maxY)
        );

        // Add remaining vertices; the polygon auto-closes
        for (int i = 1; i < count; i++) {
            pg.lineTo(
                    random.doubleRange(minX, maxX),
                    random.doubleRange(minY, maxY)
            );
        }

        return pg;
    }
}
