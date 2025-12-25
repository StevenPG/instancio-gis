import org.jreleaser.model.Active

plugins {
    id("org.jreleaser")
}

jreleaser {
    configFile.set(layout.projectDirectory.file("config.yaml"))
}
