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
package com.stevenpg.instancio.postgis.jdbc.jts.internal.spi;

import com.stevenpg.instancio.postgis.jdbc.jts.internal.generator.JtsGeometryGenerator;
import net.postgis.jdbc.jts.JtsGeometry;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * SPI provider for PostGIS JTS geometry generators.
 */
public class PostgisJdbcJtsServiceProvider implements InstancioServiceProvider {

    /**
     * Default constructor.
     */
    public PostgisJdbcJtsServiceProvider(){
        // No custom instantiations needed
    }

    @Override
    public void init(ServiceProviderContext providerContext) {
        // No initialization needed
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final Map<Class<?>, Generator<?>> map = new HashMap<>();
        map.put(JtsGeometry.class, new JtsGeometryGenerator());
        return (Node node, Generators gen) -> map.get(node.getTargetClass());
    }
}
