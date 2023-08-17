enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Example-Expansion"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

arrayOf("common", "paper", "velocity", "sponge").forEach {
    include("example-expansion-$it")

    project(":example-expansion-$it").projectDir = file(it)
}

