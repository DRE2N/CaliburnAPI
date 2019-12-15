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
package de.erethon.caliburn.item;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.category.Categorizable;
import de.erethon.caliburn.category.Category;
import de.erethon.caliburn.mob.ExMob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;

public class ExItem extends Categorizable implements ConfigurationSerializable {

    protected CaliburnAPI api;
    protected ItemFactory itemFactory = Bukkit.getItemFactory();

    private boolean loaded = false;

    protected Map<String, Object> raw;

    protected Material material;

    protected List<Category<ExItem>> categories = new ArrayList<>();
    protected Map<Category<ExMob>, Double> categoryDamageModifiers = new HashMap<>();
    protected Map<ExMob, Double> mobDamageModifiers = new HashMap<>();

    /**
     * @param api the API instance to inject
     */
    public void load(CaliburnAPI api) {
        if (loaded) {
            return;
        }
        loaded = true;
        this.api = api;

        for (Category<ExItem> category : api.getItemCategories()) {
            if (category.getElements().contains(this)) {
                categories.add(category);
            }
        }

        Object categoryDamageModifiers = raw.get("categoryDamageModifiers");
        if (categoryDamageModifiers instanceof Map) {
            for (Entry<?, ?> categoryDamageModifier : ((Map<?, ?>) categoryDamageModifiers).entrySet()) {
                if (categoryDamageModifier.getKey() instanceof String && categoryDamageModifier.getValue() instanceof Double) {
                    Category<ExMob> mobCategory = api.getMobCategory((String) categoryDamageModifier.getKey());
                    this.categoryDamageModifiers.put(mobCategory, (Double) categoryDamageModifier.getValue());
                }
            }
        }

        Object mobDamageModifiers = raw.get("mobDamageModifiers");
        if (mobDamageModifiers instanceof Map) {
            for (Entry<?, ?> mobDamageModifier : ((Map<?, ?>) mobDamageModifiers).entrySet()) {
                if (mobDamageModifier.getKey() instanceof String && mobDamageModifier.getValue() instanceof Double) {
                    ExMob mob = api.getExMob((String) mobDamageModifier.getKey());
                    this.mobDamageModifiers.put(mob, (Double) mobDamageModifier.getValue());
                }
            }
        }
    }

    /* Getters and setters */
    /**
     * Returns the raw data Map that has been used to deserialize this object. Doing any changes to this will not immediately result in changes to the item, but
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
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the Material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the unique name of the item
     */
    public String getName() {
        return getMaterial().name();
    }

    /**
     * @param id an ID to compare to the one of this item
     * @return this object if the ID String refers to this item not necessarily exclusively; null if not
     */
    public ExItem idMatch2nd(String id) {
        return null;
    }

    /* Damage modifiers */
    public Map<Category<ExMob>, Double> getCategoryDamageModifiers() {
        return categoryDamageModifiers;
    }

    public double getCategoryDamageModifier(Category<ExMob> mobCategory) {
        if (categoryDamageModifiers.containsKey(mobCategory)) {
            return categoryDamageModifiers.get(mobCategory);
        } else {
            return 1;
        }
    }

    public Map<ExMob, Double> getMobDamageModifiers() {
        return mobDamageModifiers;
    }

    public double getMobDamageModifier(ExMob mob) {
        if (mobDamageModifiers.containsKey(mob)) {
            return mobDamageModifiers.get(mob);
        } else {
            return 1;
        }
    }

    /**
     * @return the text of the lore line to use as an identifier
     */
    public String getIdLore() {
        return api.getIdentifierPrefix() + id;
    }

    /* Actions */
    @Override
    public Map<String, Object> serialize() {
        if (raw != null) {
            return new HashMap<>(raw);
        }
        Map<String, Object> config = new HashMap<>();
        for (Entry<Category<ExMob>, Double> categoryDamageModifier : categoryDamageModifiers.entrySet()) {
            config.put("categoryDamageModifiers." + categoryDamageModifier.getKey().getId(), categoryDamageModifier.getValue());
        }
        for (Entry<ExMob, Double> mobDamageModifier : mobDamageModifiers.entrySet()) {
            config.put("mobDamageModifiers." + mobDamageModifier.getKey().getId(), mobDamageModifier.getValue());
        }
        return config;
    }

    /**
     * @param amount the stack size
     * @return the item as an org.bukkit.inventory.ItemStack
     */
    public ItemStack toItemStack(int amount) {
        return new ItemStack(getMaterial(), amount);
    }

    /**
     * @return the item as an org.bukkit.inventory.ItemStack with the amount 1
     */
    public ItemStack toItemStack() {
        return toItemStack(1);
    }

}
