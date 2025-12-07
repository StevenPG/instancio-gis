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

package com.stevenpg.instancio.locationtech.core.internal.generator.specs;

import org.instancio.generator.Generator;
import org.locationtech.jts.geom.Envelope;

/**
 * Specs that extend Envelopable allow the user to input an envelope that is taken into account when generating
 * values for the generator. The generator should not generate any sort of geometry that is outside of the envelope
 * unless the user overrides the envelope by providing their own explicit points.
 * @param <T> the type of geometry that the generator will generate
 */
public interface EnvelopableGenerator<T> extends Generator<T> {

    /**
     * Generators that extend EnvelopableGeneratorSpec should be able to accept an envelope that defines the valid
     * generation area. The generator should not generate any sort of geometry that is outside of the envelope unless
     * the user overrides the envelope by providing their own explicit points.
     * @param validGenerationAreaEnvelope the envelope that defines the valid generation area
     * @return this generator
     */
    Generator<T> within(Envelope validGenerationAreaEnvelope);

}