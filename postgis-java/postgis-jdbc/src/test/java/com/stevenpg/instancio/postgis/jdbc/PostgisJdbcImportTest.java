package com.stevenpg.instancio.postgis.jdbc;

import net.postgis.jdbc.PGbox2d;
import net.postgis.jdbc.PGbox3d;
import net.postgis.jdbc.PGgeometry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostgisJdbcImportTest {
    @Test
    void testImports() {
        assertNotNull(PGbox2d.class);
        assertNotNull(PGbox3d.class);
        assertNotNull(PGgeometry.class);
    }
}
