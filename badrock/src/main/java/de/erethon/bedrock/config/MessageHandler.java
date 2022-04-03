package de.erethon.bedrock.config;

import de.erethon.bedrock.chat.MessageUtil;
import de.erethon.bedrock.misc.FileUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daniel Saukel, Fyreum
 */
public class MessageHandler {

    private String defaultLanguage = "english";
    private final Map<String, ConfigurationSection> messageFiles = new HashMap<>();

    public MessageHandler(File file) {
        if (file.isDirectory()) {
            FileUtil.getFilesForFolder(file).forEach(this::load);
        } else {
            load(file);
        }
    }

    private void load(File file) {
        if (file.getName().endsWith(".yml")) {
            messageFiles.put(file.getName().substring(0, file.getName().length() - 4), YamlConfiguration.loadConfiguration(file));
        }
    }

    /**
     * Returns the default language file name.
     *
     * @return the default language file name
     */
    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    /**
     * Sets the default language file name.
     *
     * @param defaultLanguage the default language file name to set
     */
    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    /**
     * Returns the unformatted message String.
     *
     * @param language the language
     * @param path     the message configuration path
     * @return the unformatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    public String getRaw(String language, String path) {
        if (path == null) {
            return null;
        }
        ConfigurationSection config = messageFiles.get(language);
        if (config == null) {
            return path;
        }
        try {
            String message = config.getString(path);
            return message != null ? message : path;
        } catch (Exception exception) {
            return "{erroneous config at " + path + "}";
        }
    }

    /**
     * Returns the unformatted message String.
     *
     * @param language the language
     * @param message  the message
     * @return the unformatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    public String getRaw(String language, Message message) {
        return message != null ? getRaw(language, message.getPath()) : null;
    }

    /**
     * Returns the formatted message String.
     *
     * @param language the language
     * @param message  the message
     * @return the formatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    public String getMessage(String language, Message message) {
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', getRaw(language, message));//PATCH
    }

    /**
     * Returns the formatted message String.
     *
     * @param message the message
     * @return the formatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    public String getMessage(Message message) {
        return getMessage(getDefaultLanguage(), message);
    }

    /**
     * Returns the formatted message String.
     *
     * @param language the language
     * @param message  the message
     * @param args     Strings to replace possible variables in the message
     * @return the formatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    public String getMessage(String language, Message message, String... args) {
        String output = getMessage(language, message);
        int i = 0;
        while (i < args.length) {
            String replace = args[i] == null ? "" : args[i];
            output = output.replace("&v" + ++i, replace);
        }
        return output;
    }

    /**
     * Returns the formatted message String.
     *
     * @param message the message
     * @param args    Strings to replace possible variables in the message
     * @return the formatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    public String getMessage(Message message, String... args) {
        return getMessage(getDefaultLanguage(), message, args);
    }

}
