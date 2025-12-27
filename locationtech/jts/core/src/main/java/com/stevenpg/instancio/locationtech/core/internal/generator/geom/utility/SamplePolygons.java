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

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

/**
 * Sample Polygons for testing. These are not guaranteed to be valid and are intended to be roughly representative and
 * used for testing purposes only.
 */
public class SamplePolygons {

    private static final GeometryFactory GF = new GeometryFactory();

    public static final Polygon CENTRAL_PARK_NYC_USA = GF.createPolygon(List.of(
                    new Coordinate(-73.95830171602019, 40.80015748479991),
                    new Coordinate(-73.98126397430777, 40.76871183900582),
                    new Coordinate(-73.9811741393241, 40.767883038364886),
                    new Coordinate(-73.97308133609236, 40.76447749358462),
                    new Coordinate(-73.94964949496267, 40.796608223737394),
                    new Coordinate(-73.94972290461409, 40.7969447518025),
                    new Coordinate(-73.9576676892954, 40.8002712087324),
                    new Coordinate(-73.95830171602019, 40.80015748479991)
            ).toArray(Coordinate[]::new)
    );

    public static final Polygon NIIHAU_HAWAII_USA = GF.createPolygon(List.of(
                    new Coordinate(-160.07002521691666,
                            21.89314669232479),
                    new Coordinate(-160.0791728203031,
                            21.92756562878401),
                    new Coordinate(-160.06341861447103,
                            21.96857458810227),
                    new Coordinate(-160.04664800826265,
                            21.97611517758881),
                    new Coordinate(-160.04563160788635,
                            21.99496489907804),
                    new Coordinate(-160.08374662199626,
                            22.010042873076785),
                    new Coordinate(-160.1101730317792,
                            22.00061932721566),
                    new Coordinate(-160.12796003836374,
                            21.96150492186304),
                    new Coordinate(-160.16963245379048,
                            21.94453628781335),
                    new Coordinate(-160.23061647636635,
                            21.894089787662423),
                    new Coordinate(-160.2524690844561,
                            21.853531057634214),
                    new Coordinate(-160.24840348295103,
                            21.801636826684927),
                    new Coordinate(-160.20114086545465,
                            21.77001930349735),
                    new Coordinate(-160.15082904682964,
                            21.861077740480667),
                    new Coordinate(-160.07002521691666,
                            21.89314669232479)
            ).toArray(Coordinate[]::new)
    );

    public static final Polygon BUCKINGHAM_PALACE_UK = GF.createPolygon(List.of(
                    new Coordinate(-0.14222991268587748,
                            51.501751667261175
                    ),
                    new Coordinate(-0.14355210649341643,
                            51.50121667943557
                    ),
                    new Coordinate(-0.14367551124939837,
                            51.50131270335376
                    ),
                    new Coordinate(-0.14403691088992332,
                            51.50116180853399
                    ),
                    new Coordinate(-0.1425780903886391,
                            51.49983665620405
                    ),
                    new Coordinate(-0.14177595947808186,
                            51.500160402980185
                    ),
                    new Coordinate(-0.14208447136729774,
                            51.50044848080498
                    ),
                    new Coordinate(-0.14190377154704947,
                            51.50051432690918
                    ),
                    new Coordinate(-0.14151152071659112,
                            51.500481403868804
                    ),
                    new Coordinate(-0.14102230900829227,
                            51.50067345460175
                    ),
                    new Coordinate(-0.14222991268587748,
                            51.501751667261175
                    )
            ).toArray(Coordinate[]::new)
    );

    public static final Polygon ROMAN_COLOSSEUM_ITALY = GF.createPolygon(List.of(
            new Coordinate(12.492651931486648,
                    41.8902839371533),
            new Coordinate(12.4924914154129,
                    41.89035861969904),
            new Coordinate(12.492312268008959,
                    41.89039916161616),
            new Coordinate(12.492103023840912,
                    41.89040022850875),
            new Coordinate(12.49192960915434,
                    41.89031060950114),
            new Coordinate(12.491994102219309,
                    41.890201786251225),
            new Coordinate(12.492114489274854,
                    41.89011003317157),
            new Coordinate(12.492307968471351,
                    41.890039617927926),
            new Coordinate(12.492521512176182,
                    41.89001401236541),
            new Coordinate(12.492651931486648,
                    41.8902839371533)
            ).toArray(Coordinate[]::new)
    );

    /**
     * Private constructor.
     */
    private SamplePolygons() {
    }
}
