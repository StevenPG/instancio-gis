plugins {
    id("java-common-conventions")
    id("java-instancio-gis-library")
    application
}

dependencies {

    // Pull actual JTS library
    implementation("org.locationtech.jts:jts-core:1.20.0")

    // Pull instancio extension
    implementation(project(":locationtech-core"))

    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.1")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    // Define the main class for the application.
    mainClass = "org.example.app.App"
}
