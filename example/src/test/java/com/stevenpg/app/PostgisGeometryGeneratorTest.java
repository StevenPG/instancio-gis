/*
 * Copyright 2025 Steven Gantz.
 */
package com.stevenpg.app;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostgisGeometryGeneratorTest {

    @Test
    void shouldGenerateGeometryWithInstancio() {
        Geometry g = Instancio.create(Geometry.class);
        assertNotNull(g);
    }
}
