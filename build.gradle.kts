import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    java
    `maven-publish`
    id("dev.yumi.gradle.licenser") version "2.2.1"
}

group = "com.stevenpg.instancio"
version = "0.0.1"

subprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "dev.yumi.gradle.licenser")

    group = rootProject.group
    version = rootProject.version

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        finalizedBy(tasks.named("jacocoTestReport"))
    }

    tasks.withType<JacocoReport> {
        reports {
            xml.required.set(true)
            csv.required.set(false)
            html.required.set(false)
        }
        dependsOn(tasks.named("test"))
    }

    extensions.configure<JacocoPluginExtension>("jacoco") {
        toolVersion = "0.8.12"
    }

    license {
        rule(file("${rootDir}/LICENSE_HEADER"))
        include("**/*.java")
        exclude("**/*.properties")
        exclude("**/*.yaml")
        exclude("./LICENSE_HEADER")
    }
}

// Project-specific configurations
configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_17
}