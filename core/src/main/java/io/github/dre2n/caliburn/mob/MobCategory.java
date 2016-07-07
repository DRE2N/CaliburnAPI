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

/**
 * @author Daniel Saukel
 */
public class MobCategory {

    CaliburnAPI api;

    private String id;

    private List<UniversalMob> mobs = new ArrayList<>();

    public MobCategory(CaliburnAPI api, String id) {
        this.api = api;
        this.id = id;
    }

    public void setup(List<String> mobs) {
        for (String mob : mobs) {
            this.mobs.add(api.getMobs().getById(mob));
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the mobs
     */
    public List<UniversalMob> getMobs() {
        return mobs;
    }

    /**
     * @param mobs
     * the mobs to set
     */
    public void addMob(UniversalMob mob) {
        mobs.add(mob);
    }

}
