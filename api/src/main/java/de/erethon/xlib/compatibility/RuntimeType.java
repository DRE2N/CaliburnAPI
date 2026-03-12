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

import static de.erethon.xlib.compatibility.RuntimeTrait.*;

/**
 * Gives an overview over the specifics of the environment the plugin is running in.
 *
 * @author Daniel Saukel
 */
public enum RuntimeType {

    /**
     * The base Spigot and Paper shared prior to Paper's hard fork (1.21.4).
     */
    PRE_1_17(NMS_RELOCATIONS, OBC_RELOCATIONS, MAPPINGS_CRAFTBUKKIT),
    /**
     * Spigot 1.17-1.21.11, Paper 1.17-1.21.4.
     */
    SPECIAL_SOURCE(OBC_RELOCATIONS, MAPPINGS_CRAFTBUKKIT),
    /**
     * Spigot 26.1 and higher.
     *
     * @deprecated future version
     */
    @Deprecated
    SPIGOT(OBC_RELOCATIONS, MAPPINGS_MOJANG),
    /**
     * Paper 1.20.5 and higher.
     */
    PAPER(PAPER_API, MAPPINGS_MOJANG);

    private static RuntimeType running;

    private RuntimeTrait[] traits;

    RuntimeType(RuntimeTrait... traits) {
        this.traits = traits;
    }

    /**
     * Returns if the server running has the given trait.
     *
     * @param trait a trait
     * @return if the server running has the given trait
     */
    public boolean hasTrait(RuntimeTrait trait) {
        for (RuntimeTrait t : traits) {
            if (t == trait) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the type of the server running.
     *
     * @return the type of the server running
     */
    public static RuntimeType get() {
        if (running == null) {
            if (Version.isAtMost(Version.MC1_16_5)) {
                running = PRE_1_17;
            } else if (Version.isAtMost(Version.MC1_21_4)) {
                running = SPECIAL_SOURCE;
            } else {
                try {
                    Class.forName("io.papermc.paper.ServerBuildInfo");
                    running = PAPER;
                } catch (ClassNotFoundException exception) {
                    running = Version.isAtMost(Version.MC1_21_11) ? SPECIAL_SOURCE : SPIGOT;
                }
            }
        }
        return running;
    }

}
