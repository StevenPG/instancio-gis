plugins {
    id("java-instancio-gis-library")
}

dependencies {
    implementation("net.postgis:postgis-geometry:2025.1.1")
    // Use PGgeometry WKT parsing for convenience
    implementation("net.postgis:postgis-jdbc:2025.1.1")
}