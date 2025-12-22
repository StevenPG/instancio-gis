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

package com.stevenpg.instancio.postgis.jdbc.internal.generator.pg;

import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.geometric.*;

import static org.junit.jupiter.api.Assertions.*;

class OtherPostgresGeometricGeneratorsTest {

    @Test
    void shouldGenerateLsegAndLine() {
        var lseg = new PGLsegGenerator().xRange(-1, 1).yRange(-1, 1).generate(null);
        assertNotNull(lseg);
        var line = new PGLineGenerator().xRange(-1, 1).yRange(-1, 1).generate(null);
        assertNotNull(line);
    }

    @Test
    void shouldGeneratePathPolygonBoxCircle() {
        var path = new PGPathGenerator().vertices(2, 5).xRange(-2, 2).yRange(-2, 2).generate(null);
        assertNotNull(path);

        var poly = new PGPolygonGenerator().vertices(3, 6).xRange(-3, 3).yRange(-3, 3).generate(null);
        assertNotNull(poly);

        var box = new PGBoxGenerator().xRange(-5, 5).yRange(-5, 5).generate(null);
        assertNotNull(box);

        var circle = new PGCircleGenerator().radiusRange(0.5, 2.5).xRange(-5, 5).yRange(-5, 5).generate(null);
        assertNotNull(circle);
    }

    @RepeatedTest(2)
    void instancioShouldCreateViaSPI() {
        assertNotNull(Instancio.create(PGpoint.class));
        assertNotNull(Instancio.create(PGlseg.class));
        assertNotNull(Instancio.create(PGline.class));
        assertNotNull(Instancio.create(PGpath.class));
        assertNotNull(Instancio.create(PGpolygon.class));
        assertNotNull(Instancio.create(PGbox.class));
        assertNotNull(Instancio.create(PGcircle.class));
    }
}
