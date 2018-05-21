/*
 * Copyright (C) 2012-2018 Frank Baumann
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
package de.erethon.caliburn.loottable;

import de.erethon.caliburn.CaliburnAPI;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

/**
 * A loot table e.g. for mob drops.
 *
 * @author Daniel Saukel
 */
public class LootTable {

    public class Entry {

        private String id;
        private ItemStack item;
        private double chance;

        public Entry(String id, ItemStack item, double chance) {
            this.id = id;
            this.item = item;
            this.chance = chance;
        }

        /* Getters and setters */
        /**
         * @return the id of the loot table entry
         */
        public String getId() {
            return id;
        }

        /**
         * @param id
         * the id of the loot table entry to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the loot item stack
         */
        public ItemStack getLootItem() {
            return item;
        }

        /**
         * @param item
         * the loot item to set
         */
        public void setLootItem(ItemStack item) {
            this.item = item;
        }

        /**
         * @return the loot chance
         */
        public double getLootChance() {
            return chance;
        }

        /**
         * @param chance
         * the loot chance to set
         */
        public void setLootChance(double chance) {
            this.chance = chance;
        }

    }

    private String name;
    private List<Entry> entries = new ArrayList<>();

    /**
     * @param file
     * the script file
     */
    public LootTable(CaliburnAPI api, File file) {
        this(api, file.getName().substring(0, file.getName().length() - 4), YamlConfiguration.loadConfiguration(file));
    }

    /**
     * @param name
     * the name of the loot table
     * @param config
     * the config that stores the information
     */
    public LootTable(CaliburnAPI api, String name, FileConfiguration config) {
        api.getLootTables().add(this);

        this.name = name;

        for (String id : config.getKeys(false)) {
            ItemStack item = api.deserializeStack(config, id + ".item");
            if (item == null) {
                continue;
            }

            double chance = config.getDouble(id + ".chance");
            entries.add(new Entry(id, item, chance));
        }
    }

    /**
     * @param api
     * the API instance
     * @param name
     * the name of the loot table
     */
    public LootTable(CaliburnAPI api, String name) {
        api.getLootTables().add(this);
        this.name = name;
    }

    /* Getters and setters */
    /**
     * @return the name of the loot table
     */
    public String getName() {
        return name;
    }

    /**
     * @return the entries
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * @param entry
     * the entry to add
     */
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    /**
     * @param entry
     * the entry to remove
     */
    public void removeEntry(Entry entry) {
        entries.remove(entry);
    }

    /* Actions */
    /**
     * Adds loot to a list randomly based on the chance value
     *
     * @return a list of the loot
     */
    public List<ItemStack> generateLootList() {
        List<ItemStack> lootList = new ArrayList<>();
        for (Entry entry : entries) {
            if (new Random().nextInt(100) < entry.getLootChance()) {
                lootList.add(entry.getLootItem());
            }
        }
        return lootList;
    }

}
