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
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author Daniel Saukel
 */
public class MobCategories {

    private ConfigurationSection config;
    private List<MobCategory> mobCategories = new ArrayList<>();

    public MobCategories(CaliburnAPI api, ConfigurationSection config) {
        this.config = config;

        Map<String, Object> mobCategories = config.getValues(false);
        for (Entry<String, Object> mobCategory : mobCategories.entrySet()) {
            this.mobCategories.add(new MobCategory(api, mobCategory.getKey()));
        }
    }

    public void setup() {
        for (MobCategory mobCategory : mobCategories) {
            mobCategory.setup(config.getStringList(mobCategory.getId()));
        }
    }

    /**
     * @return
     * the MobCategory that has the ID
     */
    public MobCategory getById(String id) {
        for (MobCategory mobCategory : mobCategories) {
            if (mobCategory.getId().equals(id)) {
                return mobCategory;
            }
        }
        return null;
    }

    /**
     * @return
     * the categories of the mob
     */
    public List<MobCategory> getByMob(UniversalMob mob) {
        ArrayList<MobCategory> categories = new ArrayList<>();
        for (MobCategory mobCategory : mobCategories) {
            if (mobCategory.getMobs().contains(mob)) {
                categories.add(mobCategory);
            }
        }
        return categories;
    }

    /**
     * @return
     * the MobCategories
     */
    public List<MobCategory> getMobCategories() {
        return mobCategories;
    }

    /**
     * @param mobCategory
     * the MobCategory to add
     */
    public void addMobCategory(MobCategory mobCategory) {
        mobCategories.add(mobCategory);
    }

    /**
     * @param mobCategory
     * the MobCategory to remove
     */
    public void removeMobCategory(MobCategory mobCategory) {
        mobCategories.remove(mobCategory);
    }

}
