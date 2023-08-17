package io.github.miniplaceholders.expansion.insights;

import dev.frankheijden.insights.api.InsightsPlugin;
import dev.frankheijden.insights.api.config.LimitEnvironment;
import dev.frankheijden.insights.api.config.limits.LimitInfo;
import dev.frankheijden.insights.api.objects.wrappers.ScanObject;
import io.github.miniplaceholders.api.placeholder.AudiencePlaceholder;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LimitNamePlaceholder implements AudiencePlaceholder {
    private final InsightsPlugin plugin;

    public LimitNamePlaceholder(final InsightsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @Nullable Tag tag(
            final @NotNull Audience audience,
            final @NotNull ArgumentQueue queue,
            final @NotNull Context ctx
    ) {
        final String argument = queue.popOr("You need to provide an argument").value();

        final ScanObject<?> item;
        try {
            item = ScanObject.parse(argument);
        } catch (IllegalArgumentException ex) {
            throw ctx.newException("Invalid Material/Entity");
        }

        final Player player = (Player) audience;

        final Location location = player.getLocation();
        final World world = location.getWorld();
        final LimitEnvironment env = new LimitEnvironment(player, world.getName());

        return plugin.getLimits()
                .getFirstLimit(item, env)
                .map(limit -> limit.getLimit(item))
                .map(LimitInfo::getName)
                .map(Tag::preProcessParsed)
                .orElseThrow(() -> ctx.newException("There is no limit for this Material/Entity"));
    }
}
