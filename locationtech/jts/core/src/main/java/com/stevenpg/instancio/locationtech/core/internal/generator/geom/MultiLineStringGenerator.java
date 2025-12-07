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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.MultiLineStringGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.MultiLineStringSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for creating a MultiLineString.
 */
public class MultiLineStringGenerator implements MultiLineStringSpec, MultiLineStringGeneratorSpec, EnvelopableGenerator<MultiLineString> {
    private final static GeometryFactory defaultGeometryFactory = new GeometryFactory();
    private final static java.util.Random random = new java.util.Random();
    private final static LineStringGenerator lineStringGenerator = new LineStringGenerator();

    private GeometryFactory inputGeometryFactory;
    private Integer inputLength;
    private Envelope inputEnvelope;
    private List<LineString> inputLineStrings;

    /**
     * Default constructor.
     */
    public MultiLineStringGenerator() {
    }

    @Override
    public MultiLineStringGenerator lineStrings(List<LineString> points) {
        this.inputLineStrings = points;
        return this;
    }

    @Override
    public MultiLineStringGenerator length(int length) {
        this.inputLength = length;
        return this;
    }

    @Override
    public MultiLineStringGenerator geometryFactory(GeometryFactory geometryFactory) {
        this.inputGeometryFactory = geometryFactory;
        return this;
    }

    @Override
    public MultiLineStringGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public MultiLineString generate(Random random) {
        var geometryFactory = inputGeometryFactory != null ? inputGeometryFactory : defaultGeometryFactory;
        if (inputLineStrings != null) {
            return new MultiLineString(inputLineStrings.toArray(new LineString[0]), geometryFactory);
        } else {

            var length = random != null
                    ? random.intRange(2, 10)
                    : MultiLineStringGenerator.random.nextInt(2, 10);
            if (inputLength != null) {
                length = inputLength;
            }
            if (inputEnvelope != null) {
                var lineStringList = new ArrayList<LineString>();
                for (int i = 0; i < length; i++) {
                    lineStringList.add(lineStringGenerator.within(inputEnvelope).generate(random));
                }
                return new MultiLineString(lineStringList.toArray(new LineString[0]), geometryFactory);
            } else {
                var lineStringList = new ArrayList<LineString>();
                for (int i = 0; i < length; i++) {
                    lineStringList.add(lineStringGenerator.generate(random));
                }
                return new MultiLineString(lineStringList.toArray(new LineString[0]), geometryFactory);
            }
        }
    }
}
