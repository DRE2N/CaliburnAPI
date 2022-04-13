/*
 * Copyright (C) 2015-2022 Daniel Saukel.
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

import com.google.common.collect.Multimap;
import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.category.IdentifierType;
import de.erethon.caliburn.util.StringUtil;
import de.erethon.bedrock.misc.EnumUtil;
import de.erethon.headlib.HeadLib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

/**
 * An item that has by default changed properties compared to Minecraft's vanilla items.
 *
 * @author Daniel Saukel
 */
public class CustomItem extends ExItem {

    public static final NamespacedKey ID = new NamespacedKey(CaliburnAPI.NAMESPACE, "id");

    /**
     * The ItemMeta that will be applied to an {@link org.bukkit.inventory.ItemStack} created from this CustomItem.
     */
    protected ItemMeta meta;
    private String name;

    private List<CustomAttribute.Instance> staticAttributes = new ArrayList<>();
    private long updateTimestamp;

    private String skullOwner, textureValue;
    private String nbt;

    @Deprecated
    private short data = Short.MIN_VALUE;

    public CustomItem(CaliburnAPI api, IdentifierType idType, String id, ItemStack item) {
        this.api = api;
        this.idType = idType;
        this.id = id;
        name = StringUtil.formatId(id);

        setBase(VanillaItem.get(item.getType()));
        meta = item.getItemMeta();
        if (meta instanceof SkullMeta) {
            OfflinePlayer owner = ((SkullMeta) meta).getOwningPlayer();
            if (owner != null) {
                skullOwner = owner.getUniqueId().toString();
                textureValue = HeadLib.getTextureValue(item);
            }
        }

        raw = serialize();
    }

    public CustomItem(CaliburnAPI api, IdentifierType idType, String id, ExItem base) {
        this.api = api;
        this.idType = idType;
        this.id = id;
        name = StringUtil.formatId(id);
        setBase(base);
        raw = serialize();
    }

    private CustomItem() {
    }

    public static CustomItem deserialize(Map<String, Object> args) {
        if (args == null) {
            throw new IllegalArgumentException("args must not be null");
        }
        CustomItem deserialized = new CustomItem();
        deserialized.api = CaliburnAPI.getInstance();
        deserialized.raw = args;

        Object material = args.get("material");
        if (material instanceof String) {
            ExItem base = deserialized.api.getExItem((String) material);
            if (base != null) {
                deserialized.setBase(base);
            }
        }
        if (deserialized.base == null) {
            throw new IllegalArgumentException("Custom item does not have valid material");
        }

        Object idType = args.get("idType");
        if (idType instanceof String) {
            IdentifierType idTypeValue = EnumUtil.getEnumIgnoreCase(IdentifierType.class, (String) idType);
            if (idTypeValue != null) {
                deserialized.idType = idTypeValue;
            }
        } else {
            idType = IdentifierType.LORE;
        }

        Object meta = args.get("meta");
        if (meta instanceof ItemMeta) {
            deserialized.meta = (ItemMeta) meta;
        } else {
            deserialized.meta = Bukkit.getItemFactory().getItemMeta(deserialized.getMaterial());
        }

        Object updateTimestamp = args.get("updateTimestamp");
        if (updateTimestamp instanceof Number) {
            deserialized.updateTimestamp = ((Number) updateTimestamp).longValue();
        }

        Object skullOwner = args.get("skullOwner"), textureValue = args.get("textureValue");
        if (skullOwner instanceof String && textureValue instanceof String) {
            deserialized.setSkullTexture((String) skullOwner, (String) textureValue);
        }

        Object nbt = args.get("nbt");
        if (nbt instanceof String) {
            deserialized.nbt = (String) nbt;
        }

        Object data = args.get("durability");
        if (data instanceof Number) {
            deserialized.data = ((Number) data).shortValue();
        }

        return deserialized;
    }

    @Override
    public CustomItem id(String id) {
        super.id(id);
        name = StringUtil.formatId(id);
        return this;
    }

    /* Getters and setters */
    @Override
    public String getName() {
        if (getMeta() != null && getMeta().hasDisplayName()) {
            return getMeta().getDisplayName();
        } else {
            return name;
        }
    }

    /**
     * Sets the display name of the item.
     *
     * @param name the display name to set
     */
    public void setName(String name) {
        getMeta().setDisplayName(name);
    }

