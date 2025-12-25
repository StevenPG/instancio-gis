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
    signing {
        active.set(Active.RELEASE)
        armored.set(true)
    }

    deploy {
        maven {
            mavenCentral {
                register("sonatype") {
                    active.set(Active.RELEASE)
                    url.set("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
                    sign.set(true)
                    stagingRepository("staging/")
                    sourceJar.set(true)
                    checksums.set(true)
                    javadocJar.set(true)
                }
            }
        }
    }
}
