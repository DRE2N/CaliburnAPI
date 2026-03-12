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
 * @author Daniel Saukel
 */
public enum RuntimeTrait {
    /**
     * net.minecraft.server packages of this server are relocated to net.minecraft.server.(package version).
     */
    NMS_RELOCATIONS,
    /**
     * org.bukkit.craftbukkit packages of this server are relocated to org.bukkit.craftbukkit.(package version).
     */
    OBC_RELOCATIONS,
    /**
     * At runtime, this implementation is based on the obfuscated Mojang server with remappings done by SpigotMC.
     */
    MAPPINGS_CRAFTBUKKIT,
    /**
     * At runtime, this implementation is based on the Mojang server with official Mojang mappings or no obfuscation.
     */
    MAPPINGS_MOJANG,
    /**
     * The server implements the Paper API.
     */
    PAPER_API
}
