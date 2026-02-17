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
package de.erethon.caliburn.mob.actionhandler;

import org.bukkit.entity.Entity;

/**
 * Fired when the mob attacks another mob.
 *
 * @author Daniel Saukel
 */
@FunctionalInterface
public interface AttackHandler {

    /**
     * Instantiates a handler through reflection.
     *
     * @param className the name of the class
     * @return the handler instance
     */
    static AttackHandler create(String className) {
        try {
            Class cl = Class.forName(className);
            if (cl != null && AttackHandler.class.isAssignableFrom(cl)) {
                return (AttackHandler) cl.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
        }
        return null;
    }

    /**
     * @param entityInstance the entity involved in this action
     * @param attacked       the attacked entity
     */
    void onAttack(Entity entityInstance, Entity attacked);

}
