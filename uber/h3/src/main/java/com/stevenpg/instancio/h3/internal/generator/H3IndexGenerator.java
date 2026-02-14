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

import com.stevenpg.instancio.h3.internal.generator.specs.H3IndexSpec;
import com.uber.h3core.H3Core;
import org.instancio.Random;
import org.instancio.generator.Generator;

import java.io.IOException;

/**
 * Generator for creating valid H3 cell index values ({@link Long}) by
 * converting a random latitude/longitude pair into an H3 cell at a
 * configurable resolution.
 *
 * <p>By default, generates indices within the full geographic range
 * (latitude -90 to 90, longitude -180 to 180) at resolution 7.
 * The ranges and resolution can be customized via the
 * {@link H3IndexSpec} methods.</p>
 *
 * <p>H3 resolution ranges from 0 (coarsest, ~4,357 km edge length)
 * to 15 (finest, ~0.5 m edge length).</p>
 *
 * @since 1.0.0
 */
public class H3IndexGenerator implements Generator<Long>, H3IndexSpec<H3IndexGenerator> {

    private double minLat = -90;
    private double maxLat = 90;
    private double minLng = -180;
    private double maxLng = 180;
    private int resolution = 7;

    /**
     * Default constructor.
     */
    public H3IndexGenerator() {
        // No custom instantiations needed
    }

    @Override
    public H3IndexGenerator latRange(double minLat, double maxLat) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        return this;
    }

    @Override
    public H3IndexGenerator lngRange(double minLng, double maxLng) {
        this.minLng = minLng;
        this.maxLng = maxLng;
        return this;
    }

    /**
     * Sets the H3 resolution level. Values outside the valid range (0-15)
     * are clamped to the nearest boundary.
     *
     * @param resolution the desired H3 resolution
     * @return this instance for method chaining
     */
    @Override
    public H3IndexGenerator resolution(int resolution) {
        // Clamp resolution to the valid H3 range of 0-15
        this.resolution = Math.max(0, Math.min(15, resolution));
        return this;
    }

    @Override
    public Long generate(Random random) {
        try {
            H3Core h3 = H3Core.newInstance();
            double lat = random.doubleRange(minLat, maxLat);
            double lng = random.doubleRange(minLng, maxLng);
            return h3.latLngToCell(lat, lng, resolution);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to initialize H3Core", e);
        }
    }
}
