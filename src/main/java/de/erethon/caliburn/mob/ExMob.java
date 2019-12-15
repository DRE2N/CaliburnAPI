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
package de.erethon.caliburn.mob;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.category.Categorizable;
import de.erethon.caliburn.category.Category;
import de.erethon.caliburn.item.ExItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * @author Daniel Saukel
 */
public class ExMob extends Categorizable implements ConfigurationSerializable {

    protected CaliburnAPI api;

    protected Map<String, Object> raw;

    protected EntityType species;

    protected List<Category<ExMob>> categories = new ArrayList<>();
    protected Map<Category<ExItem>, Double> categoryDamageModifiers = new HashMap<>();
    protected Map<ExItem, Double> itemDamageModifiers = new HashMap<>();

    public void load(CaliburnAPI api) {
        for (Category<ExMob> category : api.getMobCategories()) {
            if (category.getElements().contains(this)) {
                categories.add(category);
            }
        }

        Object categoryDamageModifiers = raw.get("categoryDamageModifiers");
        if (categoryDamageModifiers instanceof Map) {
            for (Entry<?, ?> categoryDamageModifier : ((Map<?, ?>) categoryDamageModifiers).entrySet()) {
                if (categoryDamageModifier.getKey() instanceof String && categoryDamageModifier.getValue() instanceof Double) {
                    Category<ExItem> itemCategory = api.getItemCategory((String) categoryDamageModifier.getKey());
                    this.categoryDamageModifiers.put(itemCategory, (Double) categoryDamageModifier.getValue());
                }
            }
        }

        Object itemDamageModifiers = raw.get("itemDamageModifiers");
        if (itemDamageModifiers instanceof Map) {
            for (Entry<?, ?> itemDamageModifier : ((Map<?, ?>) itemDamageModifiers).entrySet()) {
                if (itemDamageModifier.getKey() instanceof String && itemDamageModifier.getValue() instanceof Double) {
                    ExItem item = api.getExItem((String) itemDamageModifier.getKey());
                    this.itemDamageModifiers.put(item, (Double) itemDamageModifier.getValue());
                }
            }
        }
    }

    /* Getters and setters */
    /**
     * Returns the raw data Map that has been used to deserialize this object. Doing any changes to this will not immediately result in changes to the mob, but
     * changes will be kept upon serialization. While {@link #serialize() serialize()} returns a copy of the data Map, this returns the original.
     *
     * @return the raw data Map that has been used to deserialize this object
     */
    public Map<String, Object> getRaw() {
        return raw;
    }

    /**
     * Sets the Map that will be used for serialization.
     *
     * @param raw the raw data Map to set
     */
    public void setRaw(Map<String, Object> raw) {
        this.raw = raw;
    }

    /**
     * Modifies a value of the raw data Map.
     *
     * @param raw the raw data Map to set
     */
    public void setRaw(String key, Object value) {
        if (raw == null) {
            raw = serialize();
        }
        raw.put(key, value);
    }

    /**
     * @return the mob species
     */
    public EntityType getSpecies() {
        return species;
    }

    /**
     * @param species the mob species to set
     */
    public void setSpecies(EntityType species) {
        this.species = species;
    }

    /**
     * @return the display name of the mob
     */
    public String getName() {
        return getSpecies().name();
    }

    /* Damage modifiers */
    public Map<ExItem, Double> getItemDamageModifiers() {
        return itemDamageModifiers;
    }

    public double getItemDamageModifier(ExItem item) {
        if (itemDamageModifiers.containsKey(item)) {
            return itemDamageModifiers.get(item);

        } else {
            return 1;
        }
    }

    public Map<Category<ExItem>, Double> getCategoryDamageModifiers() {
        return categoryDamageModifiers;
    }

    public double getCategoryDamageModifier(Category<ExItem> itemCategory) {
        if (categoryDamageModifiers.containsKey(itemCategory)) {
            return categoryDamageModifiers.get(itemCategory);

        } else {
            return 1;
        }
    }

    /* Actions */
    @Override
    public Map<String, Object> serialize() {
        if (raw != null) {
            return new HashMap<>(raw);
        }
        Map<String, Object> config = new HashMap<>();
        for (Entry<Category<ExItem>, Double> categoryDamageModifier : categoryDamageModifiers.entrySet()) {
            config.put("categoryDamageModifiers." + categoryDamageModifier.getKey().getId(), categoryDamageModifier.getValue());
        }
        for (Entry<ExItem, Double> mobDamageModifier : itemDamageModifiers.entrySet()) {
            config.put("mobDamageModifiers." + mobDamageModifier.getKey().getId(), mobDamageModifier.getValue());
        }
        return config;
    }

    /**
     * @return the mob as an org.bukkit.entity.Entity
     */
    public Entity toEntity(Location location) {
        return location.getWorld().spawnEntity(location, getSpecies());
    }

}
