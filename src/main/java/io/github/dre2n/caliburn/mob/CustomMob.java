/*
 * Copyright (C) 2015-2016 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.caliburn.mob;

import io.github.dre2n.caliburn.CaliburnAPI;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * @author Daniel Saukel
 */
public class CustomMob extends UniversalMob {

    private String name;

    public CustomMob(CaliburnAPI api, String id, EntityType type) {
        super(api, id, type);
    }

    public CustomMob(CaliburnAPI api, String id, ConfigurationSection config) {
        super(api, id, config);

        if (config.contains("name")) {
            name = config.getString("name");
        }
    }

    /* Getters and setters */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /* Actions */
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
