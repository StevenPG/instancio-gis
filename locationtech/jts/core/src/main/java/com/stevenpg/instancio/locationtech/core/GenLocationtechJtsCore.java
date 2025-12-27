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

package com.stevenpg.instancio.locationtech.core;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.*;
import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.CoordinateArraySequenceGenerator;

/**
 * Provides access to the Locationtech JTS core generator provider.
 *
 * @since 1.0.0
 */
public class GenLocationtechJtsCore {


    /**
     * Access to the Generator Spec for org.locationtech.jts.core.geom.CoordinateArraySequence.
     * @return generator spec
     */
    public static CoordinateArraySequenceGenerator coordinateArraySequence() {
        return new CoordinateArraySequenceGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.Coordinate.
     * @return generator
     */
    public static CoordinateGenerator coordinate() {
        return new CoordinateGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.CoordinateSequence.
     * @return generator
     */
    public static CoordinateSequenceGenerator coordinateSequence() {
        return new CoordinateSequenceGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.CoordinateXY.
     * @return generator
     */
    public static CoordinateXYGenerator coordinateXY() {
        return new CoordinateXYGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.CoordinateXYM.
     * @return generator
     */
    public static CoordinateXYMGenerator coordinateXYM() {
        return new CoordinateXYMGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.CoordinateXYZM.
     * @return generator
     */
    public static CoordinateXYZMGenerator coordinateXYZM() {
        return new CoordinateXYZMGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.LineString.
     * @return generator
     */
    public static LineStringGenerator lineString() {
        return new LineStringGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.LinearRing.
     * @return generator
     */
    public static LinearRingGenerator linearRing() {
        return new LinearRingGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.core.geom.Point.
     *
     * @return generator spec
     * @since 1.0.0
     */
    public static PointGenerator point() {
        return new PointGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.MultiLineString.
     * @return generator
     * @since 1.0.0
     */
    public static MultiLineStringGenerator multiLineString() {
        return new MultiLineStringGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.MultiPoint.
     * @return generator
     * @since 1.0.0
     */
    public static MultiPointGenerator multiPoint() {
        return new MultiPointGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.Envelope.
     * @return generator
     * @since 1.0.0
     */
    public static EnvelopeGenerator envelope() {
        return new EnvelopeGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.OctagonalEnvelope.
     * @return generator
     * @since 1.0.0
     */
    public static OctagonalEnvelopeGenerator octagonalEnvelope() {
        return new OctagonalEnvelopeGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.Polygon.
     * @return generator
     * @since 1.0.0
     */
    public static PolygonGenerator polygon() {
        return new PolygonGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.MultiPolygon.
     * @return generator
     * @since 1.0.0
     */
    public static MultiPolygonGenerator multiPolygon() {
        return new MultiPolygonGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.LineSegment.
     * @return generator
     * @since 1.0.0
     */
    public static LineSegmentGenerator lineSegment() {
        return new LineSegmentGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.GeometryCollection.
     * @return generator
     * @since 1.0.0
     */
    public static GeometryCollectionGenerator geometryCollection() {
        return new GeometryCollectionGenerator();
    }

    /**
     * Access to the Generator for org.locationtech.jts.geom.Geometry.
     * @return generator
     * @since 1.0.0
     */
    public static GeometryGenerator geometry() {
        return new GeometryGenerator();
    }

    private GenLocationtechJtsCore() {
        // private constructor to prevent instantiation
    }

}
