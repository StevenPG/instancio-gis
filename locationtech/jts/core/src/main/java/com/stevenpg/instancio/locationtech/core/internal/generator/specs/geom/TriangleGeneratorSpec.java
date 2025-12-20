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

package com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.TriangleGenerator;
import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.*;

/**
 * Spec for generating a Triangle.
 */
public interface TriangleGeneratorSpec extends GeneratorSpec<Triangle> {

    /**
     * Provide a list of points to load into a triangle. If all three points are not provided, a random
     * triangle will be created.
     *
     * @param p0 the first point in the triangle
     * @param p1 the second point in the triangle
     * @param p2 the third point in the triangle
     * @return spec builder
     */
    TriangleGenerator points(Point p0, Point p1, Point p2);

}
