package io.github.miniplaceholders.expansion.insights;

import dev.frankheijden.insights.api.InsightsPlugin;
import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.api.provider.ExpansionProvider;
import io.github.miniplaceholders.api.provider.LoadRequirement;
import io.github.miniplaceholders.api.types.Platform;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public final class InsightsExpansionProvider implements ExpansionProvider {

    @Override
    public Expansion provideExpansion() {
        final InsightsPlugin plugin = InsightsPlugin.getInstance();
        return Expansion.builder("insights")
                .version("2.0.0")
                .author("MiniPlaceholders Contributors")
                .audiencePlaceholder(Player.class, "limits_name", new LimitNamePlaceholder(plugin))
                .audiencePlaceholder(Player.class, "limits_count", new LimitCountPlaceholder(plugin))
                .audiencePlaceholder(Player.class, "limits_max", new LimitMaxPlaceholder(plugin))
                .build();
    }

    @Override
    public LoadRequirement loadRequirement() {
        return LoadRequirement.allOf(LoadRequirement.platform(Platform.PAPER), LoadRequirement.requiredComplement("Insights"));
    }
}
