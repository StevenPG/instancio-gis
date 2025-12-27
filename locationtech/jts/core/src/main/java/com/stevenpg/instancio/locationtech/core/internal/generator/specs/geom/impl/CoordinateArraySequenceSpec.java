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

package com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.impl;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.CoordinateArraySequenceGenerator;
import org.locationtech.jts.geom.Coordinate;

import java.util.List;

/**
 * Spec for generating a CoordinateSequence.
 *
 * @since 1.0.0
 */
public interface CoordinateArraySequenceSpec extends CoordinateArraySequenceGeneratorSpec {

    @Override
    CoordinateArraySequenceSpec coordinateArraySequence(List<Coordinate> coordinateSequence);

    @Override
    CoordinateArraySequenceGenerator length(int length);

    @Override
    CoordinateArraySequenceGenerator length(int min, int max);
}
