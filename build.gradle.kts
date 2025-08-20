plugins {
    java
}

dependencies {
    compileOnly(libs.paper.api)
    compileOnly(libs.miniplaceholders)
    compileOnly("maven.modrinth:Insights:6.19.2")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://central.sonatype.com/repository/maven-snapshots/")
    //maven("https://repo.fvdh.dev/releases")
    maven("https://api.modrinth.com/maven")
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}
