import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

plugins {
    java
    `java-library`
    `maven-publish`
}

configure<JavaPluginExtension> {
    withSourcesJar()
    withJavadocJar()
}


extensions.configure<PublishingExtension> {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}