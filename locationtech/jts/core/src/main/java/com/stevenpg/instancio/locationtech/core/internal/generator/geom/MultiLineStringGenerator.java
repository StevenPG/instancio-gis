package com.stevenpg.instancio.locationtech.core.internal.generator.geom;

import com.stevenpg.instancio.locationtech.core.internal.generator.specs.EnvelopableGenerator;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.MultiLineStringGeneratorSpec;
import com.stevenpg.instancio.locationtech.core.internal.generator.specs.geom.MultiLineStringSpec;
import org.instancio.Random;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.List;

public class MultiLineStringGenerator implements MultiLineStringSpec, MultiLineStringGeneratorSpec, EnvelopableGenerator<MultiLineString> {
    private final static GeometryFactory defaultGeometryFactory = new GeometryFactory();
    private final static java.util.Random random = new java.util.Random();
    private final static PointGenerator pointGenerator = new PointGenerator();

    private GeometryFactory inputGeometryFactory;
    private Integer inputLength;
    private Envelope inputEnvelope;
    private List<Point> inputPoints;

    /**
     * Default constructor.
     */
    public MultiLineStringGenerator() {
    }

    @Override
    public MultiLineStringGenerator points(List<Point> points) {
        this.inputPoints = points;
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
        if (inputPoints != null) {
            return new MultiPoint(inputPoints.toArray(new Point[0]), geometryFactory);
        } else {

            var length = random != null
                    ? random.intRange(2, 10)
                    : MultiPointGenerator.random.nextInt(2, 10);
            if (inputLength != null) {
                length = inputLength;
            }
            if (inputEnvelope != null) {
                var pointList = new ArrayList<Point>();
                for (int i = 0; i < length; i++) {
                    pointList.add(pointGenerator.within(inputEnvelope).generate(random));
                }
                return new MultiPoint(pointList.toArray(new Point[0]), geometryFactory);
            } else {
                var pointList = new ArrayList<Point>();
                for (int i = 0; i < length; i++) {
                    pointList.add(pointGenerator.generate(random));
                }
                return new MultiPoint(pointList.toArray(new Point[0]), geometryFactory);
            }
        }
    }
}
