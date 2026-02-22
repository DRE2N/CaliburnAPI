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
package de.erethon.xlib.runtime.config;

import de.erethon.xlib.config.DREConfig;
import de.erethon.xlib.plugin.DREPlugin;
import java.io.File;
import org.bukkit.ChatColor;

/**
 * @author Daniel Saukel
 */
public class IConfig extends DREConfig {

    private DREPlugin plugin;

    public static final int CONFIG_VERSION = 2;

    private String language = "english";
    private boolean updaterEnabled = true;
    private String identifierPrefix = "&7";
    private String boxName = "&6Mysterious Box";

    public IConfig(DREPlugin plugin, File file) {
        super(file, CONFIG_VERSION);
        this.plugin = plugin;

        if (initialize) {
            initialize();
        }
        load();
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns if Spiget updater is enabled.
     *
     * @return if Spiget updater is enabled
     */
    public boolean isUpdaterEnabled() {
        return updaterEnabled;
    }

    /**
     * @return the prefix used to identify IXL items
     */
    public String getIdentifierPrefix() {
        return identifierPrefix;
    }

    /**
     * @return the boxName
     */
    public String getBoxName() {
        return ChatColor.translateAlternateColorCodes('&', boxName);
    }

    @Override
    public void initialize() {
        if (!config.contains("language")) {
            config.set("language", language);
        }
        if (!config.contains("updaterEnabled")) {
            config.set("updaterEnabled", updaterEnabled);
        }
        if (!config.contains("identifierPrefix")) {
            config.set("identifierPrefix", identifierPrefix);
        }
        if (!config.contains("boxNames")) {
            config.set("boxName", boxName);
        }

        save();
    }

    @Override
    public void load() {
        language = config.getString("language", language);
        plugin.getMessageHandler().setDefaultLanguage(language);
        updaterEnabled = config.getBoolean("updaterEnabled", updaterEnabled);
        identifierPrefix = config.getString("identifierPrefix", identifierPrefix);
        boxName = config.getString("boxName", boxName);
    }

}