    /**
     * Returns the ItemMeta that will be applied to an {@link org.bukkit.inventory.ItemStack} created from this CustomItem.
     *
     * @return the ItemMeta that will be applied to an {@link org.bukkit.inventory.ItemStack} created from this CustomItem
     */
    public ItemMeta getMeta() {
        return meta;
    }

    /**
     * Sets the ItemMeta that will be applied to an {@link org.bukkit.inventory.ItemStack} created from this CustomItem directly.
     *
     * @param meta the meta data to set
     */
    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    /**
     * Returns the lore.
     *
     * @return the lore
     */
    public List<String> getLore() {
        return meta.getLore();
    }

    /**
     * Adds a lore line.
     *
     * @param lore the lore to add
     */
    public void addLore(String lore) {
        List<String> lines = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        lines.add(ChatColor.translateAlternateColorCodes('&', lore));
        meta.setLore(lines);
    }

    /**
     * Removes a lore line.
     *
     * @param index the lore index number
     */
    public void removeLore(int index) {
        if (!meta.hasLore()) {
            return;
        }
        List<String> lines = meta.getLore();
        lines.remove(index);
        meta.setLore(lines);
    }

    /**
     * Removes a lore line.
     * <p>
     * Chat colors are translated, so removing "&amp;4Test" will remove {@link org.bukkit.ChatColor}.DARK_RED + "Test".
     *
     * @param lore the lore to remove
     */
    public void removeLore(String lore) {
        if (!meta.hasLore()) {
            return;
        }
        List<String> lines = meta.getLore();
        lines.remove(ChatColor.translateAlternateColorCodes('&', lore));
        meta.setLore(lines);
    }

    /**
     * Returns the enchantments as a Map of Enchantment and level integer.
     *
     * @return the enchantments as a Map of Enchantment and level integer
     */
    public Map<Enchantment, Integer> getEnchantments() {
        return meta.getEnchants();
    }

