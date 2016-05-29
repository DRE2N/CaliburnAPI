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
package io.github.dre2n.caliburn;

import io.github.dre2n.caliburn.item.ItemCategories;
import io.github.dre2n.caliburn.item.Items;
import io.github.dre2n.caliburn.listener.EntityListener;
import io.github.dre2n.caliburn.mob.MobCategories;
import io.github.dre2n.caliburn.mob.Mobs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * @author Daniel Saukel
 */
public class CaliburnAPI {

    public static String IDENTIFIER_PREFIX = "\u00a70\u00a7k";

    private Items items;
    private Mobs mobs;
    private ItemCategories itemCategories;
    private MobCategories mobCategories;

    public CaliburnAPI(Plugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new EntityListener(this), plugin);
    }

    /* Getters and setters */
    /**
     * @return
     * the loaded instance of ItemCategories
     */
    public ItemCategories getItemCategories() {
        return itemCategories;
    }

    /**
     * @return
     * the loaded instance of MobCategories
     */
    public MobCategories getMobCategories() {
        return mobCategories;
    }

    /**
     * @return
     * the loaded instance of Items
     */
    public Items getItems() {
        return items;
    }

    /**
     * @return
     * the loaded instance of Mobs
     */
    public Mobs getMobs() {
        return mobs;
    }

    /* Actions */
    /**
     * Finish initialization of the Object.
     */
    public void setup(Items items, Mobs mobs, ItemCategories itemCategories, MobCategories mobCategories) {
        this.items = items;
        this.mobs = mobs;
        this.itemCategories = itemCategories;
        this.mobCategories = mobCategories;

        this.items.setup();
        this.mobs.setup();
        this.itemCategories.setup();
        this.mobCategories.setup();
    }

}
