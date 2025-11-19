package com.stevenpg.app;

import com.stevenpg.instancio.locationtech.core.GenLocationtechJtsCore;
import org.instancio.Instancio;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class CoordinateGeneratorTest {

    @Test
    void coordinateGenerator() {
        assertInstanceOf(Coordinate.class, GenLocationtechJtsCore.coordinate().generate(null));
    }

    @Test
    void coordinateGeneratorLatLng() {
        var coordinate = GenLocationtechJtsCore.coordinate()
                .latitude(30)
                .longitude(-60)
                .generate(null);
        assertInstanceOf(Coordinate.class, coordinate);
        assertEquals(30, coordinate.y);
        assertEquals(-60, coordinate.x);
    }

    @Test
    void coordinateGeneratorWithRandom() {
        assertInstanceOf(Coordinate.class, GenLocationtechJtsCore.coordinate().generate(new DefaultRandom()));
    }

    @Test
    void coordinateGeneratorSpi() {
        assertInstanceOf(Coordinate.class, Instancio.create(Coordinate.class));
    }

}