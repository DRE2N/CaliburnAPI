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
package io.github.dre2n.caliburn.item;

import io.github.dre2n.caliburn.CaliburnAPI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author Daniel Saukel
 */
public class ItemCategories {

    private ConfigurationSection config;
    private List<ItemCategory> itemCategories = new ArrayList<>();

    public ItemCategories(CaliburnAPI api, ConfigurationSection config) {
        this.config = config;

        Map<String, Object> itemCategories = config.getValues(false);
        for (Entry<String, Object> itemCategory : itemCategories.entrySet()) {
            this.itemCategories.add(new ItemCategory(api, itemCategory.getKey()));
        }
    }

    public void setup() {
        for (ItemCategory itemCategory : itemCategories) {
            itemCategory.setup(config.getStringList(itemCategory.getId()));
        }
    }

    /**
     * @return
     * the itemCategory that has the ID
     */
    public ItemCategory getById(String id) {
        for (ItemCategory itemCategory : itemCategories) {
            if (itemCategory.getId().equals(id)) {
                return itemCategory;
            }
        }
        return null;
    }

    /**
     * @return
     * the categories of the item
     */
    public List<ItemCategory> getByItem(UniversalItem item) {
        ArrayList<ItemCategory> categories = new ArrayList<>();
        for (ItemCategory itemCategory : itemCategories) {
            if (itemCategory.getItems().contains(item)) {
                categories.add(itemCategory);
            }
        }
        return categories;
    }

    /**
     * @return
     * the itemCategories
     */
    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }

    /**
     * @param itemCategory
     * the itemCategory to add
     */
    public void addItemCategory(ItemCategory itemCategory) {
        itemCategories.add(itemCategory);
    }

    /**
     * @param itemCategory
     * the itemCategory to remove
     */
    public void removeItemCategory(ItemCategory itemCategory) {
        itemCategories.remove(itemCategory);
    }

}
