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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.MultiPointGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.MultiPointSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for creating a MultiPoint.
 *
 * @since 1.0.0
 */
public class MultiPointGenerator implements MultiPointSpec, MultiPointGeneratorSpec, EnvelopableGenerator<MultiPoint> {

    private static final GeometryFactory defaultGeometryFactory = new GeometryFactory();
    private static final java.util.Random random = new java.util.Random();
    private static final PointGenerator pointGenerator = new PointGenerator();

    private GeometryFactory inputGeometryFactory;
    private Integer inputLength;
    private Envelope inputEnvelope;
    private List<Point> inputPoints;

    /**
     * Default constructor.
     */
    public MultiPointGenerator() {
        // No custom instantiations needed
    }

    @Override
    public MultiPointGenerator points(List<Point> points) {
        this.inputPoints = points;
        return this;
    }

    @Override
    public MultiPointGenerator length(int length) {
        this.inputLength = length;
        return this;
    }

    @Override
    public MultiPointGenerator geometryFactory(GeometryFactory geometryFactory) {
        this.inputGeometryFactory = geometryFactory;
        return this;
    }

    @Override
    public MultiPointGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public MultiPoint generate(Random random) {
        var geometryFactory = inputGeometryFactory != null ? inputGeometryFactory : defaultGeometryFactory;
        if (inputPoints != null) {
            return new MultiPoint(inputPoints.toArray(new Point[0]), geometryFactory);
        } else {

            var length = random != null
                    ? random.intRange(2, 10)
                    : MultiPointGenerator.random.nextInt(2, 10);
            if (inputLength != null) {
                length = inputLength;
            }
            if (inputEnvelope != null) {
                var pointList = new ArrayList<Point>();
                for (int i = 0; i < length; i++) {
                    pointList.add(pointGenerator.within(inputEnvelope).generate(random));
                }
                return new MultiPoint(pointList.toArray(new Point[0]), geometryFactory);
            } else {
                var pointList = new ArrayList<Point>();
                for (int i = 0; i < length; i++) {
                    pointList.add(pointGenerator.generate(random));
                }
                return new MultiPoint(pointList.toArray(new Point[0]), geometryFactory);
            }
        }
    }
}
