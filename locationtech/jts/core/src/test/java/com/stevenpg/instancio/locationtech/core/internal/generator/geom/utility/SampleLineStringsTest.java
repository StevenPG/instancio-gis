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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.MultiLineStringGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SampleLineStringsTest {

    @Test
    void linestringGeoJsonValidation() {
        var multiLineString = new MultiLineStringGenerator()
                .lineStrings(
                        List.of(
                                SampleLineStrings.DOVER_MOTOR_SPEEDWAY_DELAWARE,
                                SampleLineStrings.DESERT_ROAD_NEW_MEXICO
                        )
                )
                .generate(null);

        assertNotNull(multiLineString);
        // https://atlas.co/tools/wkt-to-geojson/
        System.out.println(multiLineString.toText());
    }

}