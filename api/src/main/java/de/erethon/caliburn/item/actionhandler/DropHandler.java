/*
 * Copyright (C) 2015-2021 Daniel Saukel.
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
package de.erethon.caliburn.item.actionhandler;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Fired when the item is dropped.
 *
 * @author Daniel Saukel
 */
@FunctionalInterface
public interface DropHandler {

    /**
     * Instantiates a handler through reflection.
     *
     * @param className the name of the class
     * @return the handler instance
     */
    static DropHandler create(String className) {
        try {
            Class cl = Class.forName(className);
            if (cl != null && DropHandler.class.isAssignableFrom(cl)) {
                return (DropHandler) cl.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
        }
        return null;
    }

    /**
     * @param itemInstance the ItemStack involved in this action
     * @param dropped      the dropped item entity
     * @param player       the player who holds the item
     */
    void onDrop(ItemStack itemInstance, Item dropped, Player player);

}
