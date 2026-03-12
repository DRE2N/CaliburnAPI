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
import de.erethon.xlib.compatibility.RuntimeTrait;
import de.erethon.xlib.compatibility.RuntimeType;
import de.erethon.xlib.compatibility.Version;
import static de.erethon.xlib.plugin.PluginMeta.State.*;
import org.inventivetalent.update.spiget.comparator.VersionComparator;

/**
 * Contains standardized meta for a plugin.
 *
 * @author Daniel Saukel
 */
public class PluginMeta {

    /**
     * Represents how the presence of a feature affects compatibility.
     */
    public enum State {
        /**
         * The plugin does not work without this feature.
         */
        REQUIRED,
        /**
         * The plugin works if this feature is present, but also if it isn't.
         */
        SUPPORTED,
        /**
         * The plugin does not work in an environment with this feature.
         */
        NOT_SUPPORTED
    }

    public static class Builder {

        private String name;
        private Version minVersion = Version.MC1_8;
        private State paperState = SUPPORTED;
        private State spigotState = SUPPORTED;
        private State economyState = SUPPORTED;
        private State permissionsState = SUPPORTED;
        private int spigotMCResourceId = -1;
        private int bStatsResourceId = -1;
        private VersionComparator versionComparator = VersionComparator.SEM_VER_SNAPSHOT;

        public Builder(String name) {
            this.name = name;
        }

        public Builder minVersion(Version minVersion) {
            this.minVersion = minVersion;
            return this;
        }

        public Builder paperState(State paperState) {
            this.paperState = paperState;
            return this;
        }

        public Builder spigotState(State spigotState) {
            this.spigotState = spigotState;
            return this;
        }

        public Builder economyState(State economyState) {
            this.economyState = economyState;
            return this;
        }

        public Builder permissionsState(State permissionsState) {
            this.permissionsState = permissionsState;
            return this;
        }

        public Builder spigotMCResourceId(int spigotMCResourceId) {
            this.spigotMCResourceId = spigotMCResourceId;
            return this;
        }

        public Builder bStatsResourceId(int bStatsResourceId) {
            this.bStatsResourceId = bStatsResourceId;
            return this;
        }

        public Builder versionComparator(VersionComparator versionComparator) {
            this.versionComparator = versionComparator;
            return this;
        }

        public PluginMeta build() {
            return new PluginMeta(name, minVersion, paperState, spigotState, economyState, permissionsState, spigotMCResourceId, bStatsResourceId, versionComparator);
        }
    }

    private String name;
    private Version minVersion;
    private State paperState;
    private State spigotState;
    private State economyState;
    private State permissionsState;
    private int spigotMCResourceId;
    private int bStatsResourceId;
    private VersionComparator versionComparator;

    private PluginMeta(String name, Version minVersion, State paperState, State spigotState, State economyState, State permissionsState,
            int spigotMCResourceId, int bStatsResourceId, VersionComparator versionComparator) {
        this.name = name;
        this.minVersion = minVersion;
        this.paperState = paperState;
        this.spigotState = spigotState;
        this.economyState = economyState;
        this.permissionsState = permissionsState;
        this.spigotMCResourceId = spigotMCResourceId;
        this.bStatsResourceId = bStatsResourceId;
        this.versionComparator = versionComparator;
    }

    /**
     * Returns the name of the plugin.
     *
     * @return the name of the plugin
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the minimum required version to run this plugin.
     *
     * @return the minimum required version to run this plugin
     */
    public Version getMinVersion() {
        return minVersion;
    }

    /**
     * Returns the {@link State} of Paper support of this plugin.
     *
     * @return the {@link State} of Paper support of this plugin
     */
    public State getPaperState() {
        return paperState;
    }

    /**
     * Returns the {@link State} of Spigot support of this plugin.
     *
     * @return the {@link State} of Spigot support of this plugin
     */
    public State getSpigotState() {
        return spigotState;
    }

    /**
     * Returns the {@link State} of Vault economy support of this plugin.
     *
     * @return the {@link State} of Vault economy support of this plugin
     */
    public State getEconomyState() {
        return economyState;
    }

    /**
     * Returns the {@link State} of Vault permission support of this plugin.
     *
     * @return the {@link State} of Vault permission support of this plugin
     */
    public State getPermissionsState() {
        return permissionsState;
    }

