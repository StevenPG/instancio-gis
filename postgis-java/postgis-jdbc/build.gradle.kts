plugins {
    id("java-instancio-gis-library")
}

dependencies {
    implementation("net.postgis:postgis-jdbc:2025.1.1")
    api("org.postgresql:postgresql:42.7.7")
}
