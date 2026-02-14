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

package com.stevenpg.instancio.proj4j.internal.spi;

import com.stevenpg.instancio.proj4j.internal.generator.CoordinateReferenceSystemGenerator;
import com.stevenpg.instancio.proj4j.internal.generator.ProjCoordinateGenerator;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;

import java.util.HashMap;
import java.util.Map;

/**
 * SPI Provider that enables Instancio to generate Proj4J types.
 *
 * @since 1.0.0
 */
public class Proj4jServiceProvider implements InstancioServiceProvider {

    /**
     * Default constructor.
     */
    public Proj4jServiceProvider() {
        // No custom instantiations needed
    }

    @Override
    public void init(final ServiceProviderContext providerContext) {
        // No initialization needed
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final Map<Class<?>, Generator<?>> generators = new HashMap<>();

        generators.put(ProjCoordinate.class, new ProjCoordinateGenerator());
        generators.put(CoordinateReferenceSystem.class, new CoordinateReferenceSystemGenerator());

        return (Node node, Generators gen) -> generators.get(node.getTargetClass());
    }
}
