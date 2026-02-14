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

package com.stevenpg.instancio.locationtech.core.internal.spi;

import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.locationtech.jts.geom.impl.PackedCoordinateSequence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocationtechjtsCoreGeomServiceProviderTest {

    @RepeatedTest(5)
    void shouldAutoGenerateCoordinate() {
        assertNotNull(Instancio.create(Coordinate.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateCoordinateSequence() {
        assertNotNull(Instancio.create(CoordinateSequence.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateCoordinateList() {
        assertNotNull(Instancio.create(CoordinateList.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateCoordinateXY() {
        assertNotNull(Instancio.create(CoordinateXY.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateCoordinateXYM() {
        assertNotNull(Instancio.create(CoordinateXYM.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateCoordinateXYZM() {
        assertNotNull(Instancio.create(CoordinateXYZM.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateEnvelope() {
        assertNotNull(Instancio.create(Envelope.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePoint() {
        assertNotNull(Instancio.create(Point.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateLineString() {
        assertNotNull(Instancio.create(LineString.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateLinearRing() {
        assertNotNull(Instancio.create(LinearRing.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePolygon() {
        assertNotNull(Instancio.create(Polygon.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateMultiPoint() {
        assertNotNull(Instancio.create(MultiPoint.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateMultiLineString() {
        assertNotNull(Instancio.create(MultiLineString.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateMultiPolygon() {
        assertNotNull(Instancio.create(MultiPolygon.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateGeometryCollection() {
        assertNotNull(Instancio.create(GeometryCollection.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateGeometry() {
        assertNotNull(Instancio.create(Geometry.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateLineSegment() {
        assertNotNull(Instancio.create(LineSegment.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateOctagonalEnvelope() {
        assertNotNull(Instancio.create(OctagonalEnvelope.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateTriangle() {
        assertNotNull(Instancio.create(Triangle.class));
    }

    @RepeatedTest(5)
    void shouldAutoGenerateCoordinateArraySequence() {
        assertNotNull(Instancio.create(CoordinateArraySequence.class));
    }

    @RepeatedTest(5)
    void shouldAutoGeneratePackedCoordinateSequence() {
        assertNotNull(Instancio.create(PackedCoordinateSequence.class));
    }
}
