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

package com.stevenpg.instancio.postgis.geometry.internal.spi;

import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostgisGeometryServiceProviderTest {

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
    void shouldAutoGeneratePGgeometry() {
        assertNotNull(Instancio.create(PGgeometry.class));
    }
}
