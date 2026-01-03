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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.GeometryGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.GeometrySpec;
import org.instancio.Random;
import org.instancio.generator.Generator;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for creating a Geometry. If a specific subtype is provided via the spec,
 * that instance will be returned. Otherwise, a random Geometry subtype will be generated.
 * If an envelope is provided, random generation will occur within the envelope.
 *
 * @since 1.0.0
 */
public class GeometryGenerator implements GeometrySpec, GeometryGeneratorSpec, EnvelopableGenerator<Geometry>, Generator<Geometry> {

    private Envelope inputEnvelope;
    private Point inputPoint;
    private LineString inputLineString;
    private LinearRing inputLinearRing;
    private Polygon inputPolygon;
    private GeometryCollection inputGeometryCollection;
    // Note: Triangle is not a Geometry subtype; excluded from this generator

    /** Default constructor. */
    public GeometryGenerator() {
        // No custom instantiations needed
    }

    @Override
    public GeometryGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public GeometryGenerator point(Point point) {
        this.inputPoint = point;
        return this;
    }

    @Override
    public GeometryGenerator lineString(LineString lineString) {
        this.inputLineString = lineString;
        return this;
    }

    @Override
    public GeometryGenerator linearRing(LinearRing linearRing) {
        this.inputLinearRing = linearRing;
        return this;
    }

    @Override
    public GeometryGenerator polygon(Polygon polygon) {
        this.inputPolygon = polygon;
        return this;
    }

    @Override
    public GeometryGenerator geometryCollection(GeometryCollection geometryCollection) {
        this.inputGeometryCollection = geometryCollection;
        return this;
    }

    @Override
    public Geometry generate(Random random) {
        // If user provided a specific subtype, return it as-is (overrides envelope)
        if (inputPoint != null) return inputPoint;
        if (inputLineString != null) return inputLineString;
        if (inputLinearRing != null) return inputLinearRing;
        if (inputPolygon != null) return inputPolygon;
        if (inputGeometryCollection != null) return inputGeometryCollection;

        // Otherwise, generate a random Geometry subtype using existing generators
        final boolean hasEnvelope = inputEnvelope != null;

        final List<Generator<? extends Geometry>> generators = new ArrayList<>();

        // Create all generators eagerly so code paths are always executed; only one will be invoked
        var pointGen = new PointGenerator();
        generators.add(hasEnvelope ? (Generator<Point>) pointGen.within(inputEnvelope) : pointGen);

        var lineStringGen = new LineStringGenerator();
        generators.add(hasEnvelope ? (Generator<LineString>) lineStringGen.within(inputEnvelope) : lineStringGen);

        var linearRingGen = new LinearRingGenerator();
        generators.add(hasEnvelope ? (Generator<LinearRing>) linearRingGen.within(inputEnvelope) : linearRingGen);

        var polygonGen = new PolygonGenerator();
        generators.add(hasEnvelope ? (Generator<Polygon>) polygonGen.within(inputEnvelope) : polygonGen);

        var geometryCollectionGen = new GeometryCollectionGenerator();
        generators.add(hasEnvelope ? (Generator<GeometryCollection>) geometryCollectionGen.within(inputEnvelope) : geometryCollectionGen);

        int index = random.intRange(0, generators.size() - 1);
        @SuppressWarnings("unchecked")
        var selected = (Generator<Geometry>) generators.get(index);
        return selected.generate(random);
    }
}
