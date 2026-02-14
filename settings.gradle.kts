pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "instancio-gis"

// Example Project
include(":example")

// LocationTech Modules
include(":locationtech-core")
project(":locationtech-core").projectDir = file("locationtech/jts/core")

// Future Implementations
// PostGIS Java Modules
include(":postgis-java-jdbc")
project(":postgis-java-jdbc").projectDir = file("postgis-java/postgis-jdbc")
include(":postgis-java-jdbc-geometry")
project(":postgis-java-jdbc-geometry").projectDir = file("postgis-java/postgis-jdbc-geometry")
include(":postgis-java-jdbc-jts")
project(":postgis-java-jdbc-jts").projectDir = file("postgis-java/postgis-jdbc-jts")

// Spatial4j
include(":locationtech-spatial4j")
project(":locationtech-spatial4j").projectDir = file("locationtech/spatial4j")

// Geolatte
include(":geolatte-geom")
project(":geolatte-geom").projectDir = file("geolatte/geom")

// ESRI
include(":esri-geometry-api")
project(":esri-geometry-api").projectDir = file("esri/geometry-api")

// H3
include(":uber-h3")
project(":uber-h3").projectDir = file("uber/h3")

// Proj4J
include(":locationtech-proj4j")
project(":locationtech-proj4j").projectDir = file("locationtech/proj4j")