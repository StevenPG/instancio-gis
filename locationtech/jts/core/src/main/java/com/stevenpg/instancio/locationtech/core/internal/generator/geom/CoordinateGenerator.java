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

package com.stevenpg.instancio.locationtech.core.internal.generator.geom;

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.EnvelopableGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateSpec;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;

import static com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility.WithinUtility.randomLonLatInBounds;

/**
 * Generator for creating a Coordinate.
 *
 * @since 1.0.0
 */
public class CoordinateGenerator implements CoordinateSpec, EnvelopableGenerator<Coordinate> {

    private Double inputLatitude;
    private Double inputLongitude;
    private Envelope inputEnvelope;

    /**
     * Default constructor.
     */
    public CoordinateGenerator() {
        // No custom instantiations needed
    }

    @Override
    public CoordinateGenerator latitude(double latitude) {
        this.inputLatitude = latitude;
        return this;
    }

    @Override
    public CoordinateGenerator longitude(double longitude) {
        this.inputLongitude = longitude;
        return this;
    }

    @Override
    public Generator<Coordinate> within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public Coordinate generate(Random random) {
        if(envelopeProvided() && !coordinateProvided()) {
            var lonLat = randomLonLatInBounds(inputEnvelope);
            return new Coordinate(lonLat.longitude(), lonLat.latitude());
        } else {
            return new Coordinate(
                    inputLongitude == null ? Instancio.gen().spatial().coordinate().lon().get() : inputLongitude,
                    inputLatitude == null ? Instancio.gen().spatial().coordinate().lat().get() : inputLatitude
            );
        }
    }

    private boolean coordinateProvided() {
        return inputLatitude != null || inputLongitude != null;
    }

    private boolean envelopeProvided() {
        return inputEnvelope != null;
    }
}
