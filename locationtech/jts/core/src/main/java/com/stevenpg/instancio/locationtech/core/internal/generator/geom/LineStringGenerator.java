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

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.LineStringGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.LineStringSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

/**
 * Generator for creating a LineString.
 *
 * @since 1.0.0
 */
public class LineStringGenerator implements LineStringSpec, LineStringGeneratorSpec, EnvelopableGenerator<LineString> {

    private final static GeometryFactory defaultGeometryFactory = new GeometryFactory();
    private final static java.util.Random random = new java.util.Random();

    private GeometryFactory inputGeometryFactory;
    private CoordinateSequence inputCoordinateSequence;
    private Integer inputLength;
    private Envelope inputEnvelope;

    /**
     * Default constructor.
     */
    public LineStringGenerator() {
    }

    @Override
    public LineStringGenerator coordinateSequence(CoordinateSequence coordinateSequence) {
        this.inputCoordinateSequence = coordinateSequence;
        return this;
    }

    @Override
    public LineStringGenerator length(int length) {
        this.inputLength = length;
        return this;
    }

    @Override
    public LineStringGenerator geometryFactory(GeometryFactory geometryFactory) {
        this.inputGeometryFactory = geometryFactory;
        return this;
    }

    @Override
    public LineStringGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public LineString generate(Random random) {
        var geometryFactory = inputGeometryFactory != null ? inputGeometryFactory : defaultGeometryFactory;
        if (inputCoordinateSequence != null) {
            return geometryFactory.createLineString(inputCoordinateSequence);
        } else {
            var sequenceGenerator = new CoordinateSequenceGenerator();
            var length = random != null
                    ? random.intRange(2, 10)
                    : LineStringGenerator.random.nextInt(2, 10);
            if (inputLength != null) {
                length = inputLength;
            }
            if (inputEnvelope != null) {
                return geometryFactory.createLineString(sequenceGenerator.length(length).within(inputEnvelope).generate(random));
            } else {
                return geometryFactory.createLineString(sequenceGenerator.length(length).generate(random));
            }
        }
    }
}
