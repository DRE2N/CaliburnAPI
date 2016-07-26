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
import io.github.dre2n.caliburn.util.CaliConfiguration;
import io.github.dre2n.commons.util.EnumUtil;
import java.util.ArrayList;
import java.util.List;
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
     * the CaliConfiguration which stores information about the new item
     */
    public UniversalItem addItem(String id, CaliConfiguration config) {
        ItemType type = ItemType.CUSTOM_DEFAULT;
        if (config.contains("type") && EnumUtil.isValidEnum(ItemType.class, config.getString("type"))) {
            type = ItemType.valueOf(config.getString("type"));
        }

        UniversalItem item = type.instantiate(api, id, config);
        if (isValid(item)) {
            items.add(item);
        }
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
     * Checks if the item fulfills minimum requirements to be useable without throwing exceptions.
     *
     * @param item
     * the iem to check
     *
     * @return
     * if the mob fulfills the requirements
     */
    public boolean isValid(UniversalItem item) {
        if (item == null) {
            return false;
        }
        if (item.getMaterial() == null) {
            return false;
        }

        return true;
    }

}
