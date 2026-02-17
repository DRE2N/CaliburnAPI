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

import de.erethon.xlib.plugin.DREPlugin;
import java.io.File;

/**
 * @author Daniel Saukel
 */
public class CommonConfig extends DREConfig {

    public static final int CONFIG_VERSION = 1;

    private static CommonConfig instance;

    private boolean updaterEnabled = true;

    public CommonConfig(File file) {
        super(file, CONFIG_VERSION);

        if (initialize) {
            initialize();
        }
        load();
    }

    public boolean isUpdaterEnabled() {
        return updaterEnabled;
    }

    @Override
    public void initialize() {
        if (!config.contains("updaterEnabled")) {
            config.set("updaterEnabled", updaterEnabled);
        }
        save();
    }

    @Override
    public void load() {
        updaterEnabled = config.getBoolean("updaterEnabled", updaterEnabled);
    }

    public static CommonConfig getInstance() {
        if (instance == null) {
            instance = new CommonConfig(new File(DREPlugin.getInstance().getDataFolder().getParent() + "/commons", "config.yml"));
        }
        return instance;
    }

}
