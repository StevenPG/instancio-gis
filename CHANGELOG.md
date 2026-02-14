# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## [0.0.4] - 2025-XX-XX

### Added
- **Spatial4j module** (`locationtech-spatial4j`) - Generators for Point, Rectangle, Circle, ShapeCollection
- **Geolatte-geom module** (`geolatte-geom`) - Generators for all 8 OGC geometry types (Point, LineString, LinearRing, Polygon, MultiPoint, MultiLineString, MultiPolygon, GeometryCollection)
- **ESRI Geometry API module** (`esri-geometry-api`) - Generators for Point, MultiPoint, Envelope, Polyline, Polygon
- **Uber H3 module** (`uber-h3`) - Generators for LatLng coordinates and H3 cell indices
- **Proj4J module** (`locationtech-proj4j`) - Generators for ProjCoordinate and CoordinateReferenceSystem
- Integration example tests demonstrating real-world usage patterns
- Improved test coverage across all existing modules
- Coverage report generation script (`generate-coverage.sh`)

### Changed
- Updated all modules to version 0.0.4

## [0.0.3] - 2025

### Changed
- Replaced `java.util.Random` with Instancio's `org.instancio.Random` across all generators

## [0.0.2] - 2025

### Changed
- Deployment configuration updates for Maven Central publishing
- JReleaser configuration finalized

## [0.0.1] - 2025

### Added
- **LocationTech JTS module** (`locationtech-core`) - Generators for Point, LineString, LinearRing, Polygon, MultiPoint, MultiLineString, MultiPolygon, GeometryCollection, Envelope, OctagonalEnvelope, Coordinate, CoordinateXY, CoordinateXYM, CoordinateXYZM, CoordinateList, CoordinateSequence, LineSegment, Triangle, Geometry
- **PostGIS JDBC module** (`postgis-jdbc`) - Generators for PGpoint, PGbox2d, PGbox3d, PGcircle, PGline, PGlseg, PGpath, PGpolygon, PGbox
- **PostGIS JDBC Geometry module** (`postgis-jdbc-geometry`) - Generators for PostGIS Geometry types (Point, LineString, LinearRing, Polygon, MultiPoint, MultiLineString, MultiPolygon, GeometryCollection, PGgeometry)
- **PostGIS JDBC JTS module** (`postgis-jdbc-jts`) - Generator for JtsGeometry
- SPI auto-registration support via `META-INF/services`
- Example project with usage demonstrations
