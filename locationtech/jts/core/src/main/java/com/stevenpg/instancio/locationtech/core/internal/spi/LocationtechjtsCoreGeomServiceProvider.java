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

package com.stevenpg.instancio.locationtech.core.internal.spi;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.*;
import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.CoordinateArraySequenceGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.PackedCoordinateSequenceGenerator;
import org.instancio.Node;
import org.instancio.generator.Generator;
import org.instancio.generators.Generators;
import org.instancio.spi.InstancioServiceProvider;
import org.instancio.spi.ServiceProviderContext;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.locationtech.jts.geom.impl.PackedCoordinateSequence;

import java.util.HashMap;
import java.util.Map;

/**
 * SPI Provider that enables Instancio to generate JTS Geometries.
 */
public class LocationtechjtsCoreGeomServiceProvider implements InstancioServiceProvider {

    /**
     * Default constructor.
     */
    public LocationtechjtsCoreGeomServiceProvider() {
        // No custom instantiations needed
    }

    @Override
    public void init(final ServiceProviderContext providerContext) {
        // No initialization needed
    }

    @Override
    public GeneratorProvider getGeneratorProvider() {
        final Map<Class<?>, Generator<?>> generators = new HashMap<>();

        // Base Geoms
        generators.put(Coordinate.class, new CoordinateGenerator());
        generators.put(CoordinateSequence.class, new CoordinateSequenceGenerator());
        generators.put(CoordinateList.class, new CoordinateListGenerator());

        generators.put(CoordinateXY.class, new CoordinateXYGenerator());
        generators.put(CoordinateXYM.class, new CoordinateXYMGenerator());
        generators.put(CoordinateXYZM.class, new CoordinateXYZMGenerator());
        generators.put(Envelope.class, new EnvelopeGenerator());

        generators.put(Geometry.class, new GeometryGenerator());
        generators.put(GeometryCollection.class, new GeometryCollectionGenerator());

        generators.put(LineSegment.class, new LineSegmentGenerator());
        generators.put(LineString.class, new LineStringGenerator());
        generators.put(LinearRing.class, new LinearRingGenerator());
        generators.put(MultiLineString.class, new MultiLineStringGenerator());
        generators.put(MultiPoint.class, new MultiPointGenerator());
        generators.put(MultiPolygon.class, new MultiPolygonGenerator());
        generators.put(OctagonalEnvelope.class, new OctagonalEnvelopeGenerator());
        generators.put(Point.class, new PointGenerator());
        generators.put(Polygon.class, new PolygonGenerator());
        generators.put(Triangle.class, new TriangleGenerator());

        // Impls
        generators.put(CoordinateArraySequence.class, new CoordinateArraySequenceGenerator());
        generators.put(PackedCoordinateSequence.class, new PackedCoordinateSequenceGenerator());

        return (Node node, Generators gen) -> generators.get(node.getTargetClass());
    }
}
