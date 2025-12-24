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