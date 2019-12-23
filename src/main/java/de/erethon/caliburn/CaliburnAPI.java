/*
 * Copyright (C) 2015-2019 Daniel Saukel.
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
package de.erethon.caliburn;

import de.erethon.caliburn.category.Categorizable;
import de.erethon.caliburn.category.Category;
import de.erethon.caliburn.item.CustomItem;
import de.erethon.caliburn.item.ExItem;
import de.erethon.caliburn.item.VanillaItem;
import de.erethon.caliburn.listener.ItemListener;
import de.erethon.caliburn.listener.MobListener;
import de.erethon.caliburn.loottable.LootTable;
import de.erethon.caliburn.mob.CustomMob;
import de.erethon.caliburn.mob.ExMob;
import de.erethon.caliburn.mob.VanillaMob;
import de.erethon.caliburn.util.ExSerialization;
import de.erethon.caliburn.util.SimpleSerialization;
import de.erethon.commons.compatibility.CompatibilityHandler;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * @author Daniel Saukel
 */
public class CaliburnAPI {

    private static CaliburnAPI instance;

    private String identifierPrefix;

    private SimpleSerialization simpleSerialization = new SimpleSerialization(this);
    private ExSerialization exSerialization = new ExSerialization(this);

    private List<Category<ExItem>> itemCategories = new ArrayList<>();
    private List<ExItem> items = new ArrayList<>();
    private List<Category<ExMob>> mobCategories = new ArrayList<>();
    private List<ExMob> mobs = new ArrayList<>();
    private List<LootTable> lootTables = new ArrayList<>();

    public CaliburnAPI(Plugin plugin) {
        this(plugin, ChatColor.GRAY.toString());
    }

    public CaliburnAPI(Plugin plugin, String identifierPrefix) {
        instance = this;

        this.identifierPrefix = identifierPrefix;

        items.addAll(VanillaItem.getLoaded());
        mobs.addAll(VanillaMob.getLoaded());

        Bukkit.getPluginManager().registerEvents(new MobListener(this), plugin);
        ItemListener il = new ItemListener(this);
        Bukkit.getPluginManager().registerEvents(il, plugin);
        if (CompatibilityHandler.getInstance().isSpigot()) {
            Bukkit.getPluginManager().registerEvents(il.new Spigot(), plugin);
        }

        ConfigurationSerialization.registerClass(CustomItem.class);
        ConfigurationSerialization.registerClass(CustomMob.class);
        ConfigurationSerialization.registerClass(LootTable.class);
    }

    /**
     * @return the loaded instance of CaliburnAPI
     */
    public static CaliburnAPI getInstance() {
        return instance;
    }

    /**
     * Supposed to be called after all items, mobs and categories are loaded. Makes items and mobs load their damage modifiers.
     */
    public void finishInitialization() {
        items.forEach(i -> i.load(this));
        mobs.forEach(m -> m.load(this));
    }

    /**
     * @return the prefix of a custom item identifier lore line
     */
    public String getIdentifierPrefix() {
        return identifierPrefix;
    }

    /**
     * @return the loaded instance of SimpleSerialization
     */
    public SimpleSerialization getSimpleSerialization() {
        return simpleSerialization;
    }

    /**
     * @return the loaded instance of ExSerialization
     */
    public ExSerialization getExSerialization() {
        return exSerialization;
    }

    public Categorizable getExObject(String id) {
        ExItem item = getExItem(id);
        if (item != null) {
            return getExItem(id);
        }
        ExMob mob = getExMob(id);
        if (mob != null) {
            return mob;
        }
        return null;
    }

    /* Items */
    /**
     * @return the registered items
     */
    public List<ExItem> getExItems() {
        return items;
    }

    /**
     * @return the registered custom items
     */
    public List<CustomItem> getCustomItems() {
        List<CustomItem> customItems = new ArrayList<>();
        for (ExItem item : items) {
            if (item instanceof CustomItem) {
                customItems.add((CustomItem) item);
            }
        }
        return customItems;
    }

    /**
     * @param id a CustomItem or VanillaItem ID
     * @return the item that has the ID
     */
    public ExItem getExItem(Object id) {
        if (id instanceof String) {
            // This only returns something if the ID exclusively refers to the item
            for (ExItem item : items) {
                ExItem idMatch = (ExItem) item.idMatch((String) id);
                if (idMatch != null) {
                    return idMatch;
                }
            }
            // This also allows ambiguous matches
            for (ExItem item : items) {
                ExItem idMatch = item.idMatch2nd((String) id);
                if (idMatch != null) {
                    return idMatch;
                }
            }

        } else if (id instanceof Integer) {
            for (VanillaItem item : VanillaItem.getLoaded()) {
                if (item.getNumericId() == (int) id) {
                    return item;
                }
            }
        }

        return null;
    }

