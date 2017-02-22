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
package io.github.dre2n.caliburn;

import io.github.dre2n.caliburn.item.ItemCategories;
import io.github.dre2n.caliburn.item.Items;
import io.github.dre2n.caliburn.item.UniversalItem;
import io.github.dre2n.caliburn.item.UniversalItemStack;
import io.github.dre2n.caliburn.listener.EntityListener;
import io.github.dre2n.caliburn.mob.MobCategories;
import io.github.dre2n.caliburn.mob.Mobs;
import io.github.dre2n.caliburn.mob.UniversalMob;
import io.github.dre2n.caliburn.util.CaliConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

/**
 * @author Daniel Saukel
 */
public class CaliburnAPI {

    public static String IDENTIFIER_PREFIX = "\u00a70\u00a7k";

    private static CaliburnAPI instance;

    private Items items;
    private Mobs mobs;
    private ItemCategories itemCategories;
    private MobCategories mobCategories;

    private CaliburnAPI() {
        instance = this;

        ConfigurationSerialization.registerClass(UniversalItemStack.class);
        ConfigurationSerialization.registerClass(UniversalItem.class);
        ConfigurationSerialization.registerClass(UniversalMob.class);
    }

    public CaliburnAPI(Plugin plugin) {
        this();
        Bukkit.getServer().getPluginManager().registerEvents(new EntityListener(this), plugin);
    }

    /* Getters and setters */
    public static CaliburnAPI getInstance() {
        return instance;
    }

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

    /**
     * Finish initialization of the Object with default values.
     */
    public void setupClean() {
        ConfigurationSection placeholder = new CaliConfiguration();

        items = new Items(this);
        for (Material material : Material.values()) {
            items.addItem(new UniversalItem(this, material));
        }

        mobs = new Mobs(this);
        for (EntityType mob : EntityType.values()) {
            mobs.addMob(new UniversalMob(this, mob));
        }

        itemCategories = new ItemCategories(this, placeholder);
        mobCategories = new MobCategories(this, placeholder);
    }

}
