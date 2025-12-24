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

    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.1")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    // Define the main class for the application.
    mainClass = "org.example.app.App"
}
