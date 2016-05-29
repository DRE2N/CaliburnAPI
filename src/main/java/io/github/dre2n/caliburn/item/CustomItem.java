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
import io.github.dre2n.caliburn.util.ItemUtil;
import io.github.dre2n.caliburn.util.Slot;
import io.github.dre2n.commons.util.EnumUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem extends UniversalItem {

    // ItemStack
    private short durability;

    // Meta
    private String name;
    private List<String> lores = new ArrayList<>();
    private Set<ItemFlag> itemFlags = new HashSet<>();
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<Attribute> attributes = new ArrayList<>();
    private List<AttributeModifier> attributeModifiers = new ArrayList<>();
    private List<HashSet<Slot>> attributeSlots = new ArrayList<>();

    public CustomItem(CaliburnAPI api, String id, Material material) {
        super(api, id, material);
    }

    public CustomItem(CaliburnAPI api, String id, Material material, short durability) {
        super(api, id, material);

        this.durability = durability;
    }

    public CustomItem(CaliburnAPI api, String id, ConfigurationSection config) {
        super(api, id, config);

        if (EnumUtil.isValidEnum(Material.class, config.getString("material"))) {
            material = Material.valueOf(config.getString("material"));
        }

        durability = (short) config.getInt("durability");

        if (config.contains("name")) {
            setName(config.getString("name"));
        }

        if (config.contains("lores")) {
            for (String lore : config.getStringList("lores")) {
                addLore(lore);
            }
        }

        if (config.contains("enchantments")) {
            Map<String, Object> enchantments = config.getConfigurationSection("enchantments").getValues(false);
            for (Entry<String, Object> enchantment : enchantments.entrySet()) {
                Enchantment type = Enchantment.getByName(enchantment.getKey());
                int level = config.getInt("enchantments." + enchantment.getKey());
                if (type != null && level != 0) {
                    this.enchantments.put(type, level);
                }
            }
        }

        if (config.contains("itemFlags")) {
            for (String flag : config.getStringList("itemFlags")) {
                if (EnumUtil.isValidEnum(ItemFlag.class, name)) {
                    itemFlags.add(ItemFlag.valueOf(flag));
                }
            }
        }

        // Attributes
        if (config.contains("attributes")) {
            for (String name : config.getConfigurationSection("attributes").getValues(false).keySet()) {
                String prefix = config.getString("attributes." + name + ".");

                String type = config.getString(prefix + "type");
                List<String> slots = config.getStringList(prefix + "slots");
                String operation = config.getString(prefix + "operation");
                double amount = config.getDouble(prefix + "amount");

                if (!EnumUtil.isValidEnum(Attribute.class, type) || !EnumUtil.isValidEnum(Operation.class, operation)) {
                    continue;
                }

                AttributeModifier modifier = new AttributeModifier(name, amount, Operation.valueOf(operation));

                int index = attributes.size();
                if (attributes.contains(Attribute.valueOf(type))) {
                    index = attributes.indexOf(type);
                }

                attributes.set(index, Attribute.valueOf(type));
                attributeModifiers.set(index, modifier);
                attributeSlots.set(index, new HashSet<Slot>());
                for (String slot : slots) {
                    if (EnumUtil.isValidEnum(Slot.class, slot)) {
                        attributeSlots.get(index).add(Slot.valueOf(slot));
                    }
                }
            }
        }
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
    }

    /**
     * @return the lore
     */
    public List<String> getLores() {
        return lores;
    }

    /**
     * @param lore
     * the lore to add
     */
    public void addLore(String lore) {
        lores.add(ChatColor.translateAlternateColorCodes('&', lore));
    }

    /**
     * @return the enchantments as a Map<Enchantment, Integer>
     */
    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    /**
     * @param enchantment
     * the enchantment to add
     * @param level
     * the level of the enchantment
     */
    public void addEnchantment(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
    }

    /**
     * @return the ItemFlags as a List<ItemFlag>
     */
    public Set<ItemFlag> getItemFlags() {
        return itemFlags;
    }

    /**
     * @param itemFlags
     * the itemFlags to add
     */
    public void addItemFlag(ItemFlag itemFlag) {
        itemFlags.add(itemFlag);
    }

    /**
     * @param itemFlags
     * the itemFlags to remove
     */
    public void removeItemFlag(ItemFlag itemFlag) {
        itemFlags.remove(itemFlag);
    }

    /**
     * @return
     * the attributes
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * @param attribute
     * the attribute
     * @return
     * the modifier of the attribute
     */
    public AttributeModifier getAttributeModifier(Attribute attribute) {
        return attributeModifiers.get(attributes.indexOf(attribute));
    }

    /**
     * @param attribute
     * the attribute
     * @return
     * the slots where the attribute affects the player
     */
    public Set<Slot> getAttributeSlots(Attribute attribute) {
        return attributeSlots.get(attributes.indexOf(attribute));
    }

    /**
     * @return the custom item as an ItemStack
     */
    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = new ItemStack(material, amount, durability);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (name != null) {
            itemMeta.setDisplayName(name);
        }

        List<String> lores = new ArrayList<>();
        lores.add(getIdLore());
        lores.addAll(getLores());
        itemMeta.setLore(lores);

        for (ItemFlag itemFlag : getItemFlags()) {
            itemMeta.addItemFlags(itemFlag);
        }

        itemStack.setItemMeta(itemMeta);

        for (Entry<Enchantment, Integer> enchantment : getEnchantments().entrySet()) {
            itemStack.addUnsafeEnchantment(enchantment.getKey(), enchantment.getValue());
        }

        for (Attribute attribute : attributes) {
            ItemUtil.setAttribute(itemStack, attribute, getAttributeModifier(attribute), getAttributeSlots(attribute));
        }

        return itemStack;
    }

}
