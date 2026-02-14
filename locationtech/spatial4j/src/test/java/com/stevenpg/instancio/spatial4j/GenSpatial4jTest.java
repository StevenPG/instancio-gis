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

import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.locationtech.spatial4j.shape.Circle;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.ShapeCollection;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenSpatial4jTest {

    private final Random random = new DefaultRandom();

    @RepeatedTest(10)
    void point() {
        var result = GenSpatial4j.point().generate(random);
        assertNotNull(result);
        assertInstanceOf(Point.class, result);
        // Validate default WGS84 bounds
        assertTrue(result.getX() >= -180 && result.getX() <= 180,
                "X (longitude) should be within [-180, 180], was: " + result.getX());
        assertTrue(result.getY() >= -90 && result.getY() <= 90,
                "Y (latitude) should be within [-90, 90], was: " + result.getY());
    }

    @RepeatedTest(10)
    void rectangle() {
        var result = GenSpatial4j.rectangle().generate(random);
        assertNotNull(result);
        assertInstanceOf(Rectangle.class, result);
        // Validate min <= max ordering
        assertTrue(result.getMinX() <= result.getMaxX(),
                "MinX should be <= MaxX, but was: " + result.getMinX() + " > " + result.getMaxX());
        assertTrue(result.getMinY() <= result.getMaxY(),
                "MinY should be <= MaxY, but was: " + result.getMinY() + " > " + result.getMaxY());
    }

    @RepeatedTest(10)
    void circle() {
        var result = GenSpatial4j.circle().generate(random);
        assertNotNull(result);
        assertInstanceOf(Circle.class, result);
        // Validate radius is positive
        assertTrue(result.getRadius() > 0,
                "Radius should be > 0, was: " + result.getRadius());
        // Validate center point is within default WGS84 bounds
        assertTrue(result.getCenter().getX() >= -180 && result.getCenter().getX() <= 180,
                "Center X (longitude) should be within [-180, 180], was: " + result.getCenter().getX());
        assertTrue(result.getCenter().getY() >= -90 && result.getCenter().getY() <= 90,
                "Center Y (latitude) should be within [-90, 90], was: " + result.getCenter().getY());
    }

    @RepeatedTest(10)
    void shapeCollection() {
        var result = GenSpatial4j.shapeCollection().generate(random);
        assertNotNull(result);
        assertInstanceOf(ShapeCollection.class, result);
        // Validate the collection contains at least 2 shapes
        assertTrue(result.getShapes().size() >= 2,
                "Collection should have at least 2 shapes, was: " + result.getShapes().size());
    }
}
