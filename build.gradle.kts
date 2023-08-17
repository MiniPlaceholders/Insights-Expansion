plugins {
    java
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(projects.insightsExpansionPaper)
}

subprojects {
    apply<JavaPlugin>()
    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://repo.fvdh.dev/releases")
    }
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    tasks {
        compileJava {
            options.encoding = Charsets.UTF_8.name()
            options.release.set(17)
        }
    }
}

tasks {
    shadowJar {
        archiveFileName.set("${rootProject.name}-${project.version}.jar")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    build {
        dependsOn(shadowJar)
    }
}
