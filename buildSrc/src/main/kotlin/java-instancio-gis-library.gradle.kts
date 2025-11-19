plugins {
    `java-library`
    `maven-publish`
}

java {
    withSourcesJar()
    withJavadocJar()
}

val instancioCoreVersion: String = "5.5.1";

dependencies {
    implementation("org.instancio:instancio-core:${instancioCoreVersion}")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://your-repo.com/maven")
            credentials { /* auth */ }
        }
    }
}

tasks.javadoc {
    // javadoc config
}
