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
package de.erethon.xlib.runtime;

import de.erethon.xlib.XLib;
import de.erethon.xlib.runtime.command.ListCommand;
import de.erethon.xlib.runtime.command.SerializeCommand;
import de.erethon.xlib.runtime.command.OpenCommand;
import de.erethon.xlib.runtime.command.ReloadCommand;
import de.erethon.xlib.runtime.command.HelpCommand;
import de.erethon.xlib.runtime.command.RegisterMobCommand;
import de.erethon.xlib.runtime.command.RegisterItemCommand;
import de.erethon.xlib.runtime.command.GiveCommand;
import de.erethon.xlib.runtime.command.MainCommand;
import de.erethon.xlib.runtime.command.LootTableCommand;
import de.erethon.xlib.runtime.command.SummonCommand;
import de.erethon.xlib.runtime.command.GiveHeadCommand;
import de.erethon.xlib.runtime.config.IConfig;
import de.erethon.xlib.command.DRECommandCache;
import de.erethon.xlib.compatibility.Internals;
import de.erethon.xlib.compatibility.Version;
import de.erethon.xlib.runtime.listener.ItemBoxListener;
import de.erethon.xlib.runtime.listener.ItemListener;
import de.erethon.xlib.runtime.listener.MobListener;
import de.erethon.xlib.plugin.DREPlugin;
import de.erethon.xlib.plugin.DREPluginSettings;
import de.erethon.xlib.runtime.listener.InventoryListener;
import java.io.File;
import org.bukkit.ChatColor;

/**
 * @author Daniel Saukel
 */
public class XLibRuntime extends DREPlugin {

    private static XLibRuntime instance;
    private XLib api;

    private IConfig iConfig;
    private DRECommandCache iCommands;

    public XLibRuntime() {
        settings = DREPluginSettings.builder()
                .internals(Internals.INDEPENDENT)
                .metrics(true)
                .bStatsResourceId(1041)
                .spigotMCResourceId(14472)
                .build();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        loadIConfig();
        loadAPI();
        loadICommandCache();
        getCommand("givehead").setExecutor(new GiveHeadCommand());

        manager.registerEvents(new ItemBoxListener(this), this);
        manager.registerEvents(new MobListener(api), this);
        ItemListener il = new ItemListener(api);
        manager.registerEvents(il, this);
        if (compat.isSpigot() && Version.isAtLeast(Version.MC1_12_2)) {
            manager.registerEvents(il.new Spigot(), this);
        }
        manager.registerEvents(new InventoryListener(api), this);
    }

    /**
     * @return the plugin instance
     */
    public static XLibRuntime getInstance() {
        return instance;
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
    public DRECommandCache getCommandCache() {
        return iCommands;
    }

    /**
     * load / reload a new instance of ECommandCache
     */
    public void loadICommandCache() {
        iCommands = new DRECommandCache(
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
     * @return the loaded instance of XLib
     */
    public XLib getAPI() {
        return api;
    }

    /**
     * load / reload a new instance of XLib
     */
    public void loadAPI() {
        api = new XLib(this, ChatColor.translateAlternateColorCodes('&', iConfig.getIdentifierPrefix()));
        api.loadDataFiles();
        api.finishInitialization();
    }

}
