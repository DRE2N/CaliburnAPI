/*
 * Copyright (C) 2015-2018 Daniel Saukel.
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
import de.erethon.caliburn.item.CustomBanner;
import de.erethon.caliburn.item.CustomEnchantedBook;
import de.erethon.caliburn.item.CustomEquipment;
import de.erethon.caliburn.item.CustomFirework;
import de.erethon.caliburn.item.CustomHead;
import de.erethon.caliburn.item.CustomItem;
import de.erethon.caliburn.item.ExItem;
import de.erethon.caliburn.item.VanillaItem;
import de.erethon.caliburn.listener.EntityListener;
import de.erethon.caliburn.mob.ExMob;
import de.erethon.caliburn.mob.VanillaMob;
import de.erethon.caliburn.util.SimpleSerialization;
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

    private List<Category<ExItem>> itemCategories = new ArrayList<>();
    private List<ExItem> items = new ArrayList<>();
    private List<Category<ExMob>> mobCategories = new ArrayList<>();
    private List<ExMob> mobs = new ArrayList<>();

    public CaliburnAPI(Plugin plugin) {
        this(plugin, ChatColor.GRAY.toString());
    }

    public CaliburnAPI(Plugin plugin, String identifierPrefix) {
        instance = this;

        this.identifierPrefix = identifierPrefix;

        items.addAll(VanillaItem.getLoaded());
        mobs.addAll(VanillaMob.getLoaded());

        Bukkit.getPluginManager().registerEvents(new EntityListener(this), plugin);

        ConfigurationSerialization.registerClass(CustomItem.class);
        ConfigurationSerialization.registerClass(CustomBanner.class);
        ConfigurationSerialization.registerClass(CustomEnchantedBook.class);
        ConfigurationSerialization.registerClass(CustomEquipment.class);
        ConfigurationSerialization.registerClass(CustomFirework.class);
        ConfigurationSerialization.registerClass(CustomHead.class);
    }

    /**
     * @return
     * the loaded instance of CaliburnAPI
     */
    public static CaliburnAPI getInstance() {
        return instance;
    }

    /**
     * Supposed to be called after all items, mobs and categories are loaded.
     * Makes items and mobs load their damage modifiers.
     */
    public void finishInitialization() {
        items.forEach(i -> i.load(this));
        mobs.forEach(m -> m.load(this));
    }

    /**
     * @return
     * the prefix of a custom item identifier lore line
     */
    public String getIdentifierPrefix() {
        return identifierPrefix;
    }

    /**
     * @return
     * the loaded instance of SimpleSerialization
     */
    public SimpleSerialization getSimpleSerialization() {
        return simpleSerialization;
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
     * @return
     * the registered items
     */
    public List<ExItem> getExItems() {
        return items;
    }

    /**
     * @return
     * the registered items
     */
    public <T extends ExItem> List<T> getExItems(Class<T> type) {
        List<T> itemsOfType = new ArrayList<>();
        for (ExItem item : items) {
            if (type.isInstance(item)) {
                itemsOfType.add((T) item);
            }
        }
        return itemsOfType;
    }

    /**
     * @return
     * the item that has the ID
     */
    public ExItem getExItem(String id) {
        // This only returns something if the ID exclusively refers to the item
        for (ExItem item : items) {
            ExItem idMatch = (ExItem) item.idMatch(id);
            if (idMatch != null) {
                return idMatch;
            }
        }
        // This also allows ambiguous matches
        for (ExItem item : items) {
            ExItem idMatch = item.idMatch2nd(id);
            if (idMatch != null) {
                return idMatch;
            }
        }
        return null;
    }

    public ExItem getExItem(ItemStack item) {
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            return getExItem(item.getItemMeta().getLore().get(0).replace(identifierPrefix, new String()));
        } else {
            return getExItem(item.getType().name());
        }
    }

    public String getExItemId(ItemStack item) {
        if (item == null) {
            return null;
        }
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            return item.getItemMeta().getLore().get(0).replace(identifierPrefix, new String());
        } else {
            ExItem exItem = getExItem(item.getType().name());
            return exItem.getId();
        }
    }

    /* Item categories */
    /**
     * @return
     * the item categories
     */
    public List<Category<ExItem>> getItemCategories() {
        return itemCategories;
    }

    /**
     * @return
     * the Category<ExItem> that has the ID
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
     * @param type
     * All mobs which are an instance of it will be returned.
     */
    public List<ExMob> getExMobs(Class<? extends ExMob> type) {
        List<ExMob> mobsOfType = new ArrayList<>();
        for (ExMob mob : mobs) {
            if (type.isInstance(mob)) {
                mobsOfType.add(mob);
            }
        }
        return mobsOfType;
    }

    /**
     * @param id
     * a CustomMob or ExMob ID
     * @return
     * the mob that has the ID
     */
    public ExMob getExMob(String id) {
        for (ExMob mob : mobs) {
            ExMob idMatch = (ExMob) mob.idMatch(id);
            if (idMatch != null) {
                return idMatch;
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
     * @return
     * the mob categories
     */
    public List<Category<ExMob>> getMobCategories() {
        return mobCategories;
    }

    /**
     * @return
     * the Category<ExMob> that has the ID
     */
    public Category<ExMob> getMobCategory(String id) {
        for (Category<ExMob> mobCategory : mobCategories) {
            if (mobCategory.getId().equals(id)) {
                return mobCategory;
            }
        }
        return null;
    }

    /* Serialization */
    /**
     * Universal deserialization method to deserialize a Bukkit ItemStack
     *
     * @param config
     * a ConfigurationSection
     * @param path
     * the path in the config where the item to deserialize is found
     * @return
     * the deserialized ItemStack
     */
    public ItemStack deserialize(ConfigurationSection config, String path) {
        if (config.get(path) instanceof ItemStack) {
            return (ItemStack) config.get(path);
        } else if (config.get(path) instanceof String) {
            return simpleSerialization.deserialize(path);
        } else {
            return null;
        }
    }

}
