plugins {
    id("java-common-conventions")
    id("java-instancio-gis-library")
    application
}

dependencies {

    // Pull actual JTS library
    implementation("org.locationtech.jts:jts-core:1.20.0")

    // Pull PostGIS libraries
    implementation("net.postgis:postgis-jdbc:2025.1.1")
    implementation("net.postgis:postgis-geometry:2025.1.1")

    // Pull instancio extension
    implementation(project(":locationtech-core"))

    // Postgres JDBC geometric generators
    testImplementation(project(":postgis-java-jdbc"))
    testImplementation(project(":postgis-java-jdbc-geometry"))
    testImplementation(project(":postgis-java-jdbc-jts"))
    testImplementation("net.postgis:postgis-jdbc:2025.1.1")
    testImplementation("net.postgis:postgis-geometry:2025.1.1")
    testImplementation("net.postgis:postgis-jdbc-jts:2025.1.1")

    // Spatial4j
    implementation("org.locationtech.spatial4j:spatial4j:0.8")
    testImplementation(project(":locationtech-spatial4j"))

    // Geolatte
    implementation("org.geolatte:geolatte-geom:1.10")
    testImplementation(project(":geolatte-geom"))

    // ESRI
    implementation("com.esri.geometry:esri-geometry-api:2.2.4")
    testImplementation(project(":esri-geometry-api"))

    // H3
    implementation("com.uber:h3:4.4.0")
    testImplementation(project(":uber-h3"))

    // Proj4J
    implementation("org.locationtech.proj4j:proj4j:1.4.1")
    implementation("org.locationtech.proj4j:proj4j-epsg:1.4.1")
    testImplementation(project(":locationtech-proj4j"))

    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.1")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    // Define the main class for the application.
    mainClass = "org.example.app.App"
}
