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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This enumeration represents package versions.
 *
 * @author Daniel Saukel
 */
public enum Internals {

    /**
     * Represents Paper versions as of Minecraft 1.21.5.
     * <br>
     * This is the default version.
     */
    PAPER(false, false),
    SPIGOT_v1_21_R7(false, true),
    SPIGOT_v1_21_R6(false, true),
    SPIGOT_v1_21_R5(false, true),
    SPIGOT_v1_21_R4(false, true),
    v1_21_R3(false, true),// PAPER / SPIGOT DEVIDE
    v1_21_R2(false, true),
    v1_21_R1(false, true),
    v1_20_R4(false, true),
    v1_20_R3(false, true),
    v1_20_R2(false, true),
    v1_20_R1(false, true),
    v1_19_R3(false, true),
    v1_19_R2(false, true),
    v1_19_R1(false, true),
    v1_18_R1(false, true),
    v1_17_R1(false, true),// No more relocated NMS
    v1_16_R3(true, true),
    v1_16_R2(true, true),
    v1_16_R1(true, true),
    v1_15_R1(true, true),
    v1_14_R1(true, true),
    v1_13_R2(true, true),
    v1_13_R1(true, true),
    v1_12_R1(true, true),
    v1_11_R1(true, true),
    v1_10_R1(true, true),
    v1_9_R2(true, true),
    v1_9_R1(true, true),
    v1_8_R3(true, true),
    v1_8_R2(true, true),
    v1_8_R1(true, true),
    v1_7_R4(true, true),
    v1_7_R3(true, true),
    v1_7_R2(true, true),
    v1_7_R1(true, true),
    v1_6_R3(true, true),
    v1_6_R2(true, true),
    v1_6_R1(true, true),
    v1_5_R3(true, true),
    v1_5_R2(true, true),
    v1_5_R1(true, true),
    v1_4_R1(true, true),
    /**
     * Represents internals that are older than CraftBukkit 1.4.7, which introduced the current package version system.
     */
    OUTDATED(false, false),
    /**
     * Represents an implementation other than CraftBukkit.
     */
    UNKNOWN(false, false);

    private boolean nmsRelocations;
    private boolean obcRelocations;

    Internals(boolean nmsRelocations, boolean obcRelocations) {
        this.nmsRelocations = nmsRelocations;
        this.obcRelocations = obcRelocations;
    }

    /**
     * Returns true if net.minecraft.server packages are relocated to net.minecraft.server.{@link #toString()} on this server, false if not.
     *
     * @return true if net.minecraft.server packages are relocated to net.minecraft.server.{@link #toString()} on this server, false if not.
     */
    public boolean hasNMSRelocations() {
        return nmsRelocations;
    }

    /**
     * Returns true if org.bukkit.craftbukkit packages are relocated to org.bukkit.craftbukkit.{@link #toString()} on this server, false if not.
     *
     * @return true if org.bukkit.craftbukkit packages are relocated to org.bukkit.craftbukkit.{@link #toString()} on this server, false if not.
     */
    public boolean hasOBCRelocations() {
        return obcRelocations;
    }

    @Override
    public String toString() {
        if (name().startsWith("SPIGOT_")) {
            return name().replace("SPIGOT_", "");
        } else {
            return name();
        }
    }

    /* Statics */
    /**
     * Contains all values of this enumeration.
     */
    public static final Set<Internals> INDEPENDENT = new HashSet<>(Arrays.asList(Internals.values()));

    /**
     * Returns a Set of all internals that are equal to or higher than the environment version
     *
     * @param internals the oldest internals in the Set
     * @return a Set of all internals that are equal to or higher than the environment version
     */
    public static Set<Internals> andHigher(Internals internals) {
        Set<Internals> andHigher = new HashSet<>();
        Internals[] values = values();
        for (int i = 0; i <= internals.ordinal(); i++) {
            andHigher.add(values[i]);
        }
        return andHigher;
    }

    /**
     * Returns if the environment version is equal to or higher than the provided internals version
     *
     * @param internals the minimum internals version to check
     * @return if the environment version is equal to or higher than the provided internals version
     */
    public static boolean isAtLeast(Internals internals) {
        return andHigher(internals).contains(CompatibilityHandler.getInstance().getInternals());
    }

    /**
     * Returns if the environment version is equal to or lower than the provided internals version
     *
     * @param internals the maximum internals to check
     * @return if the environment version is equal to or lower than the internals version
     */
    public static boolean isAtMost(Internals internals) {
        return internals == CompatibilityHandler.getInstance().getInternals() || !isAtLeast(internals);
    }

}
