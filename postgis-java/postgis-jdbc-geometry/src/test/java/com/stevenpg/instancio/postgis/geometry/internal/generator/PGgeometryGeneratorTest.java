package com.stevenpg.instancio.postgis.geometry.internal.generator;

import net.postgis.jdbc.PGgeometry;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PGgeometryGeneratorTest {

    @Test
    void shouldGeneratePGgeometry() {
        PGgeometry pgGeom = Instancio.create(PGgeometry.class);
        assertNotNull(pgGeom);
        assertNotNull(pgGeom.getGeometry());
    }
}
