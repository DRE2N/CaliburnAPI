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
package de.erethon.xlib.config;

import de.erethon.xlib.util.FileUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Daniel Saukel
 */
public class MessageHandler {

    private String defaultLanguage = "english";
    private Map<String, ConfigurationSection> messageFiles = new HashMap<>();

    public MessageHandler(File file) {
        if (file.isDirectory()) {
            FileUtil.getFilesForFolder(file).forEach(f -> load(f));
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
        return ChatColor.translateAlternateColorCodes('&', getRaw(language, message));
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
            String replace = args[i];
            output = output.replace("&v" + ++i, replace != null ? replace : null);
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
