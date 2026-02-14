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

package com.stevenpg.instancio.h3.internal.generator;

import com.stevenpg.instancio.h3.internal.generator.specs.LatLngRangeSpec;
import com.uber.h3core.util.LatLng;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for creating H3 {@link LatLng} instances with random
 * latitude and longitude values within configurable ranges.
 *
 * <p>By default, generates coordinates within the full geographic range
 * (latitude -90 to 90, longitude -180 to 180). The ranges can be
 * customized via the {@link LatLngRangeSpec} methods.</p>
 *
 * @since 1.0.0
 */
public class LatLngGenerator implements Generator<LatLng>, LatLngRangeSpec<LatLngGenerator> {

    private double minLat = -90;
    private double maxLat = 90;
    private double minLng = -180;
    private double maxLng = 180;

    /**
     * Default constructor.
     */
    public LatLngGenerator() {
        // No custom instantiations needed
    }

    @Override
    public LatLngGenerator latRange(double minLat, double maxLat) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        return this;
    }

    @Override
    public LatLngGenerator lngRange(double minLng, double maxLng) {
        this.minLng = minLng;
        this.maxLng = maxLng;
        return this;
    }

    @Override
    public LatLng generate(Random random) {
        double lat = random.doubleRange(minLat, maxLat);
        double lng = random.doubleRange(minLng, maxLng);
        return new LatLng(lat, lng);
    }
}
