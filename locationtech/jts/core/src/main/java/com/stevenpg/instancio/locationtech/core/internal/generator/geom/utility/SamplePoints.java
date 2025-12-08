package com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.PointGenerator;
import org.locationtech.jts.geom.Point;

/**
 * Sample Points for testing. These are not guaranteed to be valid and are intended to be roughly representative and
 * used for testing purposes only.
 */
public class SamplePoints {

    private static final PointGenerator POINT_GENERATOR = new PointGenerator();

    public static final Point MACHU_PICCHU = POINT_GENERATOR.coordinate(-72.5453874,-13.1631601).generate(null);
    public static final Point MOUNT_EVEREST = POINT_GENERATOR.coordinate(86.9253406,27.9880593).generate(null);
    public static final Point ANGKOR_WAT = POINT_GENERATOR.coordinate(103.8669767,13.4124568).generate(null);
    public static final Point EIFEL_TOWER = POINT_GENERATOR.coordinate(2.2945039, 48.8582724).generate(null);
    public static final Point MOUNT_KILLIMANJARO = POINT_GENERATOR.coordinate(37.3556716,-3.0674646).generate(null);
    public static final Point CENTRAL_PARK_NYC = POINT_GENERATOR.coordinate(-73.9655951,40.7825967).generate(null);
    public static final Point SPACE_NEEDLE_SEATTLE = POINT_GENERATOR.coordinate(-122.3492910, 47.6204999).generate(null);
    public static final Point SYDNEY_OPERA_HOUSE = POINT_GENERATOR.coordinate(151.2153180, -33.8567917).generate(null);

    private SamplePoints() {}
}
