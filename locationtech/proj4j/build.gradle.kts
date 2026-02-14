plugins {
    id("java-instancio-gis-library")
    id("java-publish-conventions")
}

dependencies {
    implementation("org.locationtech.proj4j:proj4j:1.4.1")
    implementation("org.locationtech.proj4j:proj4j-epsg:1.4.1")
}
