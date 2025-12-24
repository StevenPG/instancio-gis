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

import net.postgis.jdbc.PGbox2d;
import net.postgis.jdbc.PGbox3d;
import net.postgis.jdbc.geometry.Point;
import org.junit.jupiter.api.Test;

public class ExploreBoxTest {
    @Test
    void explore() {
        PGbox2d box2d = new PGbox2d();
        // Check fields/methods via compilation or reflection if needed, 
        // but here I'll just try common ones
        System.out.println(box2d.getLLB());
        System.out.println(box2d.getURT());
        
        PGbox3d box3d = new PGbox3d();
        System.out.println(box3d.getLLB());
        System.out.println(box3d.getURT());
    }
}
