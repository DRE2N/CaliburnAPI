/*
 * Copyright (C) 2015-2018 Daniel Saukel.
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
import de.erethon.commons.item.AttributeWrapper;
import de.erethon.commons.item.InternalAttribute;
import de.erethon.commons.item.InternalOperation;
import de.erethon.commons.item.InternalSlot;
import de.erethon.commons.misc.EnumUtil;
import de.erethon.commons.misc.NumberUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem extends ExItem {

    private VanillaItem base;
    private String name;
    private List<String> lores = new ArrayList<>();
    private Set<ItemFlag> itemFlags = new HashSet<>();
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<AttributeWrapper> attributes = new ArrayList<>();

    // TODO: Better exception handling
    public CustomItem(Map<String, Object> args) {
        raw = args;

        Object material = args.get("material");
        if (material instanceof String) {
            ExItem base = CaliburnAPI.getInstance().getExItem((String) material);
            if (base instanceof VanillaItem) {
                setBase((VanillaItem) base);
                this.material = base.getMaterial();
            }
        }

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
                InternalAttribute intType = null;
                Object slots = attributeMap.get("slots");
                ArrayList<InternalSlot> intSlots = new ArrayList<>();
                Object operation = attributeMap.get("operation");
                InternalOperation intOp = null;
                Object amount = attributeMap.get("amount");
                Double intAmount = Double.NaN;

                if (type instanceof String) {
                    intType = EnumUtil.getEnumIgnoreCase(InternalAttribute.class, (String) type);
                }
                if (slots instanceof List) {
                    for (Object slot : (List) slots) {
                        if (slot instanceof String) {
                            InternalSlot iSlot = EnumUtil.getEnumIgnoreCase(InternalSlot.class, (String) slot);
                            if (iSlot != null) {
                                intSlots.add(iSlot);
                            }
                        }
                    }
                }
                if (operation instanceof String) {
                    intOp = EnumUtil.getEnumIgnoreCase(InternalOperation.class, (String) operation);
                }
                if (amount instanceof String) {
                    intAmount = NumberUtil.parseDouble((String) amount);
                } else if (amount instanceof Double) {
                    intAmount = (Double) amount;
                }

                if (intType != null && intOp != null && intAmount != Double.NaN) {
                    this.attributes.add(new AttributeWrapper(intType, intAmount, intOp, intSlots.toArray(new InternalSlot[intSlots.size()])));
                }
            }
        }
    }

    public CustomItem(String id) {
        this.id = id;
        raw = serialize();
    }

    /* Getters and setters */
    /**
     * @return
     * the item that this one is based on
     */
    public VanillaItem getBase() {
        return base;
    }

    /**
     * @param base
     * set the item that this one is based on
     */
    public void setBase(VanillaItem base) {
        this.base = base;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name
     * the display name to set
     */
    public void setName(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
    }

    /**
     * @return
     * the lore
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
     * @return
     * the ItemFlags as a List<ItemFlag>
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
    public List<AttributeWrapper> getAttributes() {
        return attributes;
    }

    /* Actions */
    @Override
    public Map<String, Object> serialize() {
        if (raw != null) {
            return new HashMap<>(raw);
        }
        Map<String, Object> config = super.serialize();
        // TO DO
        return config;
    }

    /**
     * @return the custom item as an ItemStack
     */
    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = base.toItemStack(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (name != null) {
            itemMeta.setDisplayName(name);
        }

        List<String> lores = new ArrayList<>();
        if (id != null) {
            lores.add(getIdLore());
        }
        lores.addAll(getLores());
        itemMeta.setLore(lores);

        for (ItemFlag itemFlag : itemFlags) {
            itemMeta.addItemFlags(itemFlag);
        }

        itemStack.setItemMeta(itemMeta);

        for (Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
            itemStack.addUnsafeEnchantment(enchantment.getKey(), enchantment.getValue());
        }

        for (AttributeWrapper attribute : attributes) {
            itemStack = attribute.applyTo(itemStack);
        }

        return itemStack;
    }

}
