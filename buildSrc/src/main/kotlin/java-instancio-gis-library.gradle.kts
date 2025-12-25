plugins {
    `java-library`
}

val instancioCoreVersion: String = "5.5.1"

dependencies {
    implementation("org.instancio:instancio-core:${instancioCoreVersion}")
}

tasks.javadoc {
    // javadoc config
}
