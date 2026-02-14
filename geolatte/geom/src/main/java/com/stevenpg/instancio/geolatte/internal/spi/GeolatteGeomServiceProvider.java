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

package com.stevenpg.instancio.geolatte.internal.spi;

import com.stevenpg.instancio.geolatte.internal.generator.GeometryCollectionGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.LineStringGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.LinearRingGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.MultiLineStringGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.MultiPointGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.MultiPolygonGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.PointGenerator;
import com.stevenpg.instancio.geolatte.internal.generator.PolygonGenerator;
import org.geolatte.geom.GeometryCollection;
import org.geolatte.geom.LineString;
import org.geolatte.geom.LinearRing;
import org.geolatte.geom.MultiLineString;
import org.geolatte.geom.MultiPoint;
import org.geolatte.geom.MultiPolygon;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * SPI Provider that enables Instancio to generate Geolatte Geom geometries.
 * Registers generators for all supported Geolatte geometry types using raw types
 * for SPI compatibility.
 *
 * @since 1.0.0
 */
public class GeolatteGeomServiceProvider implements InstancioServiceProvider {

    /**
     * Default constructor.
     */
    public GeolatteGeomServiceProvider() {
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
        generators.put(LineString.class, new LineStringGenerator());
        generators.put(LinearRing.class, new LinearRingGenerator());
        generators.put(Polygon.class, new PolygonGenerator());
        generators.put(MultiPoint.class, new MultiPointGenerator());
        generators.put(MultiLineString.class, new MultiLineStringGenerator());
        generators.put(MultiPolygon.class, new MultiPolygonGenerator());
        generators.put(GeometryCollection.class, new GeometryCollectionGenerator());

        return (Node node, Generators gen) -> generators.get(node.getTargetClass());
    }
}
