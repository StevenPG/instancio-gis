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

package com.stevenpg.instancio.spatial4j;

import com.stevenpg.instancio.spatial4j.internal.generator.ShapeCollectionGenerator;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.shape.Shape;
import org.locationtech.spatial4j.shape.ShapeCollection;

import org.locationtech.spatial4j.shape.Point;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShapeCollectionGeneratorTest {

    private final Random random = new DefaultRandom();

    @RepeatedTest(5)
    void create() {
        // ShapeCollection<S> requires a type parameter; use Shape as the upper bound
        var collection = Instancio.of(ShapeCollection.class).withTypeParameters(Shape.class).create();
        assertNotNull(collection);
        // Validate the collection contains at least 2 shapes
        assertTrue(collection.getShapes().size() >= 2,
                "Collection should have at least 2 shapes, was: " + collection.getShapes().size());
        // Each shape in the default collection should be a Point with valid WGS84 coordinates
        for (var shape : collection) {
            assertInstanceOf(Point.class, shape);
            var point = (Point) shape;
            assertTrue(point.getX() >= -180 && point.getX() <= 180,
                    "Point X (longitude) should be within [-180, 180], was: " + point.getX());
            assertTrue(point.getY() >= -90 && point.getY() <= 90,
                    "Point Y (latitude) should be within [-90, 90], was: " + point.getY());
        }
    }

    @Test
    void shouldGenerateWithConfiguredLength() {
        var gen = new ShapeCollectionGenerator().length(3, 7);
        var collection = gen.generate(random);
        assertNotNull(collection);
        assertTrue(collection.getShapes().size() >= 3);
        assertTrue(collection.getShapes().size() <= 7);
    }

    @Test
    void generate() {
        var result = new ShapeCollectionGenerator().generate(random);
        assertNotNull(result);
        assertFalse(result.getShapes().isEmpty());
        // Validate the collection contains at least 2 shapes
        assertTrue(result.getShapes().size() >= 2,
                "Collection should have at least 2 shapes, was: " + result.getShapes().size());
        // Each shape in the default collection should be a Point with valid WGS84 coordinates
        for (var shape : result) {
            assertInstanceOf(Point.class, shape);
            var point = (Point) shape;
            assertTrue(point.getX() >= -180 && point.getX() <= 180,
                    "Point X (longitude) should be within [-180, 180], was: " + point.getX());
            assertTrue(point.getY() >= -90 && point.getY() <= 90,
                    "Point Y (latitude) should be within [-90, 90], was: " + point.getY());
        }
    }
}
