/*
 * Copyright (C) 2015-2020 Daniel Saukel.
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
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.compatibility.Version;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

/**
 * A loot table e.g. for mob drops.
 *
 * @author Daniel Saukel
 */
public class LootTable implements ConfigurationSerializable {

    public static final String MAIN_HAND = "mainHand";
    public static final String OFF_HAND = "offHand";
    public static final String HELMET = "helmet";
    public static final String CHESTPLATE = "chestplate";
    public static final String LEGGINGS = "leggings";
    public static final String BOOTS = "boots";

    /**
     * A loot table entry which consists of an item and a spawn chance.
     * <p>
     * The ID might be an implementation specific information about the slot or something similar.
     */
    public class Entry {

        private String id;
        private ItemStack item;
        private double chance;

        public Entry(String id, ItemStack item, double chance) {
            this.id = id;
            this.item = item;
            setLootChance(chance);
        }

        public Entry(Map<String, Object> args) {
            item = CaliburnAPI.getInstance().deserializeStack(args.get("item"));
            Object chance = args.get("chance");
            if (chance instanceof Number) {
                setLootChance(((Number) chance).doubleValue());
            }
        }

        /* Getters and setters */
        /**
         * Returns the ID of the loot table entry.
         *
         * @return the ID of the loot table entry
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the ID of the loot table entry to set
         *
         * @param id the ID of the loot table entry to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Returns the loot item stack.
         *
         * @return the loot item stack
         */
        public ItemStack getLootItem() {
            return item;
        }

        /**
         * Sets the loot item stack.
         *
         * @param item the loot item stack to set
         */
        public void setLootItem(ItemStack item) {
            this.item = item;
        }

        /**
         * Returns the loot chance (0-100).
         *
         * @return the loot chance
         */
        public double getLootChance() {
            return chance;
        }

        /**
         * Sets the loot chance.
         * <p>
         * Sets the loot chance to the highest / lowest cap if the value is over 100 / below 0.
         *
         * @param chance the loot chance to set
         */
        public void setLootChance(double chance) {
            if (chance < 0d) {
                chance = 0d;
            } else if (chance > 100d) {
                chance = 100d;
            }
            this.chance = chance;

        }

        public Map<String, Object> serialize() {
            Map<String, Object> config = new HashMap<>();
            config.put("item", item);
            config.put("chance", chance);
            return config;
        }

    }

    private String name;
    private Map<String, Entry> entries = new HashMap<>();

    public LootTable(Map<String, Object> args) {
        for (Map.Entry<String, Object> mapEntry : args.entrySet()) {
            if (mapEntry.getKey().equals("==")) {
                continue;
            }
            try {
                Entry entry = new Entry((Map<String, Object>) mapEntry.getValue());
                entry.setId(mapEntry.getKey());
                entries.put(mapEntry.getKey(), entry);
            } catch (ClassCastException exception) {
                MessageUtil.log(ChatColor.RED + "Skipping erroneous loot table entry \"" + mapEntry.getKey() + "\".");
            }
        }
    }

    /**
     * Initializes a new loot table with the given name.
     *
     * @param api  the API instance
     * @param name the name of the loot table
     */
    public LootTable(CaliburnAPI api, String name) {
        api.getLootTables().add(this);
        this.name = name;
    }

    /* Getters and setters */
    /**
     * Returns the name of the loot table.
     *
     * @return the name of the loot table
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the loot table.Fails if an name has already been set. Intended to be used with a deserialization constructor.
     *
     * @param name the name to set
     * @return this object
     */
    public LootTable name(String name) {
        if (this.name == null) {
            this.name = name;
        }
        return this;
    }

    /**
     * Returns a Collection of the loot table entries.
     *
     * @return the entries
     */
    public Collection<Entry> getEntries() {
        return entries.values();
    }

    /**
     * Returns the entry with the given ID.
     *
     * @param id the entry ID
     * @return the entry with the given ID.
     */
    public Entry getEntry(String id) {
        return entries.get(id);
    }

    /**
     * Returns the entry with the given ID or creates a new one if none exists.
     *
     * @param id the entry ID
     * @return the entry with the given ID or creates a new one if none exists.
     */
    public Entry getOrCreateEntry(String id) {
        Entry entry = entries.get(id);
        if (entry != null) {
            return entry;
        }
        entry = new Entry(id, null, 100d);
        entries.put(id, entry);
        return entry;
    }

    /**
     * Adds an entry.
     *
     * @param entry the entry to add
     */
    public void addEntry(Entry entry) {
        if (entry == null) {
            return;
        }
        entries.put(entry.getId(), entry);
    }

    /**
     * Removes an entry.
     *
     * @param entry the entry to remove
     */
    public void removeEntry(Entry entry) {
        if (entry == null) {
            return;
        }
        entries.remove(entry.getId());
    }

