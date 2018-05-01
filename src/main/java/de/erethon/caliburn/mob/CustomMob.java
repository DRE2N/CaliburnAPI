/*
 * Copyright (C) 2015-2018 Daniel Saukel.
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
package de.erethon.caliburn.mob;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * @author Daniel Saukel
 */
public class CustomMob extends ExMob {

    private String name;

    public CustomMob(Map<String, Object> args) {
        raw = args;
        name = (String) args.get("name");
    }

    public CustomMob(String id, EntityType type) {
        this.id = id;
        species = type;
    }

    /* Getters and setters */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name
     * the custom name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /* Actions */
    @Override
    public Map<String, Object> serialize() {
        if (raw != null) {
            return new HashMap<>(raw);
        }
        Map<String, Object> config = new HashMap<>();
        config.put("species", species.name());
        config.put("name", name);
        return config;
    }

    @Override
    public Entity toEntity(Location location) {
        Entity entity = location.getWorld().spawnEntity(location, species);

        if (name != null) {
            entity.setCustomName(name);
            entity.setCustomNameVisible(true);
        }

        return entity;
    }

}
