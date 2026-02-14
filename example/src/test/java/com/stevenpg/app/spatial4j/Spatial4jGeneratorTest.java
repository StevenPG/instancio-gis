/*
 * Copyright 2025 Steven Gantz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stevenpg.app.spatial4j;

import com.stevenpg.instancio.spatial4j.GenSpatial4j;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.shape.Circle;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.Shape;
import org.locationtech.spatial4j.shape.ShapeCollection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Spatial4jGeneratorTest {

    private final Random random = new DefaultRandom();

    @Test
    void shouldGeneratePointWithInstancio() {
        Point p = Instancio.create(Point.class);
        assertNotNull(p);
        assertTrue(p.getX() >= -180 && p.getX() <= 180);
        assertTrue(p.getY() >= -90 && p.getY() <= 90);
    }

    @Test
    void shouldGenerateRectangleWithInstancio() {
        Rectangle r = Instancio.create(Rectangle.class);
        assertNotNull(r);
        assertTrue(r.getMinX() >= -180 && r.getMinX() <= 180);
        assertTrue(r.getMaxX() >= -180 && r.getMaxX() <= 180);
        assertTrue(r.getMinY() >= -90 && r.getMinY() <= 90);
        assertTrue(r.getMaxY() >= -90 && r.getMaxY() <= 90);
        assertTrue(r.getMinX() <= r.getMaxX());
        assertTrue(r.getMinY() <= r.getMaxY());
    }

    @Test
    void shouldGenerateCircleWithInstancio() {
        Circle c = Instancio.create(Circle.class);
        assertNotNull(c);
        assertNotNull(c.getCenter());
        assertTrue(c.getCenter().getX() >= -180 && c.getCenter().getX() <= 180);
        assertTrue(c.getCenter().getY() >= -90 && c.getCenter().getY() <= 90);
        assertTrue(c.getRadius() > 0);
    }

    @Test
    void shouldGenerateShapeCollectionWithInstancio() {
        // ShapeCollection<S> requires a type parameter; use withTypeParameters to satisfy Instancio's validator
        ShapeCollection<?> sc = Instancio.of(ShapeCollection.class)
                .withTypeParameters(Shape.class)
                .create();
        assertNotNull(sc);
    }

    @Test
    void shouldGeneratePointWithGenerator() {
        Point p = GenSpatial4j.point()
                .xRange(-10, 10)
                .yRange(-5, 5)
                .generate(random);
        assertNotNull(p);
    }

    @Test
    void shouldGenerateRectangleWithGenerator() {
        Rectangle r = GenSpatial4j.rectangle()
                .xRange(-100, 100)
                .yRange(-50, 50)
                .generate(random);
        assertNotNull(r);
    }
}