    /**
     * Returns if the plugin is published on SpigotMC.org.
     *
     * @return if the plugin is published on SpigotMC.org
     */
    public boolean isSpigotMCResource() {
        return spigotMCResourceId != -1;
    }

    /**
     * Returns the SpigotMC.org resource ID or -1 if the resource isn't published there.
     *
     * @return the SpigotMC.org resource ID or -1 if the resource isn't published there
     */
    public int getSpigotMCResourceId() {
        return spigotMCResourceId;
    }

    /**
     * Returns if the plugin uses bStats Metrics.
     *
     * @return if the plugin uses bStats Metrics
     */
    public boolean isMetricsEnabled() {
        return bStatsResourceId != -1;
    }

    /**
     * Returns the bStats.org resource ID or -1 if the resource isn't published there.
     *
     * @return the bStats.org resource ID or -1 if the resource isn't published there
     */
    public int getBStatsResourceId() {
        return bStatsResourceId;
    }

    /**
     * Returns the Spiget {@link VersionComparator} used to check if this resource is up to date.
     *
     * @return the Spiget {@link VersionComparator} used to check if this resource is up to date
     */
    public VersionComparator getVersionComparator() {
        return versionComparator;
    }

    private static final String BSTATS_LINK = "https://bstats.org/plugin/bukkit/";
    private static final String SPIGOT_LINK = "https://www.spigotmc.org/resources/";

    public void printToConsole(XLib xlib) {
        String economyProvider = null;
        String permissionsProvider = null;
        if (xlib.isVaultEnabled()) {
            economyProvider = xlib.getEconomyProvider() != null ? xlib.getEconomyProvider().getName() : null;
            permissionsProvider = xlib.getPermissionProvider() != null ? xlib.getEconomyProvider().getName() : null;
        }

        String header = "&f[&9##########&f[&6" + name + "&f]&9##########&f]";
        MessageUtil.log(header);

        String server;
        if (RuntimeType.get().hasTrait(RuntimeTrait.PAPER_API)) {
            server = checkServer(paperState) + "Paper";
        } else {
            server = checkServer(spigotState) + "Spigot";
        }
        MessageUtil.log("&fServer: [" + server + "&f]");

        String version = (Version.isAtLeast(minVersion) ? "&a" : "&4") + Version.get();
        if (!Version.isAtMost(Version.values()[Version.values().length - 2])) {
            version += " &4(possibly works through forward compatibility)";
        }
        MessageUtil.log("&fMinecraft version: [" + version + "&f]");

        String economy = "";
        if (economyState == REQUIRED) {
            economy = economyProvider != null ? "&a" : "&4";
        } else if (economyState == SUPPORTED) {
            economy = "&a";
        }
        if (economyProvider == null) {
            economyProvider = "&aNone";
        }
        economy += economyProvider;
        MessageUtil.log("&fEconomy: [" + economy + "&f]");

        String permissions = "";
        if (permissionsState == REQUIRED) {
            permissions = permissionsProvider != null ? "&a" : "&4";
        } else if (permissionsState == SUPPORTED) {
            permissions = "&a";
        }
        if (permissionsProvider == null) {
            permissionsProvider = "&aNone";
        }
        permissions += permissionsProvider;
        MessageUtil.log("&fPermissions: [" + permissions + "&f]");

        String bStatsLink = isMetricsEnabled() ? BSTATS_LINK + name + "/" + bStatsResourceId : "N/A";
        MessageUtil.log("&fMetrics: [&e" + bStatsLink + "&f]");

        String spigotLink = isSpigotMCResource() ? SPIGOT_LINK + name.toLowerCase() + "." + spigotMCResourceId : "N/A";
        MessageUtil.log("&fSpigotMC ID: [&e" + spigotLink + "&f]");

        StringBuilder builder = new StringBuilder("&f[&9");
        for (int i = header.length() - 16; i > 0; i--) {
            builder.append('#');
        }
        builder.append("&f]");
        MessageUtil.log(builder.toString());
    }

    private String checkServer(State state) {
        String server = "";
        switch (state) {
            case REQUIRED:
            case SUPPORTED:
                server = "&a";
                break;
            case NOT_SUPPORTED:
                server = "&4";
                break;
        }
        return server;
    }

}
