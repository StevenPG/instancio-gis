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

package com.stevenpg.instancio.proj4j.internal.generator;

import com.stevenpg.instancio.proj4j.internal.generator.specs.CrsSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;

/**
 * Generator for creating random {@link CoordinateReferenceSystem} instances.
 * <p>
 * By default, randomly selects from a set of common EPSG codes:
 * EPSG:4326 (WGS 84), EPSG:3857 (Web Mercator), and EPSG:32632 (UTM zone 32N).
 * Custom EPSG codes can be provided via {@link #epsgCodes(String...)}.
 *
 * @since 1.0.0
 */
public class CoordinateReferenceSystemGenerator implements Generator<CoordinateReferenceSystem>, CrsSpec<CoordinateReferenceSystemGenerator> {

    private String[] epsgCodes = {"EPSG:4326", "EPSG:3857", "EPSG:32632"};

    /**
     * Default constructor.
     */
    public CoordinateReferenceSystemGenerator() {
        // No custom instantiations needed
    }

    /**
     * Sets the EPSG codes from which the generator will randomly select.
     *
     * @param codes one or more EPSG code strings (e.g., "EPSG:4326")
     * @return this generator instance for method chaining
     */
    @Override
    public CoordinateReferenceSystemGenerator epsgCodes(String... codes) {
        this.epsgCodes = codes;
        return this;
    }

    /**
     * Generates a random {@link CoordinateReferenceSystem} by selecting a random
     * EPSG code from the configured set and resolving it via {@link CRSFactory}.
     *
     * @param random the random instance provided by Instancio
     * @return a new CoordinateReferenceSystem for the selected EPSG code
     */
    @Override
    public CoordinateReferenceSystem generate(Random random) {
        // Pick a random index from the available EPSG codes
        int index = random.intRange(0, epsgCodes.length - 1);
        String code = epsgCodes[index];
        return new CRSFactory().createFromName(code);
    }
}
