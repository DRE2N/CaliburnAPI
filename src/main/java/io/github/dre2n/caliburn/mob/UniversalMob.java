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
import io.github.dre2n.caliburn.item.ItemCategory;
import io.github.dre2n.caliburn.item.UniversalItem;
import io.github.dre2n.commons.util.EnumUtil;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * @author Daniel Saukel
 */
public class UniversalMob {

    CaliburnAPI api;

    protected String id;
    protected ConfigurationSection config;
    protected EntityType type;

    protected Map<ItemCategory, Double> categoryDamageModifiers = new HashMap<>();
    protected Map<UniversalItem, Double> itemDamageModifiers = new HashMap<>();

    public UniversalMob(CaliburnAPI api, EntityType type) {
        this(api, String.valueOf(type.getTypeId()), type);
    }

    public UniversalMob(CaliburnAPI api, String id, EntityType type) {
        this.api = api;

        this.id = id;
        this.type = type;
    }

    public UniversalMob(CaliburnAPI api, String id, ConfigurationSection config) {
        this.api = api;

        this.id = id;
        this.config = config;
        if (EnumUtil.isValidEnum(EntityType.class, config.getString("type"))) {
            this.type = EntityType.valueOf(config.getString("type"));
        }
    }

    /**
     * Finish initialization of the Object.
     */
    public void setup() {
        if (config.contains("categoryDamageModifiers")) {
            Map<String, Object> categoryDamageModifiers = config.getConfigurationSection("categoryDamageModifiers").getValues(false);
            for (Map.Entry<String, Object> categoryDamageModifier : categoryDamageModifiers.entrySet()) {
                ItemCategory itemCategory = api.getItemCategories().getById(categoryDamageModifier.getKey());
                this.categoryDamageModifiers.put(itemCategory, (double) categoryDamageModifier.getValue());
            }
        }

        if (config.contains("itemDamageModifiers")) {
            Map<String, Object> itemDamageModifiers = config.getConfigurationSection("itemDamageModifiers").getValues(false);
            for (Map.Entry<String, Object> itemDamageModifier : itemDamageModifiers.entrySet()) {
                UniversalItem item = api.getItems().getById(itemDamageModifier.getKey());
                this.itemDamageModifiers.put(item, (double) itemDamageModifier.getValue());
            }
        }
    }

    /* Getters and setters */
    /**
     * @return
     * the ID value of the mob
     */
    public String getId() {
        return id;
    }

    /**
     * @return the mob type
     */
    public EntityType getType() {
        return type;
    }

    /**
     * @param type
     * the mob type to set
     */
    public void setType(EntityType type) {
        this.type = type;
    }

    /**
     * @return
     * the custom name of the mob
     */
    public String getName() {
        return type.getName();
    }

    /**
     * @param name
     * the custom name to set
     */
    public void setName(String name) {
    }

    // Damage modifiers
    public Map<UniversalItem, Double> getItemDamageModifiers() {
        return itemDamageModifiers;
    }

    public double getItemDamageModifier(UniversalItem item) {
        if (itemDamageModifiers.containsKey(item)) {
            return itemDamageModifiers.get(item);

        } else {
            return 1;
        }
    }

    public void addItemDamageModifier(UniversalItem item, double itemDamageModifier) {
        itemDamageModifiers.put(item, itemDamageModifier);
    }

    public Map<ItemCategory, Double> getCategoryDamageModifiers() {
        return categoryDamageModifiers;
    }

    public double getCategoryDamageModifier(ItemCategory itemCategory) {
        if (categoryDamageModifiers.containsKey(itemCategory)) {
            return categoryDamageModifiers.get(itemCategory);

        } else {
            return 1;
        }
    }

    public void addCategoryDamageModifier(ItemCategory itemCategory, double categoryDamageModifier) {
        categoryDamageModifiers.put(itemCategory, categoryDamageModifier);
    }

    /**
     * @return
     * the mob as an org.bukkit.entity.Entity
     */
    public Entity toEntity(Location location) {
        return location.getWorld().spawnEntity(location, type);
    }

}
