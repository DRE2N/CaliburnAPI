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
import io.github.dre2n.caliburn.mob.MobCategory;
import io.github.dre2n.caliburn.mob.UniversalMob;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

public class UniversalItem implements ConfigurationSerializable {

    protected CaliburnAPI api;

    protected String id;
    protected ConfigurationSection config;

    protected Material material;
    protected short durability;

    protected Map<MobCategory, Double> categoryDamageModifiers = new HashMap<>();
    protected Map<UniversalMob, Double> mobDamageModifiers = new HashMap<>();

    public UniversalItem(Map<String, Object> args) {
        Object material = args.get("material");
        if (material instanceof String) {
            this.material = Material.matchMaterial((String) material);
        } else if (material instanceof Integer) {
            this.material = Material.getMaterial((int) material);
        }

        Object durability = args.get("durability");
        if (durability instanceof Integer) {
            this.durability = ((Integer) durability).shortValue();
        }
    }

    public UniversalItem(CaliburnAPI api, Material material) {
        this(api, String.valueOf(material.getId()), material);
    }

    public UniversalItem(CaliburnAPI api, String id, Material material) {
        this.api = api;

        this.id = id;
        this.material = material;
    }

    public UniversalItem(CaliburnAPI api, String id, Material material, short durability) {
        this.api = api;

        this.id = id;
        this.material = material;
        this.durability = durability;
    }

    public UniversalItem(CaliburnAPI api, String id, ConfigurationSection config) {
        this(config.getValues(true));
        this.api = api;

        this.id = id;
        this.config = config;
    }

    /**
     * Finish initialization of the Object.
     */
    public void setup() {
        if (config.contains("categoryDamageModifiers")) {
            Map<String, Object> categoryDamageModifiers = config.getConfigurationSection("categoryDamageModifiers").getValues(false);
            for (Entry<String, Object> categoryDamageModifier : categoryDamageModifiers.entrySet()) {
                MobCategory mobCategory = api.getMobCategories().getById(categoryDamageModifier.getKey());
                this.categoryDamageModifiers.put(mobCategory, (double) categoryDamageModifier.getValue());
            }
        }

        if (config.contains("mobDamageModifiers")) {
            Map<String, Object> mobDamageModifiers = config.getConfigurationSection("mobDamageModifiers").getValues(false);
            for (Entry<String, Object> mobDamageModifier : mobDamageModifiers.entrySet()) {
                UniversalMob mob = api.getMobs().getById(mobDamageModifier.getKey());
                this.mobDamageModifiers.put(mob, (double) mobDamageModifier.getValue());
            }
        }
    }

    /* Getters and setters */
    /**
     * @return
     * the ID value of the item
     */
    public String getId() {
        return id;
    }

    /**
     * @return
     * the ID formatted to be put into the lore
     */
    public String getIdLore() {
        return CaliburnAPI.IDENTIFIER_PREFIX + getId();
    }

    /**
     * @return
     * the ConfigurationSection that represents the item or a new one
     */
    public ConfigurationSection getConfig() {
        return config;
    }

    /**
     * @return
     * the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material
     * the Material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the durability
     */
    public short getDurability() {
        return durability;
    }

    /**
     * @param durability
     * the durability to set
     */
    public void setDurability(short durability) {
        this.durability = durability;
    }

    /**
     * @return
     * the name of the item
     */
    public String getName() {
        return material.toString();
    }

    /**
     * @param name
     * the name to set
     */
    public void setName(String name) {
    }

    // Damage modifiers
    public Map<MobCategory, Double> getCategoryDamageModifiers() {
        return categoryDamageModifiers;
    }

    public double getCategoryDamageModifier(MobCategory mobCategory) {
        if (categoryDamageModifiers.containsKey(mobCategory)) {
            return categoryDamageModifiers.get(mobCategory);

        } else {
            return 1;
        }
    }

    public void addCategoryDamageModifier(MobCategory mobCategory, double categoryDamageModifier) {
        categoryDamageModifiers.put(mobCategory, categoryDamageModifier);
    }

    public Map<UniversalMob, Double> getMobDamageModifiers() {
        return mobDamageModifiers;
    }

    public double getMobDamageModifier(UniversalMob mob) {
        if (mobDamageModifiers.containsKey(mob)) {
            return mobDamageModifiers.get(mob);

        } else {
            return 1;
        }
    }

    public void addMobDamageModifier(UniversalMob mob, double mobDamageModifier) {
        mobDamageModifiers.put(mob, mobDamageModifier);
    }

    /* Actions */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> config = new HashMap<>();

        config.put("type", ItemType.UNIVERSAL.toString());

        config.put("material", material.toString());

        for (Entry<MobCategory, Double> categoryDamageModifier : categoryDamageModifiers.entrySet()) {
            config.put("categoryDamageModifiers." + categoryDamageModifier.getKey().getId(), categoryDamageModifier.getValue());
        }

        for (Entry<UniversalMob, Double> mobDamageModifier : mobDamageModifiers.entrySet()) {
            config.put("mobDamageModifiers." + mobDamageModifier.getKey().getId(), mobDamageModifier.getValue());
        }

        return config;
    }

    /**
     * @param amount
     * the stack size
     * @return
     * the item as an org.bukkit.inventory.ItemStack
     */
    public ItemStack toItemStack(int amount) {
        return new ItemStack(material, amount, durability);
    }

}