    public ExItem getExItem(ItemStack item) {
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            return getExItem(item.getItemMeta().getLore().get(0).replace(identifierPrefix, ""));
        } else {
            return getExItem(item.getType().name());
        }
    }

    public String getExItemId(ItemStack item) {
        if (item == null) {
            return null;
        }
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            return item.getItemMeta().getLore().get(0).replace(identifierPrefix, "");
        } else {
            ExItem exItem = getExItem(item.getType().name());
            return exItem.getId();
        }
    }

    /* Item categories */
    /**
     * @return the item categories
     */
    public List<Category<ExItem>> getItemCategories() {
        return itemCategories;
    }

    /**
     * @return the Category<ExItem> that has the ID
     */
    public Category<ExItem> getItemCategory(String id) {
        for (Category<ExItem> itemCategory : itemCategories) {
            if (itemCategory.getId().equals(id)) {
                return itemCategory;
            }
        }
        return null;
    }

    /* Mobs */
    /**
     * @return the mobs
     */
    public List<ExMob> getExMobs() {
        return mobs;
    }

    /**
     * @return the registered custom mobs
     */
    public List<CustomMob> getCustomMobs() {
        List<CustomMob> customMobs = new ArrayList<>();
        for (ExMob mob : mobs) {
            if (mob instanceof CustomMob) {
                customMobs.add((CustomMob) mob);
            }
        }
        return customMobs;
    }

    /**
     * @param id a CustomMob or VanillaMob ID
     * @return the mob that has the ID
     */
    public ExMob getExMob(Object id) {
        if (id instanceof String) {
            for (ExMob mob : mobs) {
                ExMob idMatch = (ExMob) mob.idMatch((String) id);
                if (idMatch != null) {
                    return idMatch;
                }
            }

        } else if (id instanceof Integer) {
            for (VanillaMob mob : VanillaMob.getLoaded()) {
                if (mob.getNumericId() == (int) id) {
                    return mob;
                }
            }
        }

        return null;
    }

    public ExMob getExMob(Entity entity) {
        return getExMob(getExMobId(entity));
    }

    public String getExMobId(Entity entity) {
        return entity.getType().name();
    }

    /* Mob categories */
    /**
     * @return the mob categories
     */
    public List<Category<ExMob>> getMobCategories() {
        return mobCategories;
    }

    /**
     * @return the Category<ExMob> that has the ID
     */
    public Category<ExMob> getMobCategory(String id) {
        for (Category<ExMob> mobCategory : mobCategories) {
            if (mobCategory.getId().equals(id)) {
                return mobCategory;
            }
        }
        return null;
    }

    /* Loot tables */
    /**
     * @return the loaded loot tables
     */
    public List<LootTable> getLootTables() {
        return lootTables;
    }

    /**
     * @return the loot table that has the name
     */
    public LootTable getLootTable(String name) {
        for (LootTable lootTable : lootTables) {
            if (lootTable.getName().equalsIgnoreCase(name)) {
                return lootTable;
            }
        }

        return null;
    }

    /* Serialization */
    /**
     * Universal deserialization method to deserialize a Bukkit ItemStack
     *
     * @param config a ConfigurationSection
     * @param path   the path in the config where the item to deserialize is found
     * @return the deserialized ItemStack
     */
    public ItemStack deserializeStack(ConfigurationSection config, String path) {
        return deserializeStack(config.get(path));
    }

    /**
     * Universal deserialization method to deserialize a Bukkit ItemStack
     *
     * @param object ItemStack, ExItem or {@link de.erethon.caliburn.util.SimpleSerialization} String
     * @return the deserialized ItemStack
     */
    public ItemStack deserializeStack(Object object) {
        if (object instanceof ItemStack) {
            return (ItemStack) object;
        } else if (object instanceof String) {
            return simpleSerialization.deserialize((String) object);
        } else if (object instanceof ExItem) {
            return exSerialization.deserialize(((ExItem) object).getRaw());
        } else {
            return null;
        }
    }

    /**
     * Universal deserialization method to deserialize lists of Bukkit ItemStacks
     *
     * @param config a ConfigurationSection
     * @param path   the path in the config where the item to deserialize is found
     * @return the deserialized list of ItemStacks
     */
    public List<ItemStack> deserializeStackList(ConfigurationSection config, String path) {
        List<ItemStack> deserialized = new ArrayList<>();
        List<?> list = config.getList(path);
        if (list == null) {
            return deserialized;
        }
        list.forEach(e -> deserialized.add(deserializeStack(e)));
        return deserialized;
    }

    /**
     * Universal deserialization method to deserialize an ExItem
     *
     * @param config a ConfigurationSection
     * @param path   the path in the config where the item to deserialize is found
     * @return the deserialized ExItem
     */
    public ExItem deserializeExItem(ConfigurationSection config, String path) {
        Object obj = config.get(path);
        if (obj instanceof String) {
            return getExItem((String) obj);
        } else if (config.get(path) instanceof ExItem) {
            return (ExItem) obj;
        }
        return null;
    }

    /**
     * Universal deserialization method to deserialize lists of ExItems
     *
     * @param config a ConfigurationSection
     * @param path   the path in the config where the item to deserialize is found
     * @return the deserialized list of ItemStacks
     */
    public List<ExItem> deserializeExItemList(ConfigurationSection config, String path) {
        List<ExItem> deserialized = new ArrayList<>();
        List<?> list = config.getList(path);
        if (list == null) {
            return deserialized;
        }
        for (Object obj : list) {
            if (obj instanceof String || obj instanceof Integer) {
                deserialized.add(getExItem(obj));
            } else if (obj instanceof ExItem) {
                deserialized.add((ExItem) obj);
            }
        }
        return deserialized;
    }

}
