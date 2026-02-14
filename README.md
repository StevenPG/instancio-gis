# instancio-gis
An instancio extension that provides auto-registered custom generators for popular GIS libraries.

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=StevenPG_instancio-gis&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=StevenPG_instancio-gis)
[![javadoc](https://javadoc.io/badge2/com.stevenpg.instancio/locationtech-core/javadoc.svg)](https://javadoc.io/doc/com.stevenpg.instancio/locationtech-core)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=StevenPG_instancio-gis&metric=coverage)](https://sonarcloud.io/summary/new_code?id=StevenPG_instancio-gis)

## What is this?

**instancio-gis** is an extension for [Instancio](https://github.com/instancio/instancio) that lets you generate
random GIS objects in your tests with zero configuration. Add the dependency, and `Instancio.create(Point.class)` just works.

This is achieved using the [Instancio Service Provider Interface](https://www.instancio.org/user-guide/#instancio-service-provider-interface) -
generators are auto-registered on the classpath, so there's nothing to configure.

## Quick Start

**1. Add the dependency** (example using LocationTech JTS):

```kotlin
// build.gradle.kts
testImplementation("com.stevenpg.instancio:locationtech-core:0.0.4")
```

**2. Generate GIS objects in your tests:**

```java
import org.instancio.Instancio;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

// Auto-generate any supported type - no configuration needed
Point point = Instancio.create(Point.class);
Polygon polygon = Instancio.create(Polygon.class);
```

That's it. The SPI auto-registration detects the generators on your classpath and handles everything.

## Usage Guide

### Auto-Registration (SPI)

The simplest way to use instancio-gis is via Instancio's SPI auto-registration. Once you add
a module to your test dependencies, `Instancio.create()` automatically knows how to generate
all supported types from that library:

```java
// JTS types
Point point = Instancio.create(Point.class);
LineString line = Instancio.create(LineString.class);
Polygon polygon = Instancio.create(Polygon.class);
Envelope envelope = Instancio.create(Envelope.class);

// PostGIS types
PGpoint pgPoint = Instancio.create(PGpoint.class);
PGpolygon pgPolygon = Instancio.create(PGpolygon.class);

// Spatial4j types
org.locationtech.spatial4j.shape.Point s4jPoint = Instancio.create(
    org.locationtech.spatial4j.shape.Point.class);

// H3 types
LatLng latLng = Instancio.create(LatLng.class);

// Proj4J types
ProjCoordinate coord = Instancio.create(ProjCoordinate.class);
CoordinateReferenceSystem crs = Instancio.create(CoordinateReferenceSystem.class);
```

### Custom Configuration with Gen Facades

Each module provides a `Gen*` facade class with factory methods that return configurable generators.
Use these when you need control over the generated values:

```java
import com.stevenpg.instancio.locationtech.core.GenLocationtechJtsCore;

// Generate a point within San Francisco
Envelope sfBounds = new Envelope(-122.52, -122.35, 37.70, 37.81);
Point point = GenLocationtechJtsCore.point()
    .within(sfBounds)
    .generate(random);

// Generate a line string with 10 coordinates in an area
LineString route = GenLocationtechJtsCore.lineString()
    .length(10)
    .within(sfBounds)
    .generate(random);

// Generate a polygon with 6 vertices and 2 holes
Polygon polygon = GenLocationtechJtsCore.polygon()
    .vertices(6)
    .holes(2)
    .within(sfBounds)
    .generate(random);
```

Other module facades follow the same pattern:

```java
// PostGIS - coordinate ranges
PGpoint pgPoint = GenPostgisJdbc.pgPoint()
    .xRange(-122.5, -122.3)
    .yRange(37.7, 37.8)
    .generate(random);

// Spatial4j - circle with radius
Circle circle = GenSpatial4j.circle()
    .xRange(-5, 5)
    .yRange(-5, 5)
    .radiusRange(1.0, 5.0)
    .generate(random);

// H3 - hexagonal cell index at a specific resolution
Long h3Cell = GenH3.h3Index()
    .latRange(51.4, 51.6)   // London
    .lngRange(-0.2, 0.1)
    .resolution(7)
    .generate(random);

// Proj4J - coordinate reference system
CoordinateReferenceSystem crs = GenProj4j.crs()
    .epsgCodes("EPSG:4326", "EPSG:3857")
    .generate(random);
```

### Domain Model Integration

The real power of instancio-gis is generating entire domain objects that contain GIS fields.
Instancio populates all fields automatically, including the GIS types:

```java
// Your domain model
class Restaurant {
    String name;
    Point location;
    Polygon deliveryZone;
}

// Auto-generates name, location, AND deliveryZone
Restaurant restaurant = Instancio.create(Restaurant.class);

// Or constrain specific fields while auto-generating the rest
Envelope manhattanBounds = new Envelope(-74.02, -73.93, 40.70, 40.80);
Restaurant nyRestaurant = Instancio.of(Restaurant.class)
    .supply(Select.field(Restaurant.class, "location"), () ->
        GenLocationtechJtsCore.point()
            .within(manhattanBounds)
            .generate(new DefaultRandom()))
    .create();

// Generate a list of restaurants in a specific area
List<Restaurant> restaurants = Instancio.ofList(Restaurant.class)
    .size(10)
    .supply(Select.field(Restaurant.class, "location"), () ->
        GenLocationtechJtsCore.point()
            .within(manhattanBounds)
            .generate(new DefaultRandom()))
    .create();
```

## Supported Libraries

| Library | Module Artifact | Facade Class | Supported Types |
|---------|----------------|--------------|-----------------|
| [LocationTech JTS](https://locationtech.github.io/jts/) | `locationtech-core` | `GenLocationtechJtsCore` | Point, LineString, LinearRing, Polygon, MultiPoint, MultiLineString, MultiPolygon, GeometryCollection, Envelope, OctagonalEnvelope, Coordinate, CoordinateXY, CoordinateXYM, CoordinateXYZM, CoordinateList, CoordinateSequence, LineSegment, Triangle, Geometry |
| [PostGIS JDBC](https://github.com/postgis/postgis-java) | `postgis-jdbc` | `GenPostgisJdbc` | PGpoint, PGbox2d, PGbox3d, PGcircle, PGline, PGlseg, PGpath, PGpolygon, PGbox |
| [PostGIS Geometry](https://github.com/postgis/postgis-java) | `postgis-jdbc-geometry` | `GenPostgisGeometry` | Point, LineString, LinearRing, Polygon, MultiPoint, MultiLineString, MultiPolygon, GeometryCollection, PGgeometry |
| [PostGIS JTS](https://github.com/postgis/postgis-java) | `postgis-jdbc-jts` | `GenPostgisJdbcJts` | JtsGeometry |
| [Spatial4j](https://projects.eclipse.org/projects/locationtech.spatial4j) | `locationtech-spatial4j` | `GenSpatial4j` | Point, Rectangle, Circle, ShapeCollection |
| [Geolatte-geom](https://github.com/GeoLatte/geolatte-geom) | `geolatte-geom` | `GenGeolatte` | Point, LineString, LinearRing, Polygon, MultiPoint, MultiLineString, MultiPolygon, GeometryCollection |
| [ESRI Geometry API](https://github.com/Esri/geometry-api-java) | `esri-geometry-api` | `GenEsriGeometry` | Point, MultiPoint, Envelope, Polyline, Polygon |
| [Uber H3](https://h3geo.org/) | `uber-h3` | `GenH3` | LatLng, H3 cell index (Long) |
| [Proj4J](https://github.com/locationtech/proj4j) | `locationtech-proj4j` | `GenProj4j` | ProjCoordinate, CoordinateReferenceSystem |

## Installation

All modules are available on [Maven Central](https://central.sonatype.com/search?q=com.stevenpg.instancio).

Add the module(s) for the GIS libraries you use. You also need the underlying GIS library itself as a dependency.

### LocationTech JTS

```xml
<!-- Maven -->
<dependency>
    <groupId>com.stevenpg.instancio</groupId>
    <artifactId>locationtech-core</artifactId>
    <version>${instancio-gis.version}</version>
    <scope>test</scope>
</dependency>
```

```kotlin
// Gradle (Kotlin DSL)
testImplementation("com.stevenpg.instancio:locationtech-core:${version}")
```

### PostGIS-Java

```kotlin
// JDBC types (PGpoint, PGpolygon, etc.)
testImplementation("com.stevenpg.instancio:postgis-jdbc:${version}")

// PostGIS Geometry types (Point, LineString, etc.)
testImplementation("com.stevenpg.instancio:postgis-jdbc-geometry:${version}")

// JTS bridge
testImplementation("com.stevenpg.instancio:postgis-jdbc-jts:${version}")
```

### Spatial4j

```kotlin
testImplementation("com.stevenpg.instancio:locationtech-spatial4j:${version}")
```

### Geolatte-geom

```kotlin
testImplementation("com.stevenpg.instancio:geolatte-geom:${version}")
```

### ESRI Geometry API

```kotlin
testImplementation("com.stevenpg.instancio:esri-geometry-api:${version}")
```

### Uber H3

```kotlin
testImplementation("com.stevenpg.instancio:uber-h3:${version}")
```

### Proj4J

```kotlin
testImplementation("com.stevenpg.instancio:locationtech-proj4j:${version}")
```

## Contributing

This project aims to provide comprehensive GIS library support for Instancio, and community contributions can help make that possible.

### Getting Started

#### Prerequisites

- Java 17
- Gradle 8.14

#### Development Setup

1. Fork the repository on GitHub
2. Clone your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/instancio-gis.git
   cd instancio-gis
   ```
3. Build the project:
   ```bash
   ./gradlew build
   ```
4. Run tests to ensure everything works:
   ```bash
   ./gradlew test
   ```

### Contributing Guidelines

#### Before You Start

- Check existing issues to see if your idea is already being discussed
- For new features, please create an issue first to discuss the approach
- For bug fixes, you can submit a PR directly with a clear description

#### Adding New Library Support

When adding support for a new GIS library:

1. Create a new module under the appropriate directory structure
2. Implement custom generators following Instancio's SPI pattern
3. Add comprehensive tests with good coverage
4. Update documentation including:
   - README.md usage examples
   - Maven/Gradle dependency snippets
   - Supported library versions

#### Code Standards

- Follow existing code style and conventions
- Write meaningful commit messages
- Include tests for new functionality
- Ensure all tests pass before submitting
- Add JavaDoc comments for public APIs

#### Testing

- All new generators must have unit tests
- Aim for high test coverage
- Test edge cases and error conditions
- Use meaningful test names that describe the scenario

#### Publishing with JReleaser

Run `./gradlew clean build publish jreleaserFullRelease` to publish the latest version of the library.
Given you have the correct configuration in your `$HOME/.jreleaser` properties file.

### Questions or Need Help?

- Create an issue for questions about contributing
- Check the [Instancio documentation](https://www.instancio.org/user-guide/) for SPI implementation details
- Review existing modules for implementation patterns

Thank you for contributing to instancio-gis!

## Instancio

Here are the official links:

- [Website](https://www.instancio.org/)
- [GitHub](https://github.com/instancio/instancio)
- [Documentation](https://www.instancio.org/user-guide/)
- [JavaDoc](https://javadoc.io/doc/org.instancio/instancio-core/latest/org/instancio/Instancio.html)
