package com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility;

import org.locationtech.jts.geom.Envelope;

/**
 * Sample Envelopes for testing. These are not guaranteed to be valid and are intended to be roughly representative and
 * used for testing purposes only.
 */
public final class SampleEnvelopes {

    public static final Envelope WORLD = new Envelope(-180, 180, -90, 90);
    public static final Envelope NEW_YORK_CITY = new Envelope(-74.2226031143777, 40.94553036267402,
            -73.75468910748152, 40.531048935705286);

    /**
     * Private constructor to prevent instantiation.
     */
    private SampleEnvelopes() {}

}
