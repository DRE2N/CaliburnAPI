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

import org.bukkit.Bukkit;

/**
 * This enumeration represents the Minecraft version and is supposed not to be implementation specific.
 *
 * @author Daniel Saukel
 */
public enum Version {

    MC1_8_8(false, false, "v1_8_R3"),
    MC1_9(false, false, "v1_9_R1"),
    MC1_9_2(false, false, "v1_9_R1"),
    MC1_9_4(false, false, "v1_9_R2"),
    MC1_10(false, false, "v1_10_R1"),
    MC1_10_1(false, false, "UNKNOWN"),
    MC1_10_2(false, false, "v1_10_R1"),
    MC1_11(false, "v1_11_R1"),
    MC1_11_1(false, "v1_11_R1"),
    MC1_11_2(false, "v1_11_R1"),
    MC1_12(false, "v1_12_R1"),
    MC1_12_1(false, "v1_12_R1"),
    MC1_12_2(false, "v1_12_R1"),
    MC1_13("v1_13_R1"),
    MC1_13_1("v1_13_R2"),
    MC1_13_2("v1_13_R2"),
    MC1_14("v1_14_R1"),
    MC1_14_1("v1_14_R1"),
    MC1_14_2("v1_14_R1"),
    MC1_14_3("v1_14_R1"),
    MC1_14_4("v1_14_R1"),
    MC1_15("v1_15_R1"),
    MC1_15_1("v1_15_R1"),
    MC1_15_2("v1_15_R1"),
    MC1_16("v1_16_R1"),
    MC1_16_1("v1_16_R1"),
    MC1_16_2("v1_16_R2"),
    MC1_16_3("v1_16_R2"),
    MC1_16_4("v1_16_R3"),
    MC1_16_5("v1_16_R3"),
    MC1_17("v1_17_R1"),
    MC1_17_1("v1_17_R1"),
    MC1_18("v1_18_R1"),
    MC1_18_1("v1_18_R1"),
    MC1_18_2("v1_18_R1"),
    MC1_19("v1_19_R1"),
    MC1_19_1("v1_19_R1"),
    MC1_19_2("v1_19_R1"),
    MC1_19_3("v1_19_R2"),
    MC1_19_4("v1_19_R3"),
    MC1_20("v1_20_R1"),
    MC1_20_1("v1_20_R1"),
    MC1_20_2("v1_20_R2"),
    MC1_20_3("v1_20_R3"),
    MC1_20_4("v1_20_R3"),
    MC1_20_5("v1_20_R4"),
    MC1_20_6("v1_20_R4"),
    MC1_21("v1_21_R1"),
    MC1_21_1("v1_21_R1"),
    MC1_21_2("v1_21_R2"),
    MC1_21_3("v1_21_R2"),
    MC1_21_4("v1_21_R3"),
    MC1_21_5("v1_21_R4"),
    MC1_21_6("v1_21_R5"),
    MC1_21_7("v1_21_R5"),
    MC1_21_8("v1_21_R5"),
    MC1_21_9("v1_21_R6"),
    MC1_21_10("v1_21_R6"),
    MC1_21_11("v1_21_R7"),
    MC26_1,
    /**
     * New versions only supported by XLib through forward compatibility mechanisms or outdated version older than 1.8.8.
     */
    UNKNOWN;

    /**
     * The latest version supported by XLib without forward compatibility mechanisms.
     */
    public static final Version LATEST_SUPPORTED = Version.values()[Version.values().length - 2];

    private boolean newMobNames;
    private boolean newMaterials;
    private String relocationTarget;

    Version() {
        this("");
    }

    Version(String relocationTarget) {
        this(true, relocationTarget);
    }

    Version(boolean newMaterials, String relocationTarget) {
        this(true, newMaterials, relocationTarget);
    }

    Version(boolean newMobNames, boolean newMaterials, String relocationTarget) {
        this.newMobNames = newMobNames;
        this.newMaterials = newMaterials;
        this.relocationTarget = relocationTarget;
    }

    /**
     * Returns if this version uses the mob String IDs introduced in Minecraft 1.11
     *
     * @return if this version uses the mob String IDs introduced in Minecraft 1.11
     */
    public boolean useNewMobNames() {
        return newMobNames;
    }

    /**
     * Returns if this version uses the material String IDs introduced in Minecraft 1.13
     *
     * @return if this version uses the material String IDs introduced in Minecraft 1.13
     */
    public boolean useNewMaterials() {
        return newMaterials;
    }

    /**
     * Returns the package version that CraftBukkit uses for this Minecraft version.
     *
     * @return the package version that CraftBukkit uses for this Minecraft version
     */
    public String getRelocationTarget() {
        return relocationTarget;
    }

    @Override
    public String toString() {
        String[] string = super.toString().replace("_", ".").split("MC");

        if (string.length == 2) {
            return string[1];

        } else {
            return string[0];
        }
    }

    /* Statics */
    private static Version running;

    /**
     * Returns the version the server is running.
     *
     * @return the version the server is running
     */
    public static Version get() {
        if (running == null) {
            try {
                String versionString = Bukkit.getBukkitVersion().split("-")[0];
                running = Version.valueOf("MC" + versionString.replace(".", "_"));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException exception) {
                running = UNKNOWN;
            }
        }
        return running;
    }

    /**
     * Returns if the environment version is equal to or higher than the provided version
     *
     * @param min the version the running server's has to be higher than or equal to
     * @return if the environment version is equal to or higher than the provided version
     */
    public static boolean isAtLeast(Version min) {
        return get().compareTo(min) >= 0;
    }

    /**
     * Returns if the environment version is equal to or lower than the provided version
     *
     * @param max the version the running server's has to be lower than or equal to
     * @return if the environment version is equal to or lower than the provided version
     */
    public static boolean isAtMost(Version max) {
        return get().compareTo(max) <= 0;
    }

}
