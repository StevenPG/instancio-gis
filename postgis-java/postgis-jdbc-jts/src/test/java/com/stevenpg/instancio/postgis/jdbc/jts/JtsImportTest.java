package com.stevenpg.instancio.postgis.jdbc.jts;

import net.postgis.jdbc.jts.JTSGeometry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JtsImportTest {
    @Test
    void testImport() {
        assertNotNull(JTSGeometry.class);
    }
}
