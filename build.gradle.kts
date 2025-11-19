plugins {
    java
    `maven-publish`
    id("dev.yumi.gradle.licenser") version "2.2.1"
}

group = "com.stevenpg.instancio"
version = "0.0.1"

subprojects {
    apply(plugin = "java")
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