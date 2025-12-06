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

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.impl.CoordinateArraySequenceGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.EnvelopableGenerator;

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.LineStringGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.LineStringSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

/**
 * Generator for creating a Point.
 *
 * @since 1.0.0
 */
public class LineStringGenerator implements LineStringSpec, LineStringGeneratorSpec, EnvelopableGenerator<LineString> {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    private Coordinate inputPointCoordinate;

    private final CoordinateArraySequenceGenerator coordinateSequenceGenerator = new CoordinateArraySequenceGenerator();

    /**
     * Configure the generator to generate a point with the specified coordinate.
     *
     * @param longitude coordinate longitude
     * @param latitude  coordinate latitude
     * @return spec builder
     */
    @Override
    public PointGenerator coordinate(final double longitude, final double latitude) {
        this.inputPointCoordinate = new Coordinate(longitude, latitude);
        return this;
    }

    @Override
    public Point generate(Random random) {
        CoordinateArraySequence singularCoordinateSequence;
        if (inputPointCoordinate != null) {
            singularCoordinateSequence = new CoordinateArraySequence(new Coordinate[]{inputPointCoordinate});
        } else {
            singularCoordinateSequence = coordinateSequenceGenerator.length(1).generate(random);
        }
        return new Point(singularCoordinateSequence, geometryFactory);
    }
}
