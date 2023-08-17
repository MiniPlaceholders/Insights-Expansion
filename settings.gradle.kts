enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Insights-Expansion"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

include("insights-expansion-paper")
project(":insights-expansion-paper").projectDir = file("paper")

