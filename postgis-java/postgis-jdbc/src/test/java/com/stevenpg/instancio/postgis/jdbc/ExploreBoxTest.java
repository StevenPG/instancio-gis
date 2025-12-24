package com.stevenpg.instancio.postgis.jdbc;

import net.postgis.jdbc.PGbox2d;
import net.postgis.jdbc.PGbox3d;
import net.postgis.jdbc.geometry.Point;
import org.junit.jupiter.api.Test;

public class ExploreBoxTest {
    @Test
    void explore() {
        PGbox2d box2d = new PGbox2d();
        // Check fields/methods via compilation or reflection if needed, 
        // but here I'll just try common ones
        System.out.println(box2d.getLLB());
        System.out.println(box2d.getURT());
        
        PGbox3d box3d = new PGbox3d();
        System.out.println(box3d.getLLB());
        System.out.println(box3d.getURT());
    }
}
