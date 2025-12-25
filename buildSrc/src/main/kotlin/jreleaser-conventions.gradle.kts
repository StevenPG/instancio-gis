import org.jreleaser.model.Active

plugins {
    id("org.jreleaser")
}

jreleaser {
    project {
        name.set("instancio-gis")
        version.set("0.0.1")
        description.set("Instancio support for GIS types")
        authors.set(listOf("StevenPG"))
        license.set("Apache-2.0")
        links {
            homepage.set("https://stevenpg.com/instancio-gis")
        }
        inceptionYear.set("2025")
    }
    release {
        github {
            repoOwner.set("StevenPG")
            overwrite.set(true)
            token.set(System.getenv("JRELEASER_GITHUB_TOKEN") ?: "dummy")
        }
    }
    signing {
        active.set(Active.RELEASE)
        armored.set(true)
    }
    deploy {
        maven {
            mavenCentral {
                register("sonatype") {
                    active.set(Active.RELEASE)
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    stagingRepository("build/staging-deploy")
                    sign = true
                    sourceJar = true
                    checksums = true
                    javadocJar = true
                }
            }
        }
    }
}
