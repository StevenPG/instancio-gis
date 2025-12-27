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

package com.stevenpg.instancio.locationtech.core.internal.generator.geom;

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.EnvelopableGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.TriangleGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.TriangleSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.*;

import java.util.Objects;

/**
 * Generator for creating a Triangle.
 *
 * @since 1.0.0
 */
public class TriangleGenerator implements TriangleSpec, TriangleGeneratorSpec, EnvelopableGenerator<Triangle> {

    private static final PointGenerator pointGenerator = new PointGenerator();

    private Envelope inputEnvelope;
    private Point input0;
    private Point input1;
    private Point input2;

    /**
     * Default constructor.
     */
    public TriangleGenerator() {
        // No custom instantiations needed
    }

    @Override
    public TriangleGenerator points(Point p0, Point p1, Point p2) {
        this.input0 = p0;
        this.input1 = p1;
        this.input2 = p2;
        return this;
    }

    @Override
    public TriangleGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public Triangle generate(Random random) {
        if (Objects.nonNull(input0) && Objects.nonNull(input1) && Objects.nonNull(input2)) {
            return new Triangle(
                    input0.getCoordinate(),
                    input1.getCoordinate(),
                    input2.getCoordinate());
        } else {
            if (inputEnvelope != null) {
                var point0 = pointGenerator.within(inputEnvelope).generate(random);
                var point1 = pointGenerator.within(inputEnvelope).generate(random);
                var point2 = pointGenerator.within(inputEnvelope).generate(random);
                return new Triangle(point0.getCoordinate(), point1.getCoordinate(), point2.getCoordinate());
            } else {
                var point0 = pointGenerator.generate(random);
                var point1 = pointGenerator.generate(random);
                var point2 = pointGenerator.generate(random);
                return new Triangle(point0.getCoordinate(), point1.getCoordinate(), point2.getCoordinate());
            }
        }
    }
}