    /**
     * Adds an enchantment.
     *
     * @param enchantment the enchantment to add
     * @param level       the level of the enchantment
     */
    public void addEnchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
    }

    /**
     * Removes an enchantment.
     *
     * @param enchantment the enchantment
     */
    public void removeEnchantment(Enchantment enchantment) {
        meta.removeEnchant(enchantment);
    }

    /**
     * Returns a List of the item flags.
     *
     * @return a List of the item flags
     */
    public Set<ItemFlag> getItemFlags() {
        return meta.getItemFlags();
    }

    /**
     * Adds an item flag.
     *
     * @param itemFlag the item flag to add
     */
    public void addItemFlag(ItemFlag itemFlag) {
        meta.addItemFlags(itemFlag);
    }

    /**
     * Removes an item flag.
     *
     * @param itemFlag the item flag to remove
     */
    public void removeItemFlag(ItemFlag itemFlag) {
        meta.removeItemFlags(itemFlag);
    }

    /**
     * @return the attributes
     */
    /*public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        return meta.getAttributeModifiers();
    }*/
    /**
     * @param attribute the attribute
     * @param modifier  the attribute modifier
     */
    /*public void addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        meta.addAttributeModifier(attribute, modifier);
    }*/
    /**
     * Removes all modifiers of the given attribute.
     *
     * @param attribute the attribute
     */
    /*public void removeAttributeModifier(Attribute attribute) {
        meta.removeAttributeModifier(attribute);
    }*/
    /**
     * Removes a specific modifier of the given attribute.
     *
     * @param attribute the attribute
     * @param modifier  the attribute modifier
     */
    /*public void removeAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        meta.removeAttributeModifier(attribute);
    }*/
    /**
     * Removes a modifier from the given slot.
     *
     * @param slot the equipment slot
     */
    public void removeAttributeModifier(EquipmentSlot slot) {
        meta.removeAttributeModifier(slot);
    }

    /**
     * Returns the custom model data value.
     *
     * @return the custom model data value
     */
    public Integer getCustomModelData() {
        return meta.getCustomModelData();
    }

    /**
     * Sets the custom model data value.
     *
     * @param data the custmo model data value
     */
    public void setCustomModelData(Integer data) {
        meta.setCustomModelData(data);
    }

    /**
     * Returns a copy of the list of attributes.
     *
     * @return a copy of the list of attributes
     */
    public List<CustomAttribute.Instance> getStaticAttributes() {
        return new ArrayList<>(staticAttributes);
    }

    /**
     * Adds the given attribute.
     *
     * @param attribute the attribute
     */
    public void addAttribute(CustomAttribute.Instance attribute) {
        staticAttributes.add(attribute);
    }

    /**
     * Adds an attribute of the given type with the given value.
     *
     * @param <V>   the type of the attribute's value
     * @param type  the attribute type
     * @param value the value
     */
    public <V> void addAttribute(CustomAttribute<V> type, V value) {
        addAttribute(type.instantiate(value));
    }

    /**
     * Removes the given attribute.
     *
     * @param attribute the attribute
     */
    public void removeAttribute(CustomAttribute.Instance attribute) {
        staticAttributes.remove(attribute);
    }

    /**
     * Returns a timestamp that was added when the item was changed.
     *
     * @return a timestamp that was added when the item was changed
     */
    public long getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * Sets the update timestamp to the current time.
     */
    public void setUpdateTimestamp() {
        updateTimestamp = System.currentTimeMillis();
    }

    /**
     * Returns the skull owner UUID String.
     *
     * @return the skull owner UUID String
     */
    public String getSkullOwner() {
        return skullOwner;
    }

    /**
     * Returns the Base64 encoded skull owner texture value.
     *
     * @return the Base64 encoded skull owner texture value
     */
    public String getTextureValue() {
        return textureValue;
    }

    /**
     * Sets the skull owner and texture value for player heads.
     *
     * @param skullOwner   the skull owner UUID String
     * @param textureValue the Base64 encoded skull owner texture value
     */
    public void setSkullTexture(String skullOwner, String textureValue) {
        this.skullOwner = skullOwner;
        this.textureValue = textureValue;
    }

    /* Actions */
    /**
     * Registers the item sothat it can be fetched through the getter methods.
     *
     * @return itself
     */
    public CustomItem register() {
        if (api.getExItems().contains(this) || api.getExItem(id) != null) {
            throw new IllegalStateException("Item already registered");
        }
        if (id == null) {
            throw new IllegalStateException("No ID specified");
        }
        api.getExItems().add((ExItem) id(id));
        return this;
    }

    /**
     * Registers the item sothat it can be fetched through the getter methods.
     *
     * @param id the ID to set
     * @return itself
     */
    public CustomItem register(String id) {
        if (api.getExItems().contains(this) || api.getExItem(id) != null) {
            throw new IllegalStateException("Item already registered");
        }
        api.getExItems().add((ExItem) id(id));
        return this;
    }

    @Override
    public Map<String, Object> serialize() {
        if (raw != null) {
            return new HashMap<>(raw);
        }
        Map<String, Object> config = super.serialize();

        config.put("material", base.getId());
        config.put("idType", idType.toString());
        config.put("meta", meta);
        config.put("updateTimestamp", updateTimestamp);

        if (skullOwner != null) {
            config.put("skullOwner", skullOwner);
        }
        if (textureValue != null) {
            config.put("textureValue", textureValue);
        }
        if (data != Short.MIN_VALUE) {
            config.put("durability", data);
        }
        if (nbt != null) {
            config.put("nbt", nbt);
        }
        return config;
    }

    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = base.toItemStack(amount);
        apply(itemStack);
        return itemStack;
    }

    public void apply(ItemStack itemStack) {
        itemStack.setItemMeta(meta.clone());
        if (data != Short.MIN_VALUE) {
            itemStack.setDurability(data);
        }
        if (textureValue != null && skullOwner != null) {
            itemStack = HeadLib.setSkullOwner(itemStack, skullOwner, textureValue);
        }
        if (nbt != null) {
            itemStack = Bukkit.getUnsafe().modifyItemStack(itemStack, nbt);
        }

        if (idType == IdentifierType.DISPLAY_NAME) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(api.getIdentifierPrefix() + id);
            itemStack.setItemMeta(meta);
        } else if (idType == IdentifierType.LORE) {
            ItemMeta meta = itemStack.getItemMeta();
            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            lore.add(0, api.getIdentifierPrefix() + id);
            meta.setLore(lore);
            itemStack.setItemMeta(meta);
        } else if (idType == IdentifierType.PERSISTENT_DATA_CONTAINER) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.getPersistentDataContainer().set(ID, PersistentDataType.STRING, id);
            itemStack.setItemMeta(meta);
        }
    }

}
