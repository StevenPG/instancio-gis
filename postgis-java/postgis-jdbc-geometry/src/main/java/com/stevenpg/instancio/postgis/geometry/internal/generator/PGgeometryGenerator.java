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
package com.stevenpg.instancio.postgis.geometry.internal.generator;

import com.stevenpg.instancio.postgis.geometry.internal.generator.specs.NumericRangeSpec;
import net.postgis.jdbc.PGgeometry;
import org.instancio.Random;
import org.instancio.generator.Generator;

/**
 * Generator for {@link PGgeometry}.
 */
public class PGgeometryGenerator implements Generator<PGgeometry>, NumericRangeSpec<PGgeometryGenerator> {

    private final GeometryGenerator geometryGenerator = new GeometryGenerator();

    @Override
    public PGgeometryGenerator xRange(double minX, double maxX) {
        geometryGenerator.xRange(minX, maxX);
        return this;
    }

    @Override
    public PGgeometryGenerator yRange(double minY, double maxY) {
        geometryGenerator.yRange(minY, maxY);
        return this;
    }

    @Override
    public PGgeometry generate(Random random) {
        return new PGgeometry(geometryGenerator.generate(random));
    }
}
