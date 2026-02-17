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
package de.erethon.xlib.mob.actionhandler;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Fired when a player clicks on the mob.
 *
 * @author Daniel Saukel
 */
@FunctionalInterface
public interface InteractHandler {

    /**
     * Instantiates a handler through reflection.
     *
     * @param className the name of the class
     * @return the handler instance
     */
    static InteractHandler create(String className) {
        try {
            Class cl = Class.forName(className);
            if (cl != null && InteractHandler.class.isAssignableFrom(cl)) {
                return (InteractHandler) cl.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
        }
        return null;
    }

    /**
     * @param entityInstance the entity involved in this action
     * @param player         the player who interacted with the mob
     */
    void onInteract(Entity entityInstance, Player player);

}
