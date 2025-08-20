package io.github.miniplaceholders.expansion.insights;

import dev.frankheijden.insights.api.InsightsPlugin;
import dev.frankheijden.insights.api.config.LimitEnvironment;
import dev.frankheijden.insights.api.objects.wrappers.ScanObject;
import io.github.miniplaceholders.api.resolver.AudienceTagResolver;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record LimitMaxPlaceholder(InsightsPlugin plugin) implements AudienceTagResolver<@NotNull Player> {

    @Override
    public @Nullable Tag tag(
            final @NotNull Player player,
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

        final Location location = player.getLocation();
        final World world = location.getWorld();
        final LimitEnvironment env = new LimitEnvironment(player, world.getName());

        return plugin.getLimits()
                .getFirstLimit(item, env)
                .map(limit -> limit.getLimit(item))
                .map(limitInfo -> Integer.toString(limitInfo.getLimit()))
                .map(Tag::preProcessParsed)
                .orElseThrow(() -> ctx.newException("There is no limit for this Material/Entity"));
    }
}
