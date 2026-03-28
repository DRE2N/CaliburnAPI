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

import de.erethon.xlib.compatibility.RuntimeSpecificLoader;
import de.erethon.xlib.compatibility.Version;
import de.erethon.xlib.plugin.PluginInit;
import java.util.function.Predicate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author Daniel Saukel
 */
public class MessageUtil {

    static InternalsProvider internals = RuntimeSpecificLoader.loadImplementation(InternalsProvider.class, Version.MC1_10_2, Version.MC1_18_2, new InternalsProvider());

    /**
     * Logs a message to the console. Supports color codes.
     *
     * @param message the message String
     */
    public static void log(String message) {
        internals.log(message);
    }

    /**
     * Logs a message to the console. Supports color codes.
     *
     * @param plugin  the logging plugin
     * @param message the message String
     */
    public static void log(Plugin plugin, String message) {
        log(plugin.getName(), message);
    }

    /**
     * Logs a message to the console. Supports color codes.
     *
     * @param pluginName the name of the logging plugin
     * @param message    the message String
     */
    public static void log(String pluginName, String message) {
        log("[" + pluginName + "] " + message);
    }

    /**
     * {@link #log(java.lang.String)}s the message if {@link PluginInit#isXLDevMode()}.
     *
     * @param message the message to log
     */
    public static void debug(String message) {
        if (System.getProperty(PluginInit.XL_DEV_MODE) != null) {
            log(message);
        }
    }

    /**
     * {@link #log(org.bukkit.plugin.Plugin, java.lang.String)}s the message if {@link PluginInit#isXLDevMode()}.
     *
     * @param plugin  the logging plugin
     * @param message the message to log
     */
    public static void debug(Plugin plugin, String message) {
        debug(plugin.getName(), message);
    }

    /**
     * {@link #log(java.lang.String, java.lang.String)}s the message if {@link PluginInit#isXLDevMode()}.
     *
     * @param pluginName the name of the logging plugin
     * @param message    the message to log
     */
    public static void debug(String pluginName, String message) {
        if (System.getProperty(PluginInit.XL_DEV_MODE) != null) {
            log(pluginName, message);
        }
    }

    /**
     * Throws an {@link java.lang.AssertionError#AssertionError(java.lang.String)} with the given message if the given predicate is satisfied.
     *
     * @param <T>       the predicate type
     * @param message   the error message
     * @param t         the object to test
     * @param predicate the predicate
     */
    public static <T> void assertTrue(String message, T t, Predicate<T> predicate) {
        if (System.getProperty(PluginInit.XL_DEV_MODE) != null && !predicate.test(t)) {
            throw new AssertionError(message);
        }
    }

    /**
     * Broadcasts a message to all players. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastMessage(String message) {
        internals.broadcastMessage(message);
    }

    /**
     * Broadcasts a perfectly centered message to all players. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastCenteredMessage(String message) {
        internals.broadcastCenteredMessage(message);
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     * @param fadeIn   the time in ticks it takes for the message to appear
     * @param show     the time in ticks how long the message will be visible
     * @param fadeOut  the time in ticks it takes for the message to disappear
     */
    public static void broadcastTitleMessage(String title, String subtitle, int fadeIn, int show, int fadeOut) {
        Bukkit.getOnlinePlayers().forEach(p -> sendTitleMessage(p, title, subtitle, fadeIn, show, fadeOut));
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     */
    public static void broadcastTitleMessage(String title, String subtitle) {
        broadcastTitleMessage(title, subtitle, 20, 60, 20);
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title the message of the first, big line
     */
    public static void broadcastTitleMessage(String title) {
        broadcastTitleMessage(title, "", 20, 60, 20);
    }

    /**
     * Broadcasts an action bar message. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastActionBarMessage(String message) {
        Bukkit.getOnlinePlayers().forEach(p -> sendActionBarMessage(p, message));
    }

    /**
     * Broadcasts a fat message Does not support color codes.
     *
     * @param color the color of the message
     * @param word  the word to send
     */
    public static void broadcastFatMessage(ChatColor color, String word) {
        word = ChatColor.stripColor(word);
        String[] fat = FatLetter.fromString(word);
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendCenteredMessage(player, color + fat[0]);
            sendCenteredMessage(player, color + fat[1]);
            sendCenteredMessage(player, color + fat[2]);
            sendCenteredMessage(player, color + fat[3]);
            sendCenteredMessage(player, color + fat[4]);
        }
    }

    /**
     * Sends a message to a specific player (or another CommandSender). Supports color codes.
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendMessage(CommandSender sender, String message) {
        internals.sendMessage(sender, message);
    }

    /**
     * Sends a perfectly centered message to a specific player (or another CommandSender). Supports color codes.
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendCenteredMessage(CommandSender sender, String message) {
        internals.sendCenteredMessage(sender, message);
    }

    /**
     * Sends the plugin name formatted to a player (or another sender), for example as a headline.
     *
     * @param sender the sender
     * @param plugin the plugin
     */
    public static void sendPluginTag(CommandSender sender, Plugin plugin) {
        sendCenteredMessage(sender, "&4&l[ &6" + plugin.getDescription().getName() + " &4&l]");
    }

    /**
     * Sends a title message. Supports color codes.
     *
     * @param player   the player who will receive the message
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     * @param fadeIn   the time in ticks it takes for the message to appear
     * @param show     the time in ticks how long the message will be visible
     * @param fadeOut  the time in ticks it takes for the message to disappear
     */
    public static void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        internals.sendTitle(player, title, subtitle, fadeIn, show, fadeOut);
    }

    /**
     * Sends a title message. Supports color codes.
     *
     * @param player   the player who will receive the message
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     */
    public static void sendTitleMessage(Player player, String title, String subtitle) {
        sendTitleMessage(player, title, subtitle, 20, 60, 20);
    }

    /**
     * Sends a title message. Supports color codes.
     *
     * @param player the player who will receive the message
     * @param title  the message of the first, big line
     */
    public static void sendTitleMessage(Player player, String title) {
        sendTitleMessage(player, title, "", 20, 60, 20);
    }

    /**
     * Sends an action bar message. Supports color codes.
     *
     * @param player  the player who will receive the message
     * @param message the message String
     */
    public static void sendActionBarMessage(Player player, String message) {
        internals.sendActionBar(player, message);
    }

    /**
     * Sends a fat message. Does not support color codes.
     *
     * @param player the player who will receive the message
     * @param color  the color of the message
     * @param word   the word to send
     */
    public static void sendFatMessage(Player player, ChatColor color, String word) {
        word = ChatColor.stripColor(word);
        String[] fat = FatLetter.fromString(word);
        sendCenteredMessage(player, color + fat[0]);
        sendCenteredMessage(player, color + fat[1]);
        sendCenteredMessage(player, color + fat[2]);
        sendCenteredMessage(player, color + fat[3]);
        sendCenteredMessage(player, color + fat[4]);
    }

}
