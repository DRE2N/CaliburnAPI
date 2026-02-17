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
package de.erethon.xlib.compatibility;

/**
 * The main class of CompatibilityHandler, mainly used for environment information.
 *
 * @author Daniel Saukel
 */
public class CompatibilityHandler {

    private static CompatibilityHandler instance;

    private Version version;
    private boolean spigot;
    private boolean paper;

    private CompatibilityHandler() {
        instance = this;

        version = Version.getByServer();
        spigot = Package.getPackage("org.spigotmc") != null;
        paper = Package.getPackage("com.destroystokyo.paper") != null;
    }

    /**
     * Creates a new instance if the statically saved instance is null
     *
     * @return the CompatibilityHandler instance
     */
    public static CompatibilityHandler getInstance() {
        if (instance == null) {
            new CompatibilityHandler();
        }

        return instance;
    }

    /**
     * Returns the Minecraft version
     *
     * @return the Minecraft version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Returns the package version of the internals
     *
     * @return the package version of the server internals
     */
    public Internals getInternals() {
        return version.getCraftBukkitInternals();
    }

    /**
     * Returns if the server software implements the Spigot API
     *
     * @return if the server software implements the Spigot API
     */
    public boolean isSpigot() {
        return spigot;
    }

    /**
     * Returns if the server software implements the PaperSpigot API
     *
     * @return if the server software implements the PaperSpigot API
     */
    public boolean isPaper() {
        return paper;
    }

}
