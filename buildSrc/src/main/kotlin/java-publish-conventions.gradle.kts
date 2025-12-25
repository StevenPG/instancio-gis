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
            pom {
                name.set(project.name)
                description.set("Instancio libraries for common GIS dependencies")
                url.set("https://stevenpg.com/instancio-gis")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("StevenPG")
                        name.set("StevenPG")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/StevenPG/instancio-gis.git")
                    developerConnection.set("scm:git:ssh://github.com/StevenPG/instancio-gis.git")
                    url.set("https://github.com/StevenPG/instancio-gis")
                }
            }
        }
    }
    repositories {
        maven {
            name = "staging"
            url = uri(rootProject.layout.buildDirectory.dir("staging-deploy"))
        }
    }
}