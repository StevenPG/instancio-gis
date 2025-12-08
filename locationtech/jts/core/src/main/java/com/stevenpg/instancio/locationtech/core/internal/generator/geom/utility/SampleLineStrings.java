package com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.LineStringGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.CoordinateArraySequenceGenerator;
import org.instancio.support.DefaultRandom;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.List;

public class SampleLineStrings {

    public static final LineString DESERT_ROAD_NEW_MEXICO = new LineStringGenerator()
            .coordinateSequence(new CoordinateArraySequenceGenerator().coordinateArraySequence(
                    List.of(
                            new Coordinate(-103.83824088381995,35.26271184639255),
                            new Coordinate(-103.97311253960697, 35.31496876106283),
                            new Coordinate(-103.9784028338242,35.31931221471413),
                            new Coordinate(-103.9806493885844,35.32220607360412),
                            new Coordinate(-103.98569610901953,35.324248355180444),
                            new Coordinate(-104.01583431806714,35.323916357482744),
                            new Coordinate(-104.02129998432882,35.32381939811914),
                            new Coordinate(-104.14355039686318,35.35115745357194)
                    )
            ).generate(new DefaultRandom()))
            .generate(new DefaultRandom());

    public static final LineString DOVER_MOTOR_SPEEDWAY_DELAWARE = new LineStringGenerator()
            .coordinateSequence(new CoordinateArraySequenceGenerator().coordinateArraySequence(
                    List.of(
                            new Coordinate(-75.53110117512355,39.192512575395284),
                            new Coordinate(-75.53216270979989,39.191991447576726),
                            new Coordinate(-75.53254881113303,39.19115483990703),
                            new Coordinate(-75.53184227063153,39.18774343618466),
                            new Coordinate(-75.53095771457849,39.18687254160338),
                            new Coordinate(-75.52983294544008,39.18668674403682),
                            new Coordinate(-75.52862868490249,39.18725031411256),
                            new Coordinate(-75.52819494232925,39.188089581337664),
                            new Coordinate(-75.52894001425955,39.19155886042668),
                            new Coordinate(-75.52973260408000,39.19236110115895),
                            new Coordinate(-75.53107572744969,39.19251685956547)
                    )
            ).generate(new DefaultRandom()))
            .generate(new DefaultRandom());


    /**
     * find famous straightaways and paths
     *
     * geat wall of china (straight segment)
     *
     *
     */
}
