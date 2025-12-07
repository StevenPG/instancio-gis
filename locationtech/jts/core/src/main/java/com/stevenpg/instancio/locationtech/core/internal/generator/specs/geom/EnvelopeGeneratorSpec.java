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

package com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom;

import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;

/**
 * Spec for generating an Envelope.
 */
public interface EnvelopeGeneratorSpec extends GeneratorSpec<Envelope> {

    /**
     * Sets the bounds of the generated envelope.
     * @param x1 the minimum x-coordinate
     * @param x2 the maximum x-coordinate
     * @param y1 the minimum y-coordinate
     * @param y2 the maximum y-coordinate
     * @return spec builder
     * @since 1.0.0
     */
    EnvelopeGeneratorSpec bounds(double x1, double x2, double y1, double y2);

    /**
     * Sets the coordinate of the generated envelope.
     * @param coordinate the coordinate to use
     * @return spec builder
     * @since 1.0.0
     */
    EnvelopeGeneratorSpec coordinate(Coordinate coordinate);

    /**
     * Sets the coordinates of the generated envelope.
     * @param coordinate0 the first coordinate
     * @param coordinate1 the second coordinate
     * @return spec builder
     */
    EnvelopeGeneratorSpec coordinate(Coordinate coordinate0, Coordinate coordinate1);

    /**
     * Sets the envelope to generate.
     * @param envelope the envelope to use
     * @return spec builder
     */
    EnvelopeGeneratorSpec envelope(Envelope envelope);
}
