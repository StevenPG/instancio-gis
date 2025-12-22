/*
 * Copyright 2025 Steven Gantz.
 */
package com.stevenpg.app;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.postgresql.geometric.PGpoint;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostgresGeometricGeneratorTest {

    @Test
    void shouldGeneratePGpointWithInstancio() {
        PGpoint p = Instancio.create(PGpoint.class);
        assertNotNull(p);
    }
}