    /**
     * Fills the loot table with items from the entity equipment.
     *
     * @param entityEquip the instance of EntityEquipment to fill from
     */
    public void readEntityEquipment(EntityEquipment entityEquip) {
        if (Version.isAtLeast(Version.MC1_9)) {
            if (entityEquip.getItemInMainHand() != null) {
                Entry mainHand = getOrCreateEntry(MAIN_HAND);
                mainHand.setLootItem(entityEquip.getItemInMainHand());
                mainHand.setLootChance(entityEquip.getItemInMainHandDropChance() * 100d);
            }
            if (entityEquip.getItemInOffHand() != null) {
                Entry offHand = getOrCreateEntry(OFF_HAND);
                offHand.setLootItem(entityEquip.getItemInOffHand());
                offHand.setLootChance(entityEquip.getItemInMainHandDropChance() * 100d);
            }
        } else if (entityEquip.getItemInMainHand() != null) {
            Entry mainHand = getOrCreateEntry(MAIN_HAND);
            mainHand.setLootItem(entityEquip.getItemInHand());
            mainHand.setLootChance(entityEquip.getItemInHandDropChance() * 100d);
        }

        if (entityEquip.getHelmet() != null) {
            Entry helmet = getOrCreateEntry(HELMET);
            helmet.setLootItem(entityEquip.getHelmet());
            helmet.setLootChance(entityEquip.getHelmetDropChance() * 100d);
        }

        if (entityEquip.getChestplate() != null) {
            Entry chestplate = getEntry(CHESTPLATE);
            chestplate.setLootItem(entityEquip.getChestplate());
            chestplate.setLootChance(entityEquip.getChestplateDropChance() * 100d);
        }

        if (entityEquip.getLeggings() != null) {
            Entry leggings = getEntry(LEGGINGS);
            leggings.setLootItem(entityEquip.getLeggings());
            leggings.setLootChance(entityEquip.getLeggingsDropChance() * 100d);
        }

        if (entityEquip.getBoots() != null) {
            Entry boots = getEntry(BOOTS);
            boots.setLootItem(entityEquip.getBoots());
            boots.setLootChance(entityEquip.getBootsDropChance() * 100d);
        }
    }

    /**
     * Overrides the values of the given instance of EntityEquipment.
     * <p>
     * Values are taken from the entries with the IDs specified in the constants in this class.
     * <p>
     * These are: "mainHand", "offHand", "helmet", "chestplate", "leggings" and "boots".
     *
     * @param entityEquip the instance of EntityEquipment to override
     */
    public void setEntityEquipment(EntityEquipment entityEquip) {
        Entry mainHand = getEntry(MAIN_HAND);
        Entry offHand = getEntry(OFF_HAND);
        if (Version.isAtLeast(Version.MC1_9)) {
            entityEquip.setItemInMainHand(mainHand.getLootItem());
            entityEquip.setItemInMainHandDropChance((float) (mainHand.getLootChance() / 100d));
            entityEquip.setItemInOffHand(offHand.getLootItem());
            entityEquip.setItemInOffHandDropChance((float) (mainHand.getLootChance() / 100d));
        } else {
            entityEquip.setItemInHand(mainHand.getLootItem());
            entityEquip.setItemInHandDropChance((float) (mainHand.getLootChance() / 100d));
        }

        Entry helmet = getEntry(HELMET);
        if (helmet != null) {
            entityEquip.setHelmet(helmet.getLootItem());
            entityEquip.setHelmetDropChance((float) (helmet.getLootChance() / 100d));
        }

        Entry chestplate = getEntry(CHESTPLATE);
        if (chestplate != null) {
            entityEquip.setHelmet(chestplate.getLootItem());
            entityEquip.setHelmetDropChance((float) (chestplate.getLootChance() / 100d));
        }

        Entry leggings = getEntry(LEGGINGS);
        if (leggings != null) {
            entityEquip.setHelmet(leggings.getLootItem());
            entityEquip.setHelmetDropChance((float) (leggings.getLootChance() / 100d));
        }

        Entry boots = getEntry(BOOTS);
        if (boots != null) {
            entityEquip.setHelmet(boots.getLootItem());
            entityEquip.setHelmetDropChance((float) (boots.getLootChance() / 100d));
        }
    }

    /* Actions */
    /**
     * Adds loot to a list randomly based on the chance value
     *
     * @return a list of the loot
     */
    public List<ItemStack> generateLootList() {
        List<ItemStack> lootList = new ArrayList<>();
        for (Entry entry : entries.values()) {
            if (new Random().nextInt(100) < entry.getLootChance()) {
                lootList.add(entry.getLootItem());
            }
        }
        return lootList;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> config = new HashMap<>();
        entries.values().forEach(e -> config.put(e.getId(), e.serialize()));
        return config;
    }

    @Override
    public String toString() {
        return "LootTable{Name=" + name + "}";
    }

}
