package de.erethon.bedrock.chat;

import de.erethon.bedrock.plugin.EPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import de.erethon.CaliComp;

/**
 * @author Daniel Saukel, Fyreum
 */
public class MessageUtil {

    private static final boolean paper = Package.getPackage("com.destroystokyo.paper") != null;// PATCH
    private static final boolean is1_8_8 = Package.getPackage("org.bukkit.craftbukkit.v1_8_R3") != null;// PATCH
    private static final MiniMessage mm = MiniMessage.miniMessage();
    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)&[0-9A-FK-ORX]");

    /**
     * Logs a message to the console. Supports color codes.
     *
     * @param message the message String
     */
    public static void log(String message) {
        log(EPlugin.getInstance(), message);
    }

    /**
     * Logs a message to the console. Supports color codes.
     *
     * @param plugin  the logging plugin
     * @param message the message String
     */
    public static void log(Plugin plugin, String message) {
        log(plugin, parse(message));
    }

    /**
     * Logs a message to the console.
     *
     * @param message the message Component
     */
    public static void log(Component message) {
        log(EPlugin.getInstance(), message);
    }

    /**
     * Logs a message to the console.
     *
     * @param plugin  the logging plugin
     * @param message the message Component
     */
    public static void log(Plugin plugin, Component message) {
        // PATCH
        if (paper) {
            Bukkit.getConsoleSender().sendMessage(Component.text("[" + plugin.getName() + "] ").append(message));
        } else {
            CaliComp.adventure().sender(Bukkit.getConsoleSender()).sendMessage(Component.text("[" + plugin.getName() + "] ").append(message));
        }
    }

    /**
     * Broadcasts a message to all players. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastMessage(String message) {
        broadcastMessage(parse(message));
    }

    /**
     * Broadcasts the Component to all players.
     *
     * @param message the message component
     */
    public static void broadcastMessage(Component message) {
        Bukkit.getOnlinePlayers().forEach((p) -> sendMessage(p, message));
    }

    /**
     * Broadcasts a message to all players. Supports color codes.
     *
     * @param filter  the player filter
     * @param message the message String
     */
    public static void broadcastMessageIf(String message, Predicate<Player> filter) {
        broadcastMessageIf(parse(message), filter);
    }

    /**
     * Broadcasts the Component to all players.
     *
     * @param filter  the player filter
     * @param message the message component
     */
    public static void broadcastMessageIf(Component message, Predicate<Player> filter) {
        Bukkit.getOnlinePlayers().forEach((p) -> {
            if (filter.test(p)) {
                sendMessage(p, message);
            }
        });
    }

    /**
     * Broadcasts a perfectly centered message to all players. Supports color codes. (Component formatting like <COLOR> or <rainbow> can cause issues)
     *
     * @param message the message String
     */
    public static void broadcastCenteredMessage(String message) {
        broadcastMessage(DefaultFontInfo.center(message));
    }

    /**
     * Broadcasts a perfectly centered Component message to all players. (Component formatting like <COLOR> or <rainbow> can cause issues)
     *
     * @param message the message String
     */
    public static void broadcastCenteredMessage(Component message) {
        broadcastCenteredMessage(serialize(message));
    }

    /**
     * Broadcasts a perfectly centered message to all players. Supports color codes. (Component formatting like <COLOR> or <rainbow> can cause issues)
     *
     * @param filter  the player filter
     * @param message the message String
     */
    public static void broadcastCenteredMessageIf(String message, Predicate<Player> filter) {
        broadcastMessageIf(DefaultFontInfo.center(message), filter);
    }

    /**
     * Broadcasts a perfectly centered Component message to all players. (Component formatting like <COLOR> or <rainbow> can cause issues)
     *
     * @param filter  the player filter
     * @param message the message String
     */
    public static void broadcastCenteredMessageIf(Component message, Predicate<Player> filter) {
        broadcastCenteredMessageIf(serialize(message), filter);
    }

    /**
     * Broadcasts the plugin name formatted to a player (or another sender), for example as a headline.
     *
     * @param plugin the plugin
     */
    public static void broadcastPluginTag(Plugin plugin) {
        broadcastCenteredMessage(getPluginTag(plugin));
    }

    /**
     * Broadcasts the plugin name formatted to a player (or another sender), for example as a headline.
     *
     * @param filter the player filter
     * @param plugin the plugin
     */
    public static void broadcastPluginTagIf(Plugin plugin, Predicate<Player> filter) {
        broadcastCenteredMessageIf(getPluginTag(plugin), filter);
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
        Bukkit.getOnlinePlayers().forEach((p) -> sendTitleMessage(p, title, subtitle, fadeIn, show, fadeOut));
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
        broadcastTitleMessage(title, "");
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     * @param fadeIn   the time in ticks it takes for the message to appear
     * @param show     the time in ticks how long the message will be visible
     * @param fadeOut  the time in ticks it takes for the message to disappear
     * @param filter   the player filter
     */
    public static void broadcastTitleMessageIf(String title, String subtitle, int fadeIn, int show, int fadeOut, Predicate<Player> filter) {
        Bukkit.getOnlinePlayers().forEach((p) -> {
            if (filter.test(p)) {
                sendTitleMessage(p, title, subtitle, fadeIn, show, fadeOut);
            }
        });
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     * @param filter   the player filter
     */
    public static void broadcastTitleMessageIf(String title, String subtitle, Predicate<Player> filter) {
        broadcastTitleMessageIf(title, subtitle, 20, 60, 20, filter);
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title  the message of the first, big line
     * @param filter the player filter
     */
    public static void broadcastTitleMessageIf(String title, Predicate<Player> filter) {
        broadcastTitleMessageIf(title, "", filter);
    }

    /**
     * Broadcasts an action bar message. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastActionBarMessage(String message) {
        broadcastActionBarMessage(parse(message));
    }

    /**
     * Broadcasts an action bar message.
     *
     * @param message the message components
     */
    public static void broadcastActionBarMessage(Component message) {
        Bukkit.getOnlinePlayers().forEach((p) -> sendActionBarMessage(p, message));
    }

    /**
     * Broadcasts an action bar message. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastActionBarMessageIf(String message, Predicate<Player> filter) {
        broadcastActionBarMessageIf(parse(message), filter);
    }

    /**
     * Broadcasts an action bar message.
     *
     * @param message the message components
     */
    public static void broadcastActionBarMessageIf(Component message, Predicate<Player> filter) {
        Bukkit.getOnlinePlayers().forEach((p) -> {
            if (filter.test(p)) {
                sendActionBarMessage(p, message);
            }
        });
    }

    /**
     * Broadcasts a fat message Does not support color codes.
     *
     * @param color the color of the message
     * @param word  the word to send
     */
    public static void broadcastFatMessage(ChatColor color, String word) {
        word = replaceLegacyChars(word);
        word = ChatColor.stripColor(word);
        String[] fat = FatLetter.fromString(word);

        for (Player player : Bukkit.getOnlinePlayers()) {
            sendCenteredMessage(player, color + fat[0]);
            sendCenteredMessage(player, (color + fat[1]));
            sendCenteredMessage(player, (color + fat[2]));
            sendCenteredMessage(player, (color + fat[3]));
            sendCenteredMessage(player, (color + fat[4]));
        }
    }

    /**
     * Broadcasts a fat message Does not support color codes.
     *
     * @param color the color of the message
     * @param word  the word to send
     */
    public static void broadcastFatMessageIf(ChatColor color, String word, Predicate<Player> filter) {
        word = replaceLegacyChars(word);
        word = ChatColor.stripColor(word);
        String[] fat = FatLetter.fromString(word);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!filter.test(player)) {
                continue;
            }
            sendCenteredMessage(player, color + fat[0]);
            sendCenteredMessage(player, (color + fat[1]));
            sendCenteredMessage(player, (color + fat[2]));
            sendCenteredMessage(player, (color + fat[3]));
            sendCenteredMessage(player, (color + fat[4]));
        }
    }

    /**
     * Sends a message to a specific player (or another CommandSender). Supports color codes.
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendMessage(CommandSender sender, String message) {
        sendMessage(sender, parse(message));
    }

    /**
     * Sends a Component message to a specific player (or another CommandSender).
     *
     * @param sender  the sender
     * @param message the message component
     */
    public static void sendMessage(CommandSender sender, Component message) {
        // PATCH
        if (paper) {
            sender.sendMessage(message);
        } else {
            CaliComp.adventure().sender(sender).sendMessage(message);
        }
    }

    /**
     * Sends a perfectly centered message to a specific player (or another CommandSender). Supports color codes.
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendCenteredMessage(CommandSender sender, String message) {
        sendMessage(sender, DefaultFontInfo.center(message));
    }

    /**
     * Sends a perfectly centered Component message to a specific player (or another CommandSender).
     *
     * @param sender  the sender
     * @param message the message component
     */
    public static void sendCenteredMessage(CommandSender sender, Component message) {
        sendCenteredMessage(sender, serialize(message));
    }

    /**
     * Sends the plugin name formatted to a player (or another sender), for example as a headline.
     *
     * @param sender the sender
     * @param plugin the plugin
     */
    public static void sendPluginTag(CommandSender sender, Plugin plugin) {
        sendCenteredMessage(sender, getPluginTag(plugin));
    }

    /**
     * Returns the formatted plugin name.
     *
     * @param plugin the plugin
     * @return the formatted plugin name
     */
    public static String getPluginTag(Plugin plugin) {
        return "&4&l[ &6" + plugin.getDescription().getName() + " &4&l]";
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
        title = serialize(parse(title));
        subtitle = serialize(parse(subtitle));
        if (!is1_8_8) {
            player.sendTitle(title, subtitle, fadeIn, show, fadeOut);
        } else {
            Bukkit1_8_8.sendTitle(player, title, subtitle, fadeIn, show, fadeOut);
        }
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
        // PATCH
        if (!is1_8_8) {
            sendActionBarMessage(player, parse(message));
        } else {
            Bukkit1_8_8.sendActionBar(player, message);
        }
    }

    /**
     * Sends an action bar message.
     *
     * @param player  the player who will receive the message
     * @param message the message components
     */
    public static void sendActionBarMessage(Player player, Component message) {
        // PATCH
        if (paper) {
            player.sendActionBar(message);
        } else {
            CaliComp.adventure().player(player).sendActionBar(message);
        }
    }

    /**
     * Sends a fat message. Does not support color codes.
     *
     * @param player the player who will receive the message
     * @param color  the color of the message
     * @param word   the word to send
     */
    public static void sendFatMessage(Player player, ChatColor color, String word) {
        word = replaceLegacyChars(word);
        word = ChatColor.stripColor(word);
        String[] fat = FatLetter.fromString(word);
        sendCenteredMessage(player, color + fat[0]);
        sendCenteredMessage(player, color + fat[1]);
        sendCenteredMessage(player, color + fat[2]);
        sendCenteredMessage(player, color + fat[3]);
        sendCenteredMessage(player, color + fat[4]);
    }

    /**
     * Parses the string.
     * <p>
     * Translates color codes and MiniMessage tags
     *
     * @param msg the message to parse
     * @return the parsed Component
     */
    public static Component parse(String msg) {
        return parse(mm, msg);
    }

    /**
     * Parses the string.
     * <p>
     * Translates color codes and MiniMessage tags
     *
     * @param mm  the MiniMessage instance to use
     * @param msg the message to parse
     * @return the parsed Component
     */
    public static Component parse(MiniMessage mm, String msg) {
        String translated = replaceLegacyChars(msg);
        return mm.deserialize(translated);
    }

    /**
     * Serializes the msg component.
     *
     * @param msg the message to serialize
     * @return the serialized string
     */
    public static String serialize(Component msg) {
        return mm.serialize(msg);
    }

    /**
     * Calls the {@link MiniMessage#stripTags(String input)} method.
     *
     * @param input the string to strip
     * @return the striped input
     */
    public static String stripTokens(String input) {
        return mm.stripTags(input);
    }

    /**
     * Strips all translated and untranslated color codes.
     *
     * @param input the string to strip
     * @return the striped input
     */
    public static String stripColor(String input) {
        return ChatColor.stripColor(STRIP_COLOR_PATTERN.matcher(input).replaceAll(""));
    }

    /**
     * Replace legacy chat color chars with matching {@link MiniMessage} tags.
     *
     * @param string the String to replace
     * @return the replaced string
     */
    public static String replaceLegacyChars(String string) {
        for (ChatColor color : ChatColor.values()) {
            string = string.replaceAll("[&|\u00A7]" + color.getChar(), "<" + color.name().toLowerCase() + ">");// PATCH
        }
        return string;
    }

}
