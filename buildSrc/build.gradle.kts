plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jreleaser:jreleaser-gradle-plugin:1.21.0")

}
