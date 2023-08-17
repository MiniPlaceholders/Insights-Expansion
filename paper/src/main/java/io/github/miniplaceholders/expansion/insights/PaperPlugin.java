package io.github.miniplaceholders.expansion.insights;

import dev.frankheijden.insights.api.InsightsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.miniplaceholders.api.Expansion;

@SuppressWarnings("unused")
public final class PaperPlugin extends JavaPlugin {

    @Override
    public void onEnable(){
        this.getSLF4JLogger().info("Starting Insights Expansion for Paper");
        final InsightsPlugin plugin = InsightsPlugin.getInstance();

        Expansion.builder("insights")
            .filter(Player.class)
            .audiencePlaceholder("limits_name", new LimitNamePlaceholder(plugin))
            .audiencePlaceholder("limits_count", new LimitCountPlaceholder(plugin))
            .audiencePlaceholder("limits_max", new LimitMaxPlaceholder(plugin))
            .build()
            .register();
    }
}
