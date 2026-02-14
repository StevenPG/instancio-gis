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

package com.stevenpg.instancio.esri.internal.spi;

import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.MultiPoint;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.stevenpg.instancio.esri.internal.generator.*;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * SPI Provider that enables Instancio to automatically generate
 * ESRI Geometry API objects.
 *
 * <p>When this provider is on the classpath, Instancio can create
 * {@link Point}, {@link MultiPoint}, {@link Envelope}, {@link Polyline},
 * and {@link Polygon} instances without any additional configuration.</p>
 *
 * @since 1.0.0
 */
public class EsriGeometryServiceProvider implements InstancioServiceProvider {

    /**
     * Default constructor.
     */
    public EsriGeometryServiceProvider() {
        // No custom instantiations needed
    }

    @Override
    public void init(final ServiceProviderContext providerContext) {
        // No initialization needed
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final Map<Class<?>, Generator<?>> generators = new HashMap<>();

        generators.put(Point.class, new PointGenerator());
        generators.put(MultiPoint.class, new MultiPointGenerator());
        generators.put(Envelope.class, new EnvelopeGenerator());
        generators.put(Polyline.class, new PolylineGenerator());
        generators.put(Polygon.class, new PolygonGenerator());

        return (Node node, Generators gen) -> generators.get(node.getTargetClass());
    }
}
