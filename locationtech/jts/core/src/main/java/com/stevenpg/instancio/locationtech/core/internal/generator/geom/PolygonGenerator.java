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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.PolygonGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.PolygonSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

/**
 * Generator for creating a Polygon.
 *
 * @since 1.0.0
 */
public class PolygonGenerator implements PolygonSpec, PolygonGeneratorSpec, EnvelopableGenerator<Polygon> {

    private static final GeometryFactory defaultGeometryFactory = new GeometryFactory();

    private GeometryFactory inputGeometryFactory;
    private LinearRing inputExteriorRing;
    private LinearRing[] inputHoles;
    private Integer inputVertices;
    private Integer inputHolesCount;
    private Envelope inputEnvelope;

    /**
     * Default constructor.
     */
    public PolygonGenerator() {
        // No custom instantiations needed
    }

    @Override
    public PolygonGenerator exteriorRing(LinearRing exteriorRing) {
        this.inputExteriorRing = exteriorRing;
        return this;
    }

    @Override
    public PolygonGenerator rings(LinearRing exteriorRing, LinearRing... holes) {
        this.inputExteriorRing = exteriorRing;
        this.inputHoles = holes;
        return this;
    }

    @Override
    public PolygonGenerator vertices(int vertices) {
        this.inputVertices = vertices;
        return this;
    }

    @Override
    public PolygonGenerator holes(int holes) {
        this.inputHolesCount = holes;
        return this;
    }

    @Override
    public PolygonGenerator geometryFactory(GeometryFactory geometryFactory) {
        this.inputGeometryFactory = geometryFactory;
        return this;
    }

    @Override
    public PolygonGenerator within(Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public Polygon generate(Random random) {
        var geometryFactory = inputGeometryFactory != null ? inputGeometryFactory : defaultGeometryFactory;

        if (inputExteriorRing != null) {
            return getWellDefinedPolygon(geometryFactory);
        } else {
            // Generate polygon from scratch
            var ringGenerator = new LinearRingGenerator();

            // Determine number of vertices for exterior ring
            var vertices = random.intRange(3, 8);
            if (inputVertices != null) {
                vertices = Math.max(3, inputVertices);
            }

            // Generate exterior ring
            LinearRing exteriorRing = getLinearRing(random, ringGenerator, vertices);

            // Generate holes if specified
            if (inputHolesCount != null && inputHolesCount > 0) {
                return generatePolygonWithSpecifiedHoles(random, exteriorRing, geometryFactory);
            } else {
                return geometryFactory.createPolygon(exteriorRing);
            }
        }
    }

    private Polygon generatePolygonWithSpecifiedHoles(Random random, LinearRing exteriorRing, GeometryFactory geometryFactory) {
        LinearRing[] holes = new LinearRing[inputHolesCount];
        var exteriorEnvelope = exteriorRing.getEnvelopeInternal();

        // Create smaller envelopes for holes within the exterior ring
        for (int i = 0; i < inputHolesCount; i++) {
            var holeEnvelope = createHoleEnvelope(random, exteriorEnvelope, i, inputHolesCount);
            var holeVertices = random.intRange(3, 6);
            holes[i] = new LinearRingGenerator().length(holeVertices).within(holeEnvelope).generate(random);
        }
        return geometryFactory.createPolygon(exteriorRing, holes);
    }

    private LinearRing getLinearRing(Random random, LinearRingGenerator ringGenerator, int vertices) {
        LinearRing exteriorRing;
        if (inputEnvelope != null) {
            exteriorRing = ringGenerator.length(vertices).within(inputEnvelope).generate(random);
        } else {
            exteriorRing = ringGenerator.length(vertices).generate(random);
        }
        return exteriorRing;
    }

    private Polygon getWellDefinedPolygon(GeometryFactory geometryFactory) {
        // Use provided exterior ring
        if (inputHoles != null) {
            return geometryFactory.createPolygon(inputExteriorRing, inputHoles);
        } else {
            return geometryFactory.createPolygon(inputExteriorRing);
        }
    }

    /**
     * Creates a smaller envelope within the exterior envelope for generating holes.
     */
    private Envelope createHoleEnvelope(Random random, Envelope exteriorEnvelope, int holeIndex, int totalHoles) {
        // Calculate a smaller envelope within the exterior for the hole
        var width = exteriorEnvelope.getWidth();
        var height = exteriorEnvelope.getHeight();

        // Make holes 20-40% of the exterior size
        // Use holeIndex and totalHoles to vary hole sizes for multiple holes
        var baseScale = 0.2;
        var variationScale = 0.2;
        if (totalHoles > 1) {
            // Vary hole sizes based on index
            variationScale *= (1.0 + (holeIndex * 0.3 / totalHoles));
        }
        var scaleFactor = baseScale + (variationScale * random.doubleRange(0, 1));
        var holeWidth = width * scaleFactor;
        var holeHeight = height * scaleFactor;

        // Position holes to avoid overlapping too much
        // Use holeIndex to distribute holes across the envelope
        var xOffset = (holeIndex % 2) * 0.3;
        var yOffset = (((double) holeIndex / 2) % 2) * 0.3;
        var offsetX = (width - holeWidth) * (0.1 + xOffset + 0.3 * random.doubleRange(0, 1));
        var offsetY = (height - holeHeight) * (0.1 + yOffset + 0.3 * random.doubleRange(0, 1));

        var minX = exteriorEnvelope.getMinX() + offsetX;
        var minY = exteriorEnvelope.getMinY() + offsetY;

        return new Envelope(minX, minX + holeWidth, minY, minY + holeHeight);
    }
}
