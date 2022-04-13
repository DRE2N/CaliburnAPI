/*
 * Copyright (C) 2015-2022 Daniel Saukel.
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

import de.erethon.caliburn.item.CustomAttribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Fired when something is hit with the item.
 *
 * @author Daniel Saukel
 */
@FunctionalInterface
public interface HitHandler {

    /**
     * Instantiates a handler through reflection.
     *
     * @param className the name of the class
     * @return the handler instance
     */
    static HitHandler create(String className) {
        try {
            Class cl = Class.forName(className);
            if (cl != null && HitHandler.class.isAssignableFrom(cl)) {
                return (HitHandler) cl.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
        }
        return null;
    }

    /**
     * @param attributeInstance the attribute instance that sets the handler
     * @param weapon            the ItemStack involved in this action
     * @param damager           the player who holds the item
     * @param event             the underlying Bukkit event
     */
    void onHit(CustomAttribute.Instance attributeInstance, ItemStack weapon, Player damager, EntityDamageByEntityEvent event);

}
