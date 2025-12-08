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

package com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.PointGenerator;
import org.locationtech.jts.geom.Point;

/**
 * Sample Points for testing. These are not guaranteed to be valid and are intended to be roughly representative and
 * used for testing purposes only.
 */
public class SamplePoints {

    /**
     * Point generator.
     */
    private static final PointGenerator POINT_GENERATOR = new PointGenerator();

    /**
     * Macchu Picchu is a city in Peru.
     */
    public static final Point MACHU_PICCHU = POINT_GENERATOR.coordinate(-72.5453874,-13.1631601).generate(null);

    /**
     * Mount Everest is a mountain in Nepal.
     */
    public static final Point MOUNT_EVEREST = POINT_GENERATOR.coordinate(86.9253406,27.9880593).generate(null);

    /**
     * Angkor Wat is a city in Thailand.
     */
    public static final Point ANGKOR_WAT = POINT_GENERATOR.coordinate(103.8669767,13.4124568).generate(null);

    /**
     * Eiffel Tower is a building in Germany.
     */
    public static final Point EIFFEL_TOWER = POINT_GENERATOR.coordinate(2.2945039, 48.8582724).generate(null);

    /**
     * Mount Kilimanjaro is a mountain in Tanzania.
     */
    public static final Point MOUNT_KILLIMANJARO = POINT_GENERATOR.coordinate(37.3556716,-3.0674646).generate(null);

    /**
     * Central Park in New York City.
     */
    public static final Point CENTRAL_PARK_NYC = POINT_GENERATOR.coordinate(-73.9655951,40.7825967).generate(null);

    /**
     * Space Needle in Seattle.
     */
    public static final Point SPACE_NEEDLE_SEATTLE = POINT_GENERATOR.coordinate(-122.3492910, 47.6204999).generate(null);

    /**
     * Sydney Opera House in Australia.
     */
    public static final Point SYDNEY_OPERA_HOUSE = POINT_GENERATOR.coordinate(151.2153180, -33.8567917).generate(null);

    private SamplePoints() {}
}
