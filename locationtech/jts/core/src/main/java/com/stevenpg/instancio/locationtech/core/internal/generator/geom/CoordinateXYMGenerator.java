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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateXYMGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.CoordinateXYMSpec;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.CoordinateXYM;

/**
 * Generator for creating a CoordinateXYM.
 *
 * @since 1.0.0
 */
public class CoordinateXYMGenerator implements CoordinateXYMSpec, CoordinateXYMGeneratorSpec, Generator<CoordinateXYM>{

    private Double inputLatitude;
    private Double inputLongitude;
    private Double measure;

    @Override
    public CoordinateXYMGenerator latitude(double latitude) {
        this.inputLatitude = latitude;
        return this;
    }

    @Override
    public CoordinateXYMGenerator longitude(double longitude) {
        this.inputLongitude = longitude;
        return this;
    }

    @Override
    public CoordinateXYMGenerator measure(double measure) {
        this.measure = measure;
        return this;
    }

    @Override
    public CoordinateXYM generate(Random random) {
        return new CoordinateXYM(
                inputLongitude == null ? Instancio.gen().spatial().coordinate().lon().get() : inputLongitude,
                inputLatitude == null ? Instancio.gen().spatial().coordinate().lat().get() : inputLatitude,
                measure == null ? Instancio.gen().doubles().get() : measure
        );
    }
}
