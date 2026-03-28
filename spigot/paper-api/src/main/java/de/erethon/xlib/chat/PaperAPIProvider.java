/*
 * Copyright (C) 2015-2026 Daniel Saukel.
 *
 * This library is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNULesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.erethon.xlib.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class PaperAPIProvider extends InternalsProvider {

    private MiniMessage mm = MiniMessage.miniMessage();

    @Override
    void log(String message) {
        Bukkit.getConsoleSender().sendMessage(parse(message));
    }

    @Override
    void broadcastMessage(String message) {
        Bukkit.broadcast(parse(message));
    }

    @Override
    void broadcastCenteredMessage(String message) {
        Bukkit.broadcast(center(message));
    }

    @Override
    void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(parse(message));
    }

    @Override
    void sendCenteredMessage(CommandSender sender, String message) {
        sender.sendMessage(center(message));
    }

    @Override
    void sendActionBar(Player player, String message) {
        player.sendActionBar(parse(message));
    }

    @Override
    void sendTitle(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        player.showTitle(Title.title(parse(title), parse(subtitle), fadeIn, show, fadeOut));
    }

    private Component parse(String string) {
        String colorCoded = ChatColor.translateAlternateColorCodes('&', string);
        if (string.equals(ChatColor.stripColor(colorCoded))) {
            return mm.deserialize(string);
        } else {
            return LegacyComponentSerializer.legacySection().deserialize(colorCoded);
        }
    }

    private Component center(String string) {
        Component parsed = parse(string);
        String stripped = mm.stripTags(string
                .replace("<bold>", ChatColor.BOLD.toString())
                .replace("<b>", ChatColor.BOLD.toString())
                .replace("</bold>", ChatColor.RESET.toString())
                .replace("</b>", ChatColor.RESET.toString())
        );
        String spaces = DefaultFontInfo.getCenterSpaces(stripped);
        return parse(spaces).append(parsed);
    }

}
