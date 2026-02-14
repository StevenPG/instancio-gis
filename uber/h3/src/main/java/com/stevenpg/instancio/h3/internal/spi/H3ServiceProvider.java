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

package com.stevenpg.instancio.h3.internal.spi;

import com.stevenpg.instancio.h3.internal.generator.LatLngGenerator;
import com.uber.h3core.util.LatLng;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * SPI Provider that enables Instancio to generate H3 types.
 *
 * <p>Only registers generators for types that are not built-in to Instancio.
 * {@link com.stevenpg.instancio.h3.internal.generator.H3IndexGenerator} is
 * excluded because it produces {@link Long}, which is a built-in type.</p>
 *
 * @since 1.0.0
 */
public class H3ServiceProvider implements InstancioServiceProvider {

    /**
     * Default constructor.
     */
    public H3ServiceProvider() {
        // No custom instantiations needed
    }

    @Override
    public void init(final ServiceProviderContext providerContext) {
        // No initialization needed
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final Map<Class<?>, Generator<?>> generators = new HashMap<>();

        // Only register LatLng; H3IndexGenerator returns Long (a built-in type)
        generators.put(LatLng.class, new LatLngGenerator());

        return (Node node, Generators gen) -> generators.get(node.getTargetClass());
    }
}
