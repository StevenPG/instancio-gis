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
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.LineSegmentGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.LineSegmentSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.LineSegment;
import org.locationtech.jts.geom.Point;

import java.util.Objects;

/**
 * Generator for creating a LineSegment.
 *
 * @since 1.0.0
 */
public class LineSegmentGenerator implements LineSegmentSpec, LineSegmentGeneratorSpec, EnvelopableGenerator<LineSegment> {

    private static final PointGenerator pointGenerator = new PointGenerator();

    private Envelope inputEnvelope;
    private Point p0;
    private Point p1;

    /**
     * Default constructor.
     */
    public LineSegmentGenerator() {
        // No custom instantiations needed
    }

    @Override
    public LineSegmentGenerator points(final Point p0, final Point p1) {
        this.p0 = p0;
        this.p1 = p1;
        return this;
    }

    @Override
    public LineSegmentGenerator within(final Envelope validGenerationAreaEnvelope) {
        this.inputEnvelope = validGenerationAreaEnvelope;
        return this;
    }

    @Override
    public LineSegment generate(final Random random) {
        if (Objects.nonNull(p0) && Objects.nonNull(p1)) {
            return new LineSegment(p0.getCoordinate(), p1.getCoordinate());
        }

        if (inputEnvelope != null) {
            final var start = pointGenerator.within(inputEnvelope).generate(random);
            final var end = pointGenerator.within(inputEnvelope).generate(random);
            return new LineSegment(start.getCoordinate(), end.getCoordinate());
        }

        final var start = pointGenerator.generate(random);
        final var end = pointGenerator.generate(random);
        return new LineSegment(start.getCoordinate(), end.getCoordinate());
    }
}
