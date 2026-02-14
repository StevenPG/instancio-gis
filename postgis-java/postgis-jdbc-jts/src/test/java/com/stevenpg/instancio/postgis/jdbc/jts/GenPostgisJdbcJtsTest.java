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

package com.stevenpg.instancio.postgis.jdbc.jts;

import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class GenPostgisJdbcJtsTest {

    @RepeatedTest(10)
    void jtsGeometry() {
        var generator = GenPostgisJdbcJts.jtsGeometry();
        assertNotNull(generator);
        var result = generator.generate(new DefaultRandom());
        assertNotNull(result);
        assertInstanceOf(net.postgis.jdbc.jts.JtsGeometry.class, result);
    }
}
