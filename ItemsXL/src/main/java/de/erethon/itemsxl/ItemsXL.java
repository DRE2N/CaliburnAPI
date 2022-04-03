/*
 * Copyright (C) 2015-2022 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erethon.itemsxl;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.bedrock.command.ECommandCache;
import de.erethon.bedrock.compatibility.Internals;
import de.erethon.bedrock.plugin.EPlugin;
import de.erethon.bedrock.plugin.EPluginSettings;
import de.erethon.itemsxl.command.*;
import de.erethon.itemsxl.config.IConfig;
import de.erethon.itemsxl.item.ItemBoxListener;
import de.erethon.vignette.api.VignetteAPI;
import java.io.File;
import org.bukkit.ChatColor;

/**
 * @author Daniel Saukel
 */
public class ItemsXL extends EPlugin {

    private CaliburnAPI api;

    private IConfig iConfig;
    private ECommandCache iCommands;

    public ItemsXL() {
        settings = EPluginSettings.builder()
                .internals(Internals.INDEPENDENT)
                .metrics(true)
                .bStatsResourceId(1041)
                .spigotMCResourceId(14472)
                .build();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        VignetteAPI.init(this);
        loadIConfig();
        loadAPI();
        loadICommandCache();

        manager.registerEvents(new ItemBoxListener(this), this);
    }

    /**
     * @return the loaded instance of IConfig
     */
    public IConfig getIConfig() {
        return iConfig;
    }

    /**
     * load / reload a new instance of IConfig
     */
    public void loadIConfig() {
        iConfig = new IConfig(this, new File(getDataFolder(), "config.yml"));
    }

    @Override
    public ECommandCache getCommandCache() {
        return iCommands;
    }

    /**
     * load / reload a new instance of ECommandCache
     */
    public void loadICommandCache() {
        iCommands = new ECommandCache(
                "itemsxl",
                this,
                new HelpCommand(this),
                new GiveCommand(this),
                new ListCommand(this),
                new LootTableCommand(this),
                new MainCommand(this),
                new OpenCommand(this),
                new RegisterItemCommand(this),
                new RegisterMobCommand(this),
                new ReloadCommand(this),
                new SerializeCommand(this),
                new SummonCommand(this)
        );

        iCommands.register(this);
    }

    /**
     * @return the loaded instance of CaliburnAPI
     */
    public CaliburnAPI getAPI() {
        return api;
    }

    /**
     * load / reload a new instance of CaliburnAPI
     */
    public void loadAPI() {
        api = new CaliburnAPI(this, ChatColor.translateAlternateColorCodes('&', iConfig.getIdentifierPrefix()));
        api.loadDataFiles();
        api.finishInitialization();
    }

}
