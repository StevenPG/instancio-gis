plugins {
    id("java-instancio-gis-library")
}

dependencies {
    implementation("net.postgis:postgis-jdbc-jts:2025.1.1")
    implementation("org.locationtech.jts:jts-core:1.20.0")
    implementation(project(":postgis-java-jdbc"))
    implementation(project(":locationtech-core"))
}