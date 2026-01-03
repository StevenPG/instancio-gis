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

import org.instancio.Random;
import org.locationtech.jts.geom.Envelope;

/**
 * Receives an envelope and contains helper methods for supporting "within" generation operations.
 */
public class WithinUtility {

    /**
     * Private constructor to prevent instantiation.
     */
    private WithinUtility() {
    }

    /**
     * Returns a BoundsRecord containing the envelope's bounds.
     *
     * @param envelope - envelope to extract bounds from
     * @return - a BoundsRecord containing the envelope's bounds
     */
    public static BoundsRecord getBounds(final Envelope envelope) {
        return new BoundsRecord(envelope.getMinX(), envelope.getMaxX(), envelope.getMinY(), envelope.getMaxY());
    }

    /**
     * Returns a random longitude and latitude within the specified envelope.
     *
     * @param random - the random instance to use
     * @return - a LonLatRecord containing the generated longitude and latitude
     */
    public static LonLatRecord randomLonLatInBounds(Random random) {
        double longitude = random.doubleRange(-180, 180);
        double latitude = random.doubleRange(-90, 90);
        return new LonLatRecord(longitude, latitude);
    }

    /**
     * Returns a random longitude and latitude within the specified envelope.
     *
     * @param random - the random instance to use
     * @param envelope - envelope to generate random coordinates within
     * @return - a LonLatRecord containing the generated longitude and latitude
     */
    public static LonLatRecord randomLonLatInBounds(Random random, Envelope envelope) {
        if (envelope == null) {
            return randomLonLatInBounds(random);
        }
        var longitude = random.doubleRange(envelope.getMinX(), envelope.getMaxX());
        var latitude = random.doubleRange(envelope.getMinY(), envelope.getMaxY());
        return new LonLatRecord(longitude, latitude);
    }

}
