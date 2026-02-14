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

package com.stevenpg.app.integration;

import com.stevenpg.instancio.locationtech.core.GenLocationtechJtsCore;
import org.instancio.Instancio;
import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import java.time.Instant;
import java.util.List;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Demonstrates how instancio-gis integrates with real domain model POJOs.
 *
 * <p>The key value of instancio-gis is that GIS types (Point, Polygon, LineString, etc.)
 * are automatically populated when generating domain objects via Instancio's SPI mechanism.
 * No manual configuration is required for basic generation. For constrained generation
 * (e.g., points within a specific geographic area), the generator API can be combined
 * with Instancio's {@code supply()} to override specific fields.</p>
 */
class DomainModelExampleTest {

    // -- Example domain models as inner classes --

    static class Restaurant {
        String name;
        Point location;
        Polygon deliveryZone;
    }

    static class GpsTrack {
        String deviceId;
        LineString path;
        Instant recordedAt;
    }

    static class MapLayer {
        String layerName;
        GeometryCollection features;
    }

    @Test
    void autoGenerateRestaurantWithGisFields() {
        // SPI auto-registration means Instancio knows how to create JTS types.
        // All fields -- including GIS geometry fields -- are populated automatically.
        Restaurant restaurant = Instancio.create(Restaurant.class);

        assertNotNull(restaurant.name);
        assertNotNull(restaurant.location);
        assertNotNull(restaurant.deliveryZone);
    }

    @Test
    void generateRestaurantInSpecificArea() {
        // Constrain the location to San Francisco bounds using the generator API
        Envelope sfBounds = new Envelope(-122.52, -122.35, 37.70, 37.81);

        Restaurant restaurant = Instancio.of(Restaurant.class)
                .supply(field(Restaurant.class, "location"), () ->
                        GenLocationtechJtsCore.point()
                                .within(sfBounds)
                                .generate(new DefaultRandom()))
                .create();

        assertNotNull(restaurant.name);
        // The location is constrained to the San Francisco area
        assertTrue(sfBounds.contains(restaurant.location.getCoordinate()));
    }

    @Test
    void generateMultipleRestaurantsInArea() {
        // Generate a list of restaurants, all with locations in Manhattan
        Envelope manhattanBounds = new Envelope(-74.02, -73.93, 40.70, 40.80);

        List<Restaurant> restaurants = Instancio.ofList(Restaurant.class)
                .size(5)
                .supply(field(Restaurant.class, "location"), () ->
                        GenLocationtechJtsCore.point()
                                .within(manhattanBounds)
                                .generate(new DefaultRandom()))
                .create();

        assertEquals(5, restaurants.size());
        restaurants.forEach(r -> {
            assertNotNull(r.name);
            assertTrue(manhattanBounds.contains(r.location.getCoordinate()));
        });
    }

    @Test
    void generateGpsTrackWithSpecificLength() {
        // Override the path field to produce a 10-point GPS track within SF bounds
        GpsTrack track = Instancio.of(GpsTrack.class)
                .supply(field(GpsTrack.class, "path"), () ->
                        GenLocationtechJtsCore.lineString()
                                .length(10)
                                .within(new Envelope(-122.5, -122.3, 37.7, 37.8))
                                .generate(new DefaultRandom()))
                .create();

        assertNotNull(track.deviceId);
        assertNotNull(track.recordedAt);
        assertEquals(10, track.path.getNumPoints());
    }

    @Test
    void autoGenerateMapLayer() {
        // GeometryCollection fields are also populated automatically via SPI
        MapLayer layer = Instancio.create(MapLayer.class);

        assertNotNull(layer.layerName);
        assertNotNull(layer.features);
    }
}
