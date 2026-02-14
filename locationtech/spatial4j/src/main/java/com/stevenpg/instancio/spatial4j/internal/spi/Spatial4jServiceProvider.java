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

package com.stevenpg.instancio.spatial4j.internal.spi;

import com.stevenpg.instancio.spatial4j.internal.generator.CircleGenerator;
import com.stevenpg.instancio.spatial4j.internal.generator.PointGenerator;
import com.stevenpg.instancio.spatial4j.internal.generator.RectangleGenerator;
import com.stevenpg.instancio.spatial4j.internal.generator.ShapeCollectionGenerator;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;
import org.locationtech.spatial4j.shape.Circle;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.ShapeCollection;

import java.util.HashMap;
import java.util.Map;

/**
 * SPI Provider that enables Instancio to generate Spatial4j shapes.
 *
 * <p>This provider registers generators for all supported Spatial4j shape types,
 * allowing {@code Instancio.create(Point.class)} and similar calls to produce
 * valid Spatial4j shape instances automatically.</p>
 *
 * @since 1.0.0
 */
public class Spatial4jServiceProvider implements InstancioServiceProvider {

    /**
     * Default constructor.
     */
    public Spatial4jServiceProvider() {
        // No custom instantiations needed
    }

    @Override
    public void init(final ServiceProviderContext ctx) {
        // No initialization needed
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final Map<Class<?>, Generator<?>> generators = new HashMap<>();
        generators.put(Point.class, new PointGenerator());
        generators.put(Rectangle.class, new RectangleGenerator());
        generators.put(Circle.class, new CircleGenerator());
        generators.put(ShapeCollection.class, new ShapeCollectionGenerator());

        return (Node node, Generators gen) -> generators.get(node.getTargetClass());
    }
}
