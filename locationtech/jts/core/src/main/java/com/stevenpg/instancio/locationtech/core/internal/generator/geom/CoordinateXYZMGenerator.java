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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.LatLonEnvelopableBaseGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.EnvelopableGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateXYZMGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateXYZMSpec;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.CoordinateXYZM;
import org.locationtech.jts.geom.Envelope;

import static com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility.WithinUtility.randomLonLatInBounds;

/**
 * Generator for creating a CoordinateXYZM.
 *
 * @since 1.0.0
 */
public class CoordinateXYZMGenerator extends LatLonEnvelopableBaseGenerator implements CoordinateXYZMSpec, CoordinateXYZMGeneratorSpec, Generator<CoordinateXYZM>, EnvelopableGenerator<CoordinateXYZM> {

    private Double inputAltitude;
    private Double measure;

    /**
     * Default constructor.
     */
    public CoordinateXYZMGenerator() {}

    @Override
    public CoordinateXYZMGenerator latitude(double latitude) {
        this.setInputLatitude(latitude);
        return this;
    }

    @Override
    public CoordinateXYZMGenerator longitude(double longitude) {
        this.setInputLongitude(longitude);
        return this;
    }

    @Override
    public CoordinateXYZMGenerator altitude(double altitude) {
        this.inputAltitude = altitude;
        return this;
    }

    @Override
    public CoordinateXYZMGenerator measure(double measure) {
        this.measure = measure;
        return this;
    }

    @Override
    public Generator<CoordinateXYZM> within(Envelope validGenerationAreaEnvelope) {
        this.setInputEnvelope(validGenerationAreaEnvelope);
        return this;
    }

    @Override
    public CoordinateXYZM generate(Random random) {
        if (envelopeProvided() && coordinateMissing()) {
            var lonLat = randomLonLatInBounds(getInputEnvelope());
            return new CoordinateXYZM(lonLat.longitude(), lonLat.latitude(),
                    inputAltitude == null ? Instancio.gen().doubles().get() : inputAltitude,
                    measure == null ? Instancio.gen().doubles().get() : measure);
        } else {
            return new CoordinateXYZM(
                    getInputLongitude() == null ? Instancio.gen().spatial().coordinate().lon().get() : getInputLongitude(),
                    getInputLatitude() == null ? Instancio.gen().spatial().coordinate().lat().get() : getInputLatitude(),
                    inputAltitude == null ? Instancio.gen().doubles().get() : inputAltitude,
                    measure == null ? Instancio.gen().doubles().get() : measure
            );
        }
    }
}
