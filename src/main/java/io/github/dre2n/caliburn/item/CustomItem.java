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
import io.github.dre2n.caliburn.util.CaliAttribute;
import io.github.dre2n.caliburn.util.CaliConfiguration;
import io.github.dre2n.commons.item.ArmorSlot;
import io.github.dre2n.commons.item.ItemUtil;
import io.github.dre2n.commons.misc.EnumUtil;
import io.github.dre2n.commons.misc.NumberUtil;
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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem extends UniversalItem {

    private String name;
    private List<String> lores = new ArrayList<>();
    private Set<ItemFlag> itemFlags = new HashSet<>();
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<CaliAttribute> attributes = new ArrayList<>();
    private List<AttributeModifier> attributeModifiers = new ArrayList<>();
    private List<HashSet<ArmorSlot>> attributeSlots = new ArrayList<>();

    // TODO: Better exception handling
    public CustomItem(Map<String, Object> args) {
        super(args);

        Object name = args.get("name");
        if (name instanceof String) {
            setName((String) name);
        }

        Object lores = args.get("lores");
        if (lores instanceof List) {
            for (Object lore : (List) lores) {
                if (lore instanceof String) {
                    addLore((String) lore);
                }
            }
        }

        Object enchantments = args.get("enchantments");
        if (enchantments instanceof Map) {
            for (Object enchantment : ((Map) enchantments).entrySet()) {
                Enchantment type = Enchantment.getByName(((Entry) enchantment).getKey().toString());
                if (type == null) {
                    type = Enchantment.getById(NumberUtil.parseInt(((Entry) enchantment).getKey().toString(), 1));
                }
                int level = (int) ((Entry) enchantment).getValue();
                if (type != null && level != 0) {
                    this.enchantments.put(type, level);
                }
            }
        }

        Object itemFlags = args.get("itemFlags");
        if (itemFlags instanceof List) {
            for (Object flag : (List) itemFlags) {
                if (flag instanceof String && EnumUtil.isValidEnum(ItemFlag.class, (String) flag)) {
                    this.itemFlags.add(ItemFlag.valueOf((String) flag));
                }
            }
        }

        Object attributes = args.get("attributes");
        if (attributes instanceof Map) {
            for (Object attribute : ((Map) attributes).entrySet()) {
                if (!(attribute instanceof Entry)) {
                    continue;
                }

                Map attributeMap = (Map) ((Entry) attribute).getValue();

                Object type = attributeMap.get("type");
                Object slots = attributeMap.get("slots");
                Object operation = attributeMap.get("operation");
                Object amount = attributeMap.get("amount");

                if (type instanceof String && EnumUtil.isValidEnum(Attribute.class, (String) type)) {
                    type = ItemUtil.getInternalAttributeName(Attribute.valueOf((String) type));
                }

                if (operation instanceof String && !EnumUtil.isValidEnum(Operation.class, (String) operation)) {
                    operation = ItemUtil.getBukkitOperation((byte) NumberUtil.parseInt((String) operation)).toString();
                }

                if (amount instanceof String) {
                    amount = NumberUtil.parseDouble((String) amount);
                }

                AttributeModifier modifier = new AttributeModifier((String) ((Entry) attribute).getKey(), (double) amount, Operation.valueOf((String) operation));

                CaliAttribute caliAttribute = new CaliAttribute((String) type);
                int index = this.attributes.size();
                if (this.attributes.contains(caliAttribute)) {
                    index = this.attributes.indexOf(type);
                }

                this.attributes.add(index, caliAttribute);
                attributeModifiers.add(index, modifier);
                attributeSlots.add(index, new HashSet<ArmorSlot>());

                if (slots instanceof List) {
                    for (Object slot : (List) slots) {
                        if (slot instanceof String && EnumUtil.isValidEnum(ArmorSlot.class, (String) slot)) {
                            attributeSlots.get(index).add(ArmorSlot.valueOf((String) slot));
                        }
                    }
                }
            }
        }
    }

    public CustomItem(CaliburnAPI api, String id, CaliConfiguration config) {
        this(config.getArgs());

        this.api = api;
        this.id = id;
        this.config = config;
    }

    public CustomItem(CaliburnAPI api, String id, Material material) {
        super(api, id, material);
    }

    public CustomItem(CaliburnAPI api, String id, Material material, short durability) {
        super(api, id, material, durability);
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
    public Set<ArmorSlot> getAttributeSlots(CaliAttribute attribute) {
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

        int i = 0;
        for (CaliAttribute attribute : attributes) {
            itemStack = ItemUtil.setAttribute(itemStack, attribute.getName(), attributeModifiers.get(i), attributeSlots.get(i));
            i++;
        }

        return itemStack;
    }

}
