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

package com.stevenpg.instancio.postgis.jdbc;

import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class GenPostgisJdbcTest {

    @RepeatedTest(10)
    void pgPoint() {
        var generator = GenPostgisJdbc.pgPoint();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.postgresql.geometric.PGpoint.class, result);
        assertTrue(result.x >= -180 && result.x <= 180);
        assertTrue(result.y >= -90 && result.y <= 90);
    }

    @RepeatedTest(10)
    void pgLine() {
        var generator = GenPostgisJdbc.pgLine();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.postgresql.geometric.PGline.class, result);
    }

    @RepeatedTest(10)
    void pgLseg() {
        var generator = GenPostgisJdbc.pgLseg();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.postgresql.geometric.PGlseg.class, result);
    }

    @RepeatedTest(10)
    void pgBox() {
        var generator = GenPostgisJdbc.pgBox();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.postgresql.geometric.PGbox.class, result);
    }

    @RepeatedTest(10)
    void pgCircle() {
        var generator = GenPostgisJdbc.pgCircle();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.postgresql.geometric.PGcircle.class, result);
        assertTrue(result.radius >= 0.1 && result.radius <= 100);
    }

    @RepeatedTest(10)
    void pgPath() {
        var generator = GenPostgisJdbc.pgPath();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.postgresql.geometric.PGpath.class, result);
        assertTrue(result.points.length >= 2);
    }

    @RepeatedTest(10)
    void pgPolygon() {
        var generator = GenPostgisJdbc.pgPolygon();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(org.postgresql.geometric.PGpolygon.class, result);
        assertTrue(result.points.length >= 3);
    }

    @RepeatedTest(10)
    void pgBox2d() {
        var generator = GenPostgisJdbc.pgBox2d();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.PGbox2d.class, result);
        assertTrue(result.getLLB().x <= result.getURT().x);
        assertTrue(result.getLLB().y <= result.getURT().y);
    }

    @RepeatedTest(10)
    void pgBox3d() {
        var generator = GenPostgisJdbc.pgBox3d();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.PGbox3d.class, result);
        assertTrue(result.getLLB().x <= result.getURT().x);
        assertTrue(result.getLLB().y <= result.getURT().y);
        assertTrue(result.getLLB().z <= result.getURT().z);
    }
}
