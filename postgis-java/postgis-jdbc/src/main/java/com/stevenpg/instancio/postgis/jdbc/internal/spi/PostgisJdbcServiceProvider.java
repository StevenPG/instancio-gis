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
package com.stevenpg.instancio.postgis.jdbc.internal.spi;

import com.stevenpg.instancio.postgis.jdbc.internal.generator.pg.*;
import net.postgis.jdbc.PGbox2d;
import net.postgis.jdbc.PGbox3d;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generator.GeneratorContext;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;
import org.postgresql.geometric.*;

import java.util.HashMap;
import java.util.Map;

/** SPI provider for PostGIS JDBC generators. */
public class PostgisJdbcServiceProvider implements InstancioServiceProvider {

    private GeneratorContext generatorContext;

    @Override
    public void init(ServiceProviderContext providerContext) {
        this.generatorContext = new GeneratorContext(
                providerContext.getSettings(),
                providerContext.random());
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final Map<Class<?>, Generator<?>> map = new HashMap<>();

        // net.postgis.jdbc.*
        map.put(PGbox2d.class, new PGBox2dGenerator());
        map.put(PGbox3d.class, new PGBox3dGenerator());

        // org.postgresql.geometric.*
        map.put(PGpoint.class, new PGPointGenerator());
        map.put(PGlseg.class, new PGLsegGenerator());
        map.put(PGline.class, new PGLineGenerator());
        map.put(PGpath.class, new PGPathGenerator());
        map.put(PGpolygon.class, new PGPolygonGenerator());
        map.put(PGbox.class, new PGBoxGenerator());
        map.put(PGcircle.class, new PGCircleGenerator());

        return (Node node, Generators gen) -> map.get(node.getTargetClass());
    }
}
