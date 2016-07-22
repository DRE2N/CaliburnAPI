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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class CustomEnchantedBook extends CustomItem {

    private Map<Enchantment, Integer> storedEnchantments = new HashMap<>();

    public CustomEnchantedBook(Map<String, Object> args) {
        super(args);

        Object storedEnchantments = args.get("storedEnchantments");
        if (storedEnchantments instanceof Map) {
            for (Object enchantment : ((Map) storedEnchantments).entrySet()) {
                Enchantment type = Enchantment.getByName((String) ((Entry) enchantment).getKey());
                int level = (int) ((Entry) enchantment).getValue();
                if (type != null && level != 0) {
                    this.storedEnchantments.put(type, level);
                }
            }
        }
    }

    public CustomEnchantedBook(CaliburnAPI api, String id, Material material, short durability) {
        super(api, id, material, durability);
    }

    public CustomEnchantedBook(CaliburnAPI api, String id, ConfigurationSection config) {
        this(config.getValues(true));

        this.api = api;
        this.id = id;
        this.config = config;
    }

    /* Getters and setters */
    /**
     * @return
     * the storedEnchantments
     */
    public Map<Enchantment, Integer> getStoredEnchantments() {
        return storedEnchantments;
    }

    /**
     * @param type
     * the type of the enchantment
     * @param level
     * the level of the enchantment
     */
    public void addStoredEnchantment(Enchantment type, int level) {
        this.storedEnchantments.put(type, level);
    }

    /* Actions */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> config = super.serialize();
        // TO DO
        return config;
    }

    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = super.toItemStack(amount);

        EnchantmentStorageMeta itemMeta = (EnchantmentStorageMeta) itemStack.getItemMeta();
        for (Entry<Enchantment, Integer> enchantment : getStoredEnchantments().entrySet()) {
            itemMeta.addStoredEnchant(enchantment.getKey(), enchantment.getValue(), true);
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
