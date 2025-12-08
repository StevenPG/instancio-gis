package com.stevenpg.instancio.locationtech.core.internal.generator.geom.utility;

import com.stevenpg.instancio.locationtech.core.internal.generator.geom.MultiPointGenerator;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Point;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SamplePointsTest {

    @Test
    void allSamplePoints_shouldBeValidGeographicCoordinates() {
        Point[] points = {
            SamplePoints.MACHU_PICCHU,
            SamplePoints.MOUNT_EVEREST,
            SamplePoints.ANGKOR_WAT,
            SamplePoints.EIFEL_TOWER,
            SamplePoints.MOUNT_KILLIMANJARO,
            SamplePoints.CENTRAL_PARK_NYC,
            SamplePoints.SPACE_NEEDLE_SEATTLE,
            SamplePoints.SYDNEY_OPERA_HOUSE
        };

        for (Point point : points) {
            assertNotNull(point);
            assertNotNull(point.getCoordinate());

            // Longitude should be between -180 and 180
            assertTrue(point.getCoordinate().x >= -180.0 && point.getCoordinate().x <= 180.0,
                "Longitude " + point.getCoordinate().x + " is not in valid range [-180, 180]");

            // Latitude should be between -90 and 90
            assertTrue(point.getCoordinate().y >= -90.0 && point.getCoordinate().y <= 90.0,
                "Latitude " + point.getCoordinate().y + " is not in valid range [-90, 90]");
        }
    }

    @Test
    void pointGeoJsonValidation() {
        var multipoint = new MultiPointGenerator().points(
                List.of(
                        SamplePoints.MACHU_PICCHU,
                        SamplePoints.MOUNT_EVEREST,
                        SamplePoints.ANGKOR_WAT,
                        SamplePoints.EIFEL_TOWER,
                        SamplePoints.MOUNT_KILLIMANJARO,
                        SamplePoints.CENTRAL_PARK_NYC,
                        SamplePoints.SPACE_NEEDLE_SEATTLE,
                        SamplePoints.SYDNEY_OPERA_HOUSE
                )
        ).generate(new DefaultRandom());

        assertNotNull(multipoint);
        // https://atlas.co/tools/wkt-to-geojson/
        System.out.println(multipoint.toText());
    }
}