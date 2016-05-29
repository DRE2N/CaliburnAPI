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
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

/**
 * @author Daniel Saukel
 */
public class Mobs {

    CaliburnAPI api;

    private List<UniversalMob> mobs = new ArrayList<>();

    public Mobs(CaliburnAPI api) {
        this.api = api;
    }

    public void setup() {
        for (UniversalMob mob : mobs) {
            mob.setup();
        }
    }

    /**
     * @param id
     * a CustomMob or UniversalMob ID
     * @return
     * the mob that has the ID
     */
    public UniversalMob getById(String id) {
        for (UniversalMob mob : mobs) {
            if (mob.getId().equals(id)) {
                return mob;
            }
        }

        return null;
    }

    /**
     * @param entity
     * an instance of org.bukkit.entity.Entity
     * @return
     * the mob that has the custom name of the Entity
     */
    public UniversalMob getByEntity(Entity entity) {
        for (UniversalMob mob : mobs) {
            if (mob.getName().equals(entity.getCustomName())) {
                return mob;
            }
        }

        return null;
    }

    /**
     * @return the mobs
     */
    public List<UniversalMob> getMobs() {
        return mobs;
    }

    /**
     * @param type
     * All mobs which are an instance of it will be returned.
     */
    public List<UniversalMob> getMobs(Class<? extends UniversalMob> type) {
        List<UniversalMob> mobsOfType = new ArrayList<>();
        for (UniversalMob mob : mobs) {
            if (mob.getClass() == type) {
                mobsOfType.add(mob);
            }
        }
        return mobsOfType;
    }

    /**
     * @param mob
     * the mob to add
     */
    public void addMob(UniversalMob mob) {
        mobs.add(mob);
    }

    /**
     * @param mob
     * the mob to remove
     */
    public void removeMob(UniversalMob mob) {
        mobs.remove(mob);
    }

    /**
     * @param config
     * a configuration section
     * @param amount
     * the item stack size
     * @return
     * an ItemStack with the values from config
     */
    public static Entity deserialize(ConfigurationSection config, Location location) {
        return new CustomMob(null, "caliburn", config).toEntity(location);
    }

}
