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
package de.erethon.xlib.plugin;

import de.erethon.xlib.XLib;
import de.erethon.xlib.chat.MessageUtil;
import de.erethon.xlib.command.DRECommandRegistry;
import de.erethon.xlib.compatibility.Version;
import de.erethon.xlib.config.MessageHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.update.spiget.SpigetUpdate;
import org.inventivetalent.update.spiget.UpdateCallback;

/**
 * Streamlines plugin initialization.
 *
 * @author Daniel Saukel
 */
public class PluginInit {

    public static final String XL_DEV_MODE = "XLDevMode";

    private JavaPlugin plugin;
    private XLib xlib;
    private PluginMeta meta;
    private DRECommandRegistry commands;
    private MessageHandler messageHandler;

    public PluginInit(JavaPlugin plugin, XLib xlib, PluginMeta meta) {
        this.plugin = plugin;
        this.xlib = xlib;
        this.meta = meta;
    }

    /**
     * Initializes Metrics and Spiget; prints meta to console.
     *
     * @param commands       the command registry
     * @param updaterEnabled if Spiget updater is enabled
     */
    public void init(DRECommandRegistry commands, boolean updaterEnabled) {
        setCommandRegistry(commands);

        if (meta.isMetricsEnabled()) {
            new Metrics(plugin, meta.getBStatsResourceId());
        }

        if (meta.isSpigotMCResource() && updaterEnabled) {
            try {
                SpigetUpdate updater;
                if (Version.isAtLeast(Version.MC1_18)) {
                    updater = new SpigetUpdate(plugin, meta.getSpigotMCResourceId());
                } else {
                    updater = (SpigetUpdate) Class.forName(SpigetUpdate.class.getName() + "Legacy")
                            .getConstructor(Plugin.class, int.class)
                            .newInstance(plugin, meta.getSpigotMCResourceId());
                }
                updater.setVersionComparator(meta.getVersionComparator());
                updater.checkForUpdate(new UpdateCallback() {
                    @Override
                    public void updateAvailable(String newVersion, String downloadUrl, boolean hasDirectDownload) {
                        MessageUtil.log(plugin, "A new version of " + plugin.getName() + " is available (" + newVersion + "). Download it here: " + downloadUrl);
                    }

                    @Override
                    public void upToDate() {
                        MessageUtil.log(plugin, "The plugin is up to date.");
                    }
                });
            } catch (Exception exception) {
                MessageUtil.log(plugin, "Error: Could not initialize Spiget updater:");
                exception.printStackTrace();
                return;
            }
        }

        meta.printToConsole(xlib);
    }

    /**
     * Returns if the server jar has been started with the XLDevMode flag.
     *
     * @return if the server jar has been started with the XLDevMode flag
     */
    public boolean isXLDevMode() {
        return System.getProperty(XL_DEV_MODE) != null;
    }

    /**
     * Returns the plugin associated with this initializer.
     *
     * @return the plugin associated with this initializer
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Returns the loaded instance of {@link MessageHandler}.
     *
     * @return the loaded instance of {@link MessageHandler}
     */
    public MessageHandler getMessageHandler() {
        if (messageHandler == null) {
            reloadMessageHandler();
        }
        return messageHandler;
    }

    /**
     * Reloads the language files.
     */
    public void reloadMessageHandler() {
        File languages = new File(plugin.getDataFolder(), "languages");
        languages.mkdirs();
        attemptToSaveResource("languages/english.yml", false);
        attemptToSaveResource("languages/french.yml", false);
        attemptToSaveResource("languages/german.yml", false);
        messageHandler = new MessageHandler(languages);
    }

    /**
     * Returns the command registry of the plugin
     *
     * @return the command registry of the plugin
     */
    public DRECommandRegistry getCommandRegistry() {
        return commands;
    }

    /**
     * Sets and registers the command registry to the given value.
     *
     * @param commands the command registry
     */
    public void setCommandRegistry(DRECommandRegistry commands) {
        this.commands = commands;
        commands.register(plugin);
    }

    /**
     * Attempts to save a resource.
     * <p>
     * See {@link org.bukkit.plugin.Plugin#saveResource(java.lang.String, boolean)}. This does not throw an exception.
     * <p>
     * Updates the file if it lacks configuration paths the resource has.
     *
     * @param resource the path to the resource to save
     * @param replace  if the resource shall be replaced
     * @return if the resource was saved or updated
     */
    public boolean attemptToSaveResource(String resource, boolean replace) {
        File file = new File(plugin.getDataFolder(), resource);
        if (replace || !file.exists()) {
            try {
                plugin.saveResource(resource, replace);
                return true;
            } catch (IllegalArgumentException exception) {
                return false;
            }

        } else {
            boolean updated = false;
            InputStream is = plugin.getResource(resource);
            if (is == null) {
                return false;
            }
            YamlConfiguration resourceCfg = YamlConfiguration.loadConfiguration(new InputStreamReader(is, Charset.forName("UTF-8")));
            YamlConfiguration fileCfg = YamlConfiguration.loadConfiguration(file);
            for (String key : resourceCfg.getKeys(true)) {
                if (!fileCfg.contains(key)) {
                    fileCfg.set(key, resourceCfg.get(key));
                    updated = true;
                }
            }
            if (updated) {
                try {
                    fileCfg.save(file);
                } catch (IOException exception) {
                    MessageUtil.log(plugin, "&4File \"" + resource + "\" could not be updated.");
                    exception.printStackTrace();
                }
            }
            return updated;
        }
    }

    /**
     * Overrides the data folder of the plugin.
     *
     * @param dataFolder the data folder to set
     */
    public void setDataFolder(File dataFolder) {
        try {
            Field field = JavaPlugin.class.getDeclaredField("dataFolder");
            field.setAccessible(true);
            field.set(plugin, dataFolder);

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            MessageUtil.log(plugin, "&cError: Could not set data folder!");
        }
    }

}
