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
package io.github.dre2n.caliburn.item;

import io.github.dre2n.caliburn.CaliburnAPI;
import io.github.dre2n.commons.util.EnumUtil;
import io.github.dre2n.commons.util.NumberUtil;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public class Items {

    CaliburnAPI api;

    private List<UniversalItem> items = new ArrayList<>();

    public Items(CaliburnAPI api) {
        this.api = api;
    }

    public void setup() {
        for (UniversalItem item : items) {
            item.setup();
        }
    }

    /**
     * @return
     * the item that has the ID
     */
    public UniversalItem getById(String id) {
        for (UniversalItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        return null;
    }

    /**
     * @return
     * the items
     */
    public List<UniversalItem> getItems() {
        return items;
    }

    /**
     * @param type
     * All items which are an instance of it will be returned.
     */
    public List<UniversalItem> getItems(Class<? extends UniversalItem> type) {
        List<UniversalItem> itemsOfType = new ArrayList<>();
        for (UniversalItem item : items) {
            if (item.getClass() == type) {
                itemsOfType.add(item);
            }
        }
        return itemsOfType;
    }

    /**
     * @param item
     * can be any object which extends UniversalItem
     */
    public void addItem(UniversalItem item) {
        items.add(item);
    }

    /**
     * @param config
     * the ConfigurationSection which stores information about the new item
     */
    public UniversalItem addItem(String id, ConfigurationSection config) {
        ItemType type = ItemType.CUSTOM_DEFAULT;
        if (config.contains("type") && EnumUtil.isValidEnum(ItemType.class, config.getString("type"))) {
            type = ItemType.valueOf(config.getString("type"));
        }

        UniversalItem item = type.instantiate(api, id, config);
        items.add(item);
        return item;
    }

    /**
     * @param item
     * can be any object which extends UniversalItem
     */
    public void removeItem(UniversalItem item) {
        items.remove(item);
    }

    public String getCustomItemId(ItemStack itemStack) {
        if (itemStack == null) {
            return "0";
        }

        if (itemStack.hasItemMeta()) {
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemMeta.hasLore()) {
                return itemMeta.getLore().get(0).replace(CaliburnAPI.IDENTIFIER_PREFIX, "");
            }
        }

        return String.valueOf(itemStack.getType().getId());
    }

    /**
     * @param config
     * a configuration section
     * @param amount
     * the item stack size
     * @return
     * an ItemStack with the values from config
     */
    public ItemStack deserializeStack(ConfigurationSection config, int amount) {
        return deserializeStack("caliburn", config, amount);
    }

    /**
     * @param id
     * the ID the item will use
     * @param config
     * a configuration section
     * @param amount
     * the item stack size
     * @return
     * an ItemStack with the values from config
     */
    public ItemStack deserializeStack(String id, ConfigurationSection config, int amount) {
        return deserializeItem(id, config).toItemStack(amount);
    }

    /**
     * Method to get an ItemStack from an ID String.
     * The format is "identifier,amount".
     *
     * @param data
     * the data String
     * @return
     * the List<ItemStack> with the values from config
     */
    public ItemStack deserializeStack(String data) {
        String[] values = data.split(",");
        String id = values[0];
        int amount = values.length >= 1 ? NumberUtil.parseInt(values[1]) : 1;

        if (getById(id) != null) {
            return getById(id).toItemStack(amount);

        } else {
            return null;
        }
    }

    /**
     * Method to get a List of ItemStacks from a List of ID Strings.
     * The format is "identifier,amount".
     *
     * @param dataList
     * the String List
     * @return
     * the List<ItemStack> with the values from config
     */
    public List<ItemStack> deserializeStackList(List<String> dataList) {
        List<ItemStack> items = new ArrayList<>();
        for (String data : dataList) {
            items.add(deserializeStack(data));
        }
        return items;
    }

    /**
     * @param config
     * a configuration section
     * @return
     * a UniversalItem with the values from config
     */
    public UniversalItem deserializeItem(ConfigurationSection config) {
        return deserializeItem("caliburn", config);
    }

    /**
     * @param id
     * the ID the item will use
     * @param config
     * a configuration section
     * @param amount
     * the item stack size
     * @return
     * a UniversalItem with the values from config
     */
    public UniversalItem deserializeItem(String id, ConfigurationSection config) {
        ItemType type = ItemType.CUSTOM_DEFAULT;
        if (config.contains("type") && EnumUtil.isValidEnum(ItemType.class, config.getString("type"))) {
            type = ItemType.valueOf(config.getString("type"));
        }
        return type.instantiate(api, id, config);
    }

}
