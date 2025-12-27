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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.GeometryCollectionGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.GeometryCollectionSpec;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for creating a GeometryCollection. Can return a heterogeneous
 * GeometryCollection or one of the Multi* subclasses. If an envelope is set,
 * it will be passed through to underlying generators.
 *
 * @since 1.0.0
 */
public class GeometryCollectionGenerator implements GeometryCollectionSpec, GeometryCollectionGeneratorSpec,
        EnvelopableGenerator<GeometryCollection>, Generator<GeometryCollection> {

    private static final GeometryFactory defaultGeometryFactory = new GeometryFactory();
    private static final java.util.Random backupRandom = new java.util.Random();

    private GeometryFactory inputGeometryFactory;
    private Envelope inputEnvelope;
    private Integer inputLength;
    private List<Geometry> inputGeometries;
    private MultiPoint inputMultiPoint;
    private MultiLineString inputMultiLineString;
    private MultiPolygon inputMultiPolygon;
    private GeometryCollection inputGeometryCollection;

    /**
     * Default constructor.
     */
    public GeometryCollectionGenerator() {
        // No custom instantiations needed
    }

    @Override
    public GeometryCollectionGenerator geometryFactory(GeometryFactory geometryFactory) {
        this.inputGeometryFactory = geometryFactory;
        return this;
    }

    @Override
    public GeometryCollectionGenerator length(int length) {
        this.inputLength = length;
        return this;
    }

    @Override
    public GeometryCollectionGenerator geometries(List<Geometry> geometries) {
        this.inputGeometries = geometries;
        return this;
    }

    @Override
    public GeometryCollectionGenerator multiPoint(MultiPoint multiPoint) {
        this.inputMultiPoint = multiPoint;
        return this;
    }

    @Override
    public GeometryCollectionGenerator multiLineString(MultiLineString multiLineString) {
        this.inputMultiLineString = multiLineString;
        return this;
    }

    @Override
    public GeometryCollectionGenerator multiPolygon(MultiPolygon multiPolygon) {
        this.inputMultiPolygon = multiPolygon;
        return this;
    }

    @Override
    public GeometryCollectionGenerator geometryCollection(GeometryCollection geometryCollection) {
        this.inputGeometryCollection = geometryCollection;
        return this;
    }

    @Override
    public GeometryCollectionGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public GeometryCollection generate(Random random) {

        var gf = inputGeometryFactory != null ? inputGeometryFactory : defaultGeometryFactory;

        // Explicit instances: return as-is
        if (inputGeometryCollection != null) return inputGeometryCollection;
        if (inputMultiPoint != null) return inputMultiPoint;
        if (inputMultiLineString != null) return inputMultiLineString;
        if (inputMultiPolygon != null) return inputMultiPolygon;

        // Provided component list: build a heterogeneous collection
        if (inputGeometries != null) {
            return new GeometryCollection(inputGeometries.toArray(new Geometry[0]), gf);
        }

        final boolean hasEnvelope = inputEnvelope != null;

        // If a length was specified, prefer generating a heterogeneous collection deterministically
        if (inputLength != null) {
            int count = inputLength;
            List<Geometry> members = new ArrayList<>(count);

            var pointGen = new PointGenerator();
            var lineGen = new LineStringGenerator();
            var polyGen = new PolygonGenerator();

            for (int i = 0; i < count; i++) {
                int typeChoice = random != null ? random.intRange(0, 2) : backupRandom.nextInt(3);
                switch (typeChoice) {
                    case 0 -> members.add(hasEnvelope ? pointGen.within(inputEnvelope).generate(random) : pointGen.generate(random));
                    case 1 -> members.add(hasEnvelope ? lineGen.within(inputEnvelope).generate(random) : lineGen.generate(random));
                    default -> members.add(hasEnvelope ? polyGen.within(inputEnvelope).generate(random) : polyGen.generate(random));
                }
            }
            return new GeometryCollection(members.toArray(new Geometry[0]), gf);
        }

        // Choose a generation mode: 0 = heterogeneous GC, 1 = MultiPoint, 2 = MultiLineString, 3 = MultiPolygon
        int choice = random != null ? random.intRange(0, 3) : backupRandom.nextInt(4);

        switch (choice) {
            case 1: {
                var gen = new MultiPointGenerator();
                return hasEnvelope ? gen.within(inputEnvelope).generate(random) : gen.generate(random);
            }
            case 2: {
                var gen = new MultiLineStringGenerator();
                return hasEnvelope ? gen.within(inputEnvelope).generate(random) : gen.generate(random);
            }
            case 3: {
                var gen = new MultiPolygonGenerator();
                return hasEnvelope ? gen.within(inputEnvelope).generate(random) : gen.generate(random);
            }
            default: {
                // Heterogeneous GeometryCollection
                int count = inputLength != null ? inputLength : (random != null ? random.intRange(2, 6) : 2 + backupRandom.nextInt(5));
                List<Geometry> members = new ArrayList<>(count);

                var pointGen = new PointGenerator();
                var lineGen = new LineStringGenerator();
                var polyGen = new PolygonGenerator();

                for (int i = 0; i < count; i++) {
                    int typeChoice = random != null ? random.intRange(0, 2) : backupRandom.nextInt(3);
                    switch (typeChoice) {
                        case 0: {
                            members.add(hasEnvelope ? pointGen.within(inputEnvelope).generate(random) : pointGen.generate(random));
                            break;
                        }
                        case 1: {
                            members.add(hasEnvelope ? lineGen.within(inputEnvelope).generate(random) : lineGen.generate(random));
                            break;
                        }
                        default: {
                            members.add(hasEnvelope ? polyGen.within(inputEnvelope).generate(random) : polyGen.generate(random));
                        }
                    }
                }
                return new GeometryCollection(members.toArray(new Geometry[0]), gf);
            }
        }
    }
}
