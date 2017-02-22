/*
 * Copyright (C) 2015-2017 Daniel Saukel
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
import io.github.dre2n.caliburn.util.CaliConfiguration;
import io.github.dre2n.commons.util.EnumUtil;
import java.util.ArrayList;
import java.util.List;
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
            if (entity.getCustomName() != null) {
                if (entity.getCustomName().equals(mob.getName())) {
                    return mob;
                }

            } else if (entity.getType() == mob.getSpecies()) {
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
     * @param config
     * the CaliConfiguration which stores information about the new mob
     */
    public UniversalMob addMob(String id, CaliConfiguration config) {
        MobType type = MobType.CUSTOM_DEFAULT;
        if (config.contains("type") && EnumUtil.isValidEnum(MobType.class, config.getString("type"))) {
            type = MobType.valueOf(config.getString("type"));
        }

        UniversalMob mob = type.instantiate(api, id, config);
        if (isValid(mob)) {
            mobs.add(mob);
        }
        return mob;
    }

    /**
     * @param mob
     * the mob to remove
     */
    public void removeMob(UniversalMob mob) {
        mobs.remove(mob);
    }

    /**
     * Checks if the mob fulfills minimum requirements to be useable without throwing exceptions.
     *
     * @param mob
     * the mob to check
     *
     * @return
     * if the mob fulfills the requirements
     */
    public boolean isValid(UniversalMob mob) {
        if (mob == null) {
            return false;
        }
        if (mob.getSpecies() == null) {
            return false;
        }

        return true;
    }

}
