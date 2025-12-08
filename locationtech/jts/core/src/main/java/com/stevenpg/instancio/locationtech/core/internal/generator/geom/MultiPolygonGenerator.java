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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.MultiPolygonGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.MultiPolygonSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for creating a MultiPolygon.
 *
 * @since 1.0.0
 */
public class MultiPolygonGenerator implements MultiPolygonSpec, MultiPolygonGeneratorSpec, EnvelopableGenerator<MultiPolygon> {
    private final static GeometryFactory defaultGeometryFactory = new GeometryFactory();
    private final static java.util.Random random = new java.util.Random();
    private final static PolygonGenerator polygonGenerator = new PolygonGenerator();

    private GeometryFactory inputGeometryFactory;
    private Integer inputLength;
    private Envelope inputEnvelope;
    private List<Polygon> inputPolygons;

    /**
     * Default constructor.
     */
    public MultiPolygonGenerator() {
    }

    @Override
    public MultiPolygonGenerator polygons(List<Polygon> polygons) {
        this.inputPolygons = polygons;
        return this;
    }

    @Override
    public MultiPolygonGenerator length(int length) {
        this.inputLength = length;
        return this;
    }

    @Override
    public MultiPolygonGenerator geometryFactory(GeometryFactory geometryFactory) {
        this.inputGeometryFactory = geometryFactory;
        return this;
    }

    @Override
    public MultiPolygonGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public MultiPolygon generate(Random random) {
        var geometryFactory = inputGeometryFactory != null ? inputGeometryFactory : defaultGeometryFactory;
        if (inputPolygons != null) {
            return new MultiPolygon(inputPolygons.toArray(new Polygon[0]), geometryFactory);
        } else {

            var length = random != null
                    ? random.intRange(1, 5)
                    : MultiPolygonGenerator.random.nextInt(1, 5);
            if (inputLength != null) {
                length = inputLength;
            }
            if (inputEnvelope != null) {
                var polygonList = new ArrayList<Polygon>();
                for (int i = 0; i < length; i++) {
                    polygonList.add(new PolygonGenerator().within(inputEnvelope).generate(random));
                }
                return new MultiPolygon(polygonList.toArray(new Polygon[0]), geometryFactory);
            } else {
                var polygonList = new ArrayList<Polygon>();
                for (int i = 0; i < length; i++) {
                    polygonList.add(new PolygonGenerator().generate(random));
                }
                return new MultiPolygon(polygonList.toArray(new Polygon[0]), geometryFactory);
            }
        }
    }
}
