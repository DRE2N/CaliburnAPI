/*
 * Copyright (C) 2015-2021 Daniel Saukel.
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
import de.erethon.caliburn.item.actionhandler.DamageHandler;
import de.erethon.caliburn.item.actionhandler.DropHandler;
import de.erethon.caliburn.item.actionhandler.HitHandler;
import de.erethon.caliburn.item.actionhandler.RightClickHandler;
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

    /**
     * The ItemMeta that will be applied to an {@link org.bukkit.inventory.ItemStack} created from this CustomItem.
     */
    protected ItemMeta meta;
    private String name;

    private DamageHandler damageHandler;
    private DropHandler dropHandler;
    private HitHandler hitHandler;
    private RightClickHandler rightClickHandler;

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

        Object damageHandler = args.get("damageHandler");
        if (damageHandler instanceof String) {
            deserialized.setDamageHandler(DamageHandler.create((String) damageHandler));
        }
        Object dropHandler = args.get("dropHandler");
        if (dropHandler instanceof String) {
            deserialized.setDropHandler(DropHandler.create((String) dropHandler));
        }
        Object hitHandler = args.get("hitHandler");
        if (hitHandler instanceof String) {
            deserialized.setHitHandler(HitHandler.create((String) hitHandler));
        }
        Object rightClickHandler = args.get("rightClickHandler");
        if (rightClickHandler instanceof String) {
            deserialized.setRightClickHandler(RightClickHandler.create((String) rightClickHandler));
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

    /* Events */
    /**
     * Returns if the custom item has a DamageHandler.
     *
     * @return if the custom item has a DamageHandler
     */
    public boolean hasDamageHandler() {
        return damageHandler != null;
    }

    /**
     * Returns the DamageHandler.
     *
     * @return the DamageHandler
     */
    public DamageHandler getDamageHandler() {
        return damageHandler;
    }

    /**
     * Sets the DamageHandler.
     *
     * @param handler the handler to set
     */
    public void setDamageHandler(DamageHandler handler) {
        damageHandler = handler;
    }

    /**
     * Returns if the custom item has a DropHandler.
     *
     * @return if the custom item has a DropHandler
     */
    public boolean hasDropHandler() {
        return dropHandler != null;
    }

    /**
     * Returns the DropHandler.
     *
     * @return the DropHandler
     */
    public DropHandler getDropHandler() {
        return dropHandler;
    }

    /**
     * Sets the DropHandler.
     *
     * @param handler the handler to set
     */
    public void setDropHandler(DropHandler handler) {
        dropHandler = handler;
    }

    /**
     * Returns if the custom item has a HitHandler.
     *
     * @return if the custom item has a HitHandler
     */
    public boolean hasHitHandler() {
        return hitHandler != null;
    }

    /**
     * Returns the HitHandler.
     *
     * @return the HitHandler
     */
    public HitHandler getHitHandler() {
        return hitHandler;
    }

    /**
     * Sets the HitHandler.
     *
     * @param handler the handler to set
     */
    public void setHitHandler(HitHandler handler) {
        hitHandler = handler;
    }

    /**
     * Returns if the custom item has a RightClickHandler.
     *
     * @return if the custom item has a RightClickHandler
     */
    public boolean hasRightClickHandler() {
        return rightClickHandler != null;
    }

    /**
     * Returns the RightClickHandler.
     *
     * @return the RightClickHandler
     */
    public RightClickHandler getRightClickHandler() {
        return rightClickHandler;
    }

    /**
     * Sets the RightClickHandler.
     *
     * @param handler the handler to set
     */
    public void setRightClickHandler(RightClickHandler handler) {
        rightClickHandler = handler;
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

        if (damageHandler != null) {
            config.put("damageHandler", damageHandler.getClass().getName());
        }
        if (dropHandler != null) {
            config.put("dropHandler", dropHandler.getClass().getName());
        }
        if (hitHandler != null) {
            config.put("hitHandler", hitHandler.getClass().getName());
        }
        if (rightClickHandler != null) {
            config.put("rightClickHandler", rightClickHandler.getClass().getName());
        }
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
            meta.getPersistentDataContainer().set(new NamespacedKey("caliburn", "id"), PersistentDataType.STRING, id);
            itemStack.setItemMeta(meta);
        }

        return itemStack;
    }

}
