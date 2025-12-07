package com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.MultiPointGenerator;
import org.instancio.generator.GeneratorSpec;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;

import java.util.List;

/**
 * Spec for generating a MultiPoint.
 */
public interface MultiPointGeneratorSpec extends GeneratorSpec<MultiPoint> {

    /**
     * Provide a list of points to load into a multi-point.
     * @param points the points to load
     * @return spec builder
     */
    MultiPointGenerator points(List<Point> points);

    /**
     * Provide an optional GeometryFactory to use to generate the LineString.
     * Cannot be provided alongside the CoordinateSequence.
     * @param geometryFactory the geometry factory to use
     * @return spec builder
     */
    MultiPointGenerator geometryFactory(GeometryFactory geometryFactory);

    /**
     * Set the length of the generated LineString.
     * @param length the number of coordinates must be >= 2
     * @return spec builder
     */
    MultiPointGenerator length(int length);
}
