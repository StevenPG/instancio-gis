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
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.OctagonalEnvelope;

/**
 * Spec for generating an OctagonalEnvelope.
 */
public interface OctagonalEnvelopeGeneratorSpec extends GeneratorSpec<OctagonalEnvelope> {

    /**
     * Generate an OctagonalEnvelope with the specified coordinates.
     * @param coordinate the coordinate to use
     * @return spec builder
     * @since 1.0.0
     */
    OctagonalEnvelopeGeneratorSpec coordinate(Coordinate coordinate);

    /**
     * Generate an OctagonalEnvelope with the specified coordinates.
     * @param coordinate0 the first coordinate
     * @param coordinate1 the second coordinate
     * @return spec builder
     * @since 1.0.0
     */
    OctagonalEnvelopeGeneratorSpec coordinate(Coordinate coordinate0, Coordinate coordinate1);

    /**
     * Generate an OctagonalEnvelope with the specified envelope.
     * @param envelope the envelope to use
     * @return spec builder
     * @since 1.0.0
     */
    OctagonalEnvelopeGeneratorSpec envelope(Envelope envelope);

    /**
     * Generate an OctagonalEnvelope with the specified geometry.
     * @param octagonalEnvelope the geometry to use
     * @return spec builder
     * @since 1.0.0
     */
    OctagonalEnvelopeGeneratorSpec envelope(OctagonalEnvelope octagonalEnvelope);

    /**
     * Generate an OctagonalEnvelope with the specified geometry.
     * @param geometry the geometry to use
     * @return spec builder
     * @since 1.0.0
     */
    OctagonalEnvelopeGeneratorSpec geometry(Geometry geometry);
}
