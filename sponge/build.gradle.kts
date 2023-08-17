import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.model.PluginDependency

plugins {
    id("org.spongepowered.gradle.plugin") version "2.1.1"
}

dependencies {
    compileOnly(libs.miniplaceholders)
    implementation(projects.exampleExpansionCommon)
}

sponge {
    apiVersion("8.1.0")
    license("GPL-3")
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }
    plugin("example-expansion") {
        displayName("Example-Expansion")
        entrypoint("io.github.miniplaceholders.expansion.example.sponge.SpongePlugin")
        description(project.description)
        links {
            homepage("https://github.com/MiniPlaceholders/Example-Expansion")
            source("https://github.com/MiniPlaceholders/Example-Expansion")
            issues("https://github.com/MiniPlaceholders/Example-Expansion/issues")
        }
        contributor("4drian3d") {
            description("Lead Developer")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
        dependency("miniplaceholders") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
            version("2.2.0")
        }
    }
}
