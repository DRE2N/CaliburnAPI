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
import de.erethon.xlib.command.DRECommandRegistry;
import de.erethon.xlib.compatibility.Version;
import de.erethon.xlib.config.CommonMessage;
import de.erethon.xlib.plugin.PluginInit;
import de.erethon.xlib.plugin.PluginMeta;
import de.erethon.xlib.runtime.command.GiveCommand;
import de.erethon.xlib.runtime.command.GiveHeadCommand;
import de.erethon.xlib.runtime.command.HelpCommand;
import de.erethon.xlib.runtime.command.ListCommand;
import de.erethon.xlib.runtime.command.LootTableCommand;
import de.erethon.xlib.runtime.command.MainCommand;
import de.erethon.xlib.runtime.command.OpenCommand;
import de.erethon.xlib.runtime.command.RegisterItemCommand;
import de.erethon.xlib.runtime.command.RegisterMobCommand;
import de.erethon.xlib.runtime.command.ReloadCommand;
import de.erethon.xlib.runtime.command.SerializeCommand;
import de.erethon.xlib.runtime.command.SummonCommand;
import de.erethon.xlib.runtime.config.IConfig;
import de.erethon.xlib.runtime.listener.InventoryListener;
import de.erethon.xlib.runtime.listener.ItemBoxListener;
import de.erethon.xlib.runtime.listener.ItemListener;
import de.erethon.xlib.runtime.listener.MobListener;
import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.update.spiget.comparator.VersionComparator;

/**
 * @author Daniel Saukel
 */
public class XLibRuntime extends JavaPlugin {

    /**
     * Meta information about this project.
     */
    public static final PluginMeta META = new PluginMeta.Builder("XLib-Runtime")
            .minVersion(Version.MC1_8)
            .paperState(PluginMeta.State.NOT_SUPPORTED)
            .spigotState(PluginMeta.State.SUPPORTED)
            .economyState(PluginMeta.State.SUPPORTED)
            .permissionsState(PluginMeta.State.SUPPORTED)
            .spigotMCResourceId(14472)
            .bStatsResourceId(1041)
            .versionComparator(VersionComparator.SEM_VER_SNAPSHOT)
            .build();

    public File dataFolder;
    private static XLibRuntime instance;
    private XLib api;
    private PluginInit init;

    private IConfig iConfig;

    @Override
    public void onEnable() {
        instance = this;
        dataFolder = new File(getDataFolder().getParentFile(), "XLib");
        loadIConfig();
        api = XLib.init(dataFolder, ChatColor.translateAlternateColorCodes('&', iConfig.getIdentifierPrefix()));
        api.loadDataFiles();
        init = new PluginInit(this, api, META);
        init.setDataFolder(dataFolder);
        init.getMessageHandler().setDefaultLanguage(iConfig.getLanguage());
        CommonMessage.messageHandler = init.getMessageHandler();
        init.init(loadCommandRegistry(), iConfig.isUpdaterEnabled());

        getCommand("givehead").setExecutor(new GiveHeadCommand());

        getServer().getPluginManager().registerEvents(new ItemBoxListener(this), this);
        getServer().getPluginManager().registerEvents(new MobListener(api), this);
        ItemListener il = new ItemListener(api);
        getServer().getPluginManager().registerEvents(il, this);
        if (Version.isAtLeast(Version.MC1_12_2)) {
            getServer().getPluginManager().registerEvents(il.new Spigot(), this);
        }
        getServer().getPluginManager().registerEvents(new InventoryListener(api), this);
    }

    /**
     * @return the plugin instance
     */
    public static XLibRuntime getInstance() {
        return instance;
    }

    public PluginInit getInitializer() {
        return init;
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
        iConfig = new IConfig(new File(dataFolder, "config.yml"));
    }

    /**
     * load / reload a new instance of command registry
     *
     * @return the loaded registry
     */
    public DRECommandRegistry loadCommandRegistry() {
        return new DRECommandRegistry(
                "xlib",
                init,
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
    }

    /**
     * @return the loaded instance of XLib
     */
    public XLib getAPI() {
        return api;
    }

}
