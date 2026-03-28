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

import de.erethon.xlib.compatibility.Version;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class InternalsProvider {

    static final Pattern HEX_COLOR_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");

    void log(String message) {
        Bukkit.getConsoleSender().sendMessage(parse(message));
    }

    void broadcastMessage(String message) {
        Bukkit.broadcastMessage(parse(message));
    }

    void broadcastCenteredMessage(String message) {
        Bukkit.broadcastMessage(center(message));
    }

    void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(parse(message));
    }

    void sendCenteredMessage(CommandSender sender, String message) {
        sender.sendMessage(center(message));
    }

    void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    void sendTitle(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, show, fadeOut);
    }

    private String parse(String string) {
        if (Version.isAtLeast(Version.MC1_16)) {
            Matcher match = HEX_COLOR_PATTERN.matcher(string);
            while (match.find()) {
                String color = string.substring(match.start(), match.end());
                string = string.replace(color, ChatColor.of(color) + "");
                match = HEX_COLOR_PATTERN.matcher(string);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    private String center(String string) {
        string = parse(string);
        return DefaultFontInfo.getCenterSpaces(string) + string;
    }

}
