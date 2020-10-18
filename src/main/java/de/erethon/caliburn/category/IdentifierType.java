/*
 * Copyright (C) 2015-2020 Daniel Saukel.
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
package de.erethon.caliburn.category;

/**
 * The method used to get an identifier from an instance of a mob or item.
 *
 * @author Daniel Saukel
 */
public enum IdentifierType {

    /**
     * The visible display name.
     * <p>
     * This should not be used unless the environment guarantees it cannot be changed e.g. with anvils or name tags.
     */
    DISPLAY_NAME,
    /**
     * An item lore line.
     */
    LORE,
    /**
     * The Bukkit {@link org.bukkit.metadata.Metadatable} API.
     */
    METADATA,
    /**
     * The Bukkit {@link org.bukkit.persistence.PersistentDataContainer} API.
     */
    PERSISTENT_DATA_CONTAINER,
    /**
     * This can only represent Vanilla content and is always subsidiary.
     * <p>
     * Should not be used for custom content.
     */
    VANILLA;

    /**
     * The ID lookup priority for items starting with the highest.
     */
    public static final IdentifierType[] ITEM_PRIORITY = {PERSISTENT_DATA_CONTAINER, LORE, DISPLAY_NAME, VANILLA};

    /**
     * The ID lookup priority for mobs starting with the highest.
     */
    public static final IdentifierType[] MOB_PRIORITY = {PERSISTENT_DATA_CONTAINER, METADATA, DISPLAY_NAME, VANILLA};

}
