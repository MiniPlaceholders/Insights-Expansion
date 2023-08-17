dependencies {
    compileOnly(libs.paper.api)
    compileOnly(libs.miniplaceholders)
    compileOnly(libs.insights)
}

tasks {
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}
