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
import io.github.dre2n.caliburn.util.CaliAttribute;
import io.github.dre2n.caliburn.util.CaliSlot;
import io.github.dre2n.caliburn.util.ItemUtil;
import io.github.dre2n.commons.util.EnumUtil;
import io.github.dre2n.commons.util.NumberUtil;
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
    private List<CaliAttribute> attributes = new ArrayList<>();
    private List<AttributeModifier> attributeModifiers = new ArrayList<>();
    private List<HashSet<CaliSlot>> attributeSlots = new ArrayList<>();

    // TODO: Better exception handling
    public CustomItem(Map<String, Object> args) {
        super(args);

        if (args.containsKey("durability")) {
            durability = (short) args.get("durability");
        }

        if (args.containsKey("name")) {
            setName((String) args.get("name"));
        }

        if (args.containsKey("lores")) {
            for (String lore : (List<String>) args.get("lores")) {
                addLore(lore);
            }
        }

        if (args.containsKey("enchantments")) {
            Map<?, ?> enchantments = (Map<?, ?>) args.get("enchantments");
            for (Entry<?, ?> enchantment : enchantments.entrySet()) {
                Enchantment type = Enchantment.getByName(enchantment.getKey().toString());
                if (type == null) {
                    type = Enchantment.getById(NumberUtil.parseInt(enchantment.getKey().toString(), 1));
                }
                int level = (Integer) enchantment.getValue();
                if (type != null && level != 0) {
                    this.enchantments.put(type, level);
                }
            }
        }

        if (args.containsKey("itemFlags")) {
            for (String flag : (List<String>) args.get("itemFlags")) {
                if (EnumUtil.isValidEnum(ItemFlag.class, flag)) {
                    itemFlags.add(ItemFlag.valueOf(flag));
                }
            }
        }

        // Attributes
        if (args.containsKey("attributes")) {
            for (Object name : ((Map<?, ?>) args.get("attributes")).keySet()) {
                Map<?, ?> attributeMap = (Map) name;

                String type = (String) attributeMap.get("type");
                List<String> slots = (List<String>) attributeMap.get("slots");
                String operation = (String) attributeMap.get("operation");
                Double amount = (Double) attributeMap.get("amount");

                if (EnumUtil.isValidEnum(Attribute.class, type)) {
                    type = ItemUtil.getInternalAttributeName(Attribute.valueOf(type));
                }

                if (EnumUtil.isValidEnum(Operation.class, operation)) {
                }

                AttributeModifier modifier = new AttributeModifier((String) name, amount, Operation.valueOf(operation));

                CaliAttribute attribute = new CaliAttribute(type);
                int index = attributes.size();
                if (attributes.contains(attribute)) {
                    index = attributes.indexOf(type);
                }

                attributes.add(index, attribute);
                attributeModifiers.add(index, modifier);
                attributeSlots.add(index, new HashSet<CaliSlot>());
                for (String slot : slots) {
                    if (EnumUtil.isValidEnum(CaliSlot.class, slot)) {
                        attributeSlots.get(index).add(CaliSlot.valueOf(slot));
                    }
                }
            }
        }
    }

    public CustomItem(CaliburnAPI api, String id, ConfigurationSection config) {
        this(config.getValues(true));

        this.api = api;
        this.id = id;
    }

    public CustomItem(CaliburnAPI api, String id, Material material) {
        super(api, id, material);
    }

    public CustomItem(CaliburnAPI api, String id, Material material, short durability) {
        super(api, id, material);

        this.durability = durability;
    }

    /* Getters and setters */
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
    public List<CaliAttribute> getAttributes() {
        return attributes;
    }

    /**
     * @param attribute
     * the attribute
     * @return
     * the modifier of the attribute
     */
    public AttributeModifier getAttributeModifier(CaliAttribute attribute) {
        return attributeModifiers.get(attributes.indexOf(attribute));
    }

    /**
     * @param attribute
     * the attribute
     * @return
     * the slots where the attribute affects the player
     */
    public Set<CaliSlot> getAttributeSlots(CaliAttribute attribute) {
        return attributeSlots.get(attributes.indexOf(attribute));
    }

    /* Actions */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> config = super.serialize();
        // TO DO
        return config;
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

        for (ItemFlag itemFlag : itemFlags) {
            itemMeta.addItemFlags(itemFlag);
        }

        itemStack.setItemMeta(itemMeta);

        for (Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
            itemStack.addUnsafeEnchantment(enchantment.getKey(), enchantment.getValue());
        }

        for (CaliAttribute attribute : attributes) {
            itemStack = ItemUtil.setAttribute(itemStack, attribute.getName(), getAttributeModifier(attribute), getAttributeSlots(attribute));
        }

        return itemStack;
    }

}
