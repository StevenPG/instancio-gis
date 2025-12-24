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

package com.stevenpg.instancio.postgis.geometry.internal.spi;

import com.stevenpg.instancio.postgis.geometry.internal.generator.*;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.*;
import net.postgis.jdbc.geometry.Point;
import net.postgis.jdbc.geometry.Polygon;
import net.postgis.jdbc.geometry.LineString;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generator.GeneratorContext;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;

import java.util.HashMap;
import java.util.Map;

/** SPI provider for org.postgis geometry generators. */
public class PostgisGeometryServiceProvider implements InstancioServiceProvider {

    private GeneratorContext ctx;

    @Override
    public void init(ServiceProviderContext providerContext) {
        this.ctx = new GeneratorContext(providerContext.getSettings(), providerContext.random());
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final Map<Class<?>, Generator<?>> map = new HashMap<>();
        map.put(PGgeometry.class, new PGgeometryGenerator());
        map.put(Geometry.class, new GeometryGenerator());
        map.put(Point.class, new PointGenerator());
        map.put(LineString.class, new LineStringGenerator());
        map.put(LinearRing.class, new LinearRingGenerator());
        map.put(Polygon.class, new PolygonGenerator());
        map.put(MultiPoint.class, new MultiPointGenerator());
        map.put(MultiLineString.class, new MultiLineStringGenerator());
        map.put(MultiPolygon.class, new MultiPolygonGenerator());
        map.put(GeometryCollection.class, new GeometryCollectionGenerator());
        return (Node node, Generators gen) -> map.get(node.getTargetClass());
    }

    @Override
    public TypeResolver getTypeResolver() {
        return (Class<?> type) -> {
            if (type.equals(Geometry.class)) {
                return Point.class;
            }
            return null;
        };
    }
}
