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

package com.stevenpg.app.postgis.jdbc;

import org.instancio.Random;
import org.instancio.support.DefaultRandom;

import com.stevenpg.instancio.postgis.jdbc.GenPostgisJdbc;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.postgresql.geometric.*;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PostgresGeometricGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGeneratePGpointWithInstancio() {
        PGpoint p = Instancio.create(PGpoint.class);
        assertNotNull(p);
        assertNotEquals(0, p.x);
        assertNotEquals(0, p.y);
    }

    @Test
    void shouldGeneratePGboxWithInstancio() {
        PGbox p = Instancio.create(PGbox.class);
        assertNotNull(p);
        assertNotNull(p.point[0]);
        assertNotNull(p.point[1]);
    }

    @Test
    void shouldGeneratePGcircleWithInstancio() {
        PGcircle p = Instancio.create(PGcircle.class);
        assertNotNull(p);
        assertNotNull(p.center);
        assertNotEquals(0, p.radius);
    }

    @Test
    void shouldGeneratePGlineWithInstancio() {
        PGline p = Instancio.create(PGline.class);
        assertNotNull(p);
        assertNotEquals(0, p.a);
        assertNotEquals(0, p.b);
    }

    @Test
    void shouldGeneratePGlsegWithInstancio() {
        PGlseg p = Instancio.create(PGlseg.class);
        assertNotNull(p);
        assertNotNull(p.point[0]);
        assertNotNull(p.point[1]);
    }

    @Test
    void shouldGeneratePGpathWithInstancio() {
        PGpath p = Instancio.create(PGpath.class);
        assertNotNull(p);
        assertNotEquals(0, p.points.length);
    }

    @Test
    void shouldGeneratePGpolygonWithInstancio() {
        PGpolygon p = Instancio.create(PGpolygon.class);
        assertNotNull(p);
        assertNotEquals(0, p.points.length);
    }

    @Test
    void shouldGeneratePGbox2dWithInstancio() {
        net.postgis.jdbc.PGbox2d p = Instancio.create(net.postgis.jdbc.PGbox2d.class);
        assertNotNull(p);
        assertNotNull(p.getLLB());
        assertNotNull(p.getURT());
    }

    @Test
    void shouldGeneratePGbox3dWithInstancio() {
        net.postgis.jdbc.PGbox3d p = Instancio.create(net.postgis.jdbc.PGbox3d.class);
        assertNotNull(p);
        assertNotNull(p.getLLB());
        assertNotNull(p.getURT());
    }

    @Test
    void shouldGeneratePGpointWithGenerator() {
        PGpoint p = GenPostgisJdbc.pgPoint().xRange(10, 10).yRange(20, 20).generate(random);
        assertNotNull(p);
        assertEquals(10, p.x);
        assertEquals(20, p.y);
    }

    @Test
    void shouldGeneratePGcircleWithGenerator() {
        PGcircle c = GenPostgisJdbc.pgCircle().radiusRange(5, 5).generate(random);
        assertNotNull(c);
        assertEquals(5, c.radius);
    }

    @Test
    void shouldGeneratePGbox2dWithGenerator() {
        net.postgis.jdbc.PGbox2d p = GenPostgisJdbc.pgBox2d().xRange(10, 10).yRange(20, 20).generate(random);
        assertNotNull(p);
        assertEquals(10, p.getLLB().x);
        assertEquals(20, p.getLLB().y);
    }

    @Test
    void shouldGeneratePGbox3dWithGenerator() {
        net.postgis.jdbc.PGbox3d p = GenPostgisJdbc.pgBox3d().xRange(10, 10).yRange(20, 20).zRange(30, 30).generate(random);
        assertNotNull(p);
        assertEquals(10, p.getLLB().x);
        assertEquals(20, p.getLLB().y);
        assertEquals(30, p.getLLB().z);
    }
}
