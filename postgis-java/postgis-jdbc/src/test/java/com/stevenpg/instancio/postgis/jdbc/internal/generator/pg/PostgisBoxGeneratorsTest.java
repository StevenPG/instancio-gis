package com.stevenpg.instancio.postgis.jdbc.internal.generator.pg;

import net.postgis.jdbc.PGbox2d;
import net.postgis.jdbc.PGbox3d;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostgisBoxGeneratorsTest {

    @Test
    void shouldGeneratePGbox2d() {
        PGbox2d box = Instancio.create(PGbox2d.class);
        assertNotNull(box);
        assertNotNull(box.getLLB());
        assertNotNull(box.getURT());
        assertTrue(box.getLLB().x <= box.getURT().x);
        assertTrue(box.getLLB().y <= box.getURT().y);
    }

    @Test
    void shouldGeneratePGbox3d() {
        PGbox3d box = Instancio.create(PGbox3d.class);
        assertNotNull(box);
        assertNotNull(box.getLLB());
        assertNotNull(box.getURT());
        assertTrue(box.getLLB().x <= box.getURT().x);
        assertTrue(box.getLLB().y <= box.getURT().y);
        assertTrue(box.getLLB().z <= box.getURT().z);
    }
}
