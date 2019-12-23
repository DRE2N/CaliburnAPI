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

import com.google.common.collect.Multimap;
import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.actionhandler.DamageHandler;
import de.erethon.caliburn.item.actionhandler.DropHandler;
import de.erethon.caliburn.item.actionhandler.HitHandler;
import de.erethon.caliburn.item.actionhandler.RightClickHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem extends ExItem {

    private VanillaItem base;
    private String name;
    protected ItemMeta meta;

    private DamageHandler damageHandler;
    private DropHandler dropHandler;
    private HitHandler hitHandler;
    private RightClickHandler rightClickHandler;

    private String skullOwner, textureValue;

    @Deprecated
    private short data = Short.MIN_VALUE;

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

        Object meta = args.get("meta");
        if (meta instanceof ItemMeta) {
            this.meta = (ItemMeta) meta;
        } else {
            this.meta = itemFactory.getItemMeta(this.material);
        }

        Object name = args.get("name");
        if (name instanceof String) {
            setName((String) name);
        }

        Object damageHandler = args.get("damageHandler");
        if (damageHandler instanceof String) {
            setDamageHandler(DamageHandler.create((String) damageHandler));
        }
        Object dropHandler = args.get("dropHandler");
        if (dropHandler instanceof String) {
            setDropHandler(DropHandler.create((String) dropHandler));
        }
        Object hitHandler = args.get("hitHandler");
        if (hitHandler instanceof String) {
            setHitHandler(HitHandler.create((String) hitHandler));
        }
        Object rightClickHandler = args.get("rightClickHandler");
        if (rightClickHandler instanceof String) {
            setRightClickHandler(RightClickHandler.create((String) rightClickHandler));
        }

        Object skullOwner = args.get("skullOwner");
        if (skullOwner instanceof String) {
            setSkullOwner((String) skullOwner);
        }
        Object textureValue = args.get("textureValue");
        if (textureValue instanceof String) {
            setSkullOwner((String) textureValue);
        }

        Object data = args.get("durability");
        if (data instanceof Number) {
            this.data = ((Number) data).shortValue();
        }
    }

    public CustomItem(CaliburnAPI api, String id) {
        this.api = api;
        this.id = id;
        raw = serialize();
    }

    /* Getters and setters */
    /**
     * @return the item that this one is based on
     */
    public VanillaItem getBase() {
        return base;
    }

    /**
     * @param base set the item that this one is based on
     */
    public void setBase(VanillaItem base) {
        this.base = base;
    }

    /**
     * @return the item meta
     */
    public ItemMeta getMeta() {
        return meta;
    }

    /**
     * @param meta the meta data to set
     */
    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the display name to set
     */
    public void setName(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        meta.setDisplayName(this.name);
    }

    /**
     * @return the lore
     */
    public List<String> getLore() {
        return meta.getLore();
    }

    /**
     * @param lore the lore to add
     */
    public void addLore(String lore) {
        meta.getLore().add(ChatColor.translateAlternateColorCodes('&', lore));
    }

    /**
     * @return the enchantments as a Map<Enchantment, Integer>
     */
    public Map<Enchantment, Integer> getEnchantments() {
        return meta.getEnchants();
    }

    /**
     * @param enchantment the enchantment to add
     * @param level       the level of the enchantment
     */
    public void addEnchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
    }

    /**
     * @return the ItemFlags as a List<ItemFlag>
     */
    public Set<ItemFlag> getItemFlags() {
        return meta.getItemFlags();
    }

    /**
     * @param itemFlag the itemFlag to add
     */
    public void addItemFlag(ItemFlag itemFlag) {
        meta.addItemFlags(itemFlag);
    }

    /**
     * @param itemFlag the itemFlags to remove
     */
    public void removeItemFlag(ItemFlag itemFlag) {
        meta.removeItemFlags(itemFlag);
    }

    /**
     * @return the attributes
     */
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        return meta.getAttributeModifiers();
    }

    /**
     * @param attribute the attribute
     * @param modifier  the attribute modifier
     */
    public void addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        meta.addAttributeModifier(attribute, modifier);
    }

    /**
     * @return the skullOwner
     */
    public String getSkullOwner() {
        return skullOwner;
    }

    /**
     * @param skullOwner the skullOwner to set
     */
    public void setSkullOwner(String skullOwner) {
        this.skullOwner = skullOwner;
    }

    /**
     * @return the textureValue
     */
    public String getTextureValue() {
        return textureValue;
    }

    /**
     * @param textureValue the textureValue to set
     */
    public void setTextureValue(String textureValue) {
        this.textureValue = textureValue;
    }

    /* Events */
    /**
     * @return if the custom item has a DamageHandler
     */
    public boolean hasDamageHandler() {
        return damageHandler != null;
    }

    /**
     * @return the DamageHandler
     */
    public DamageHandler getDamageHandler() {
        return damageHandler;
    }

    /**
     * @param handler the handler to set
     */
    public void setDamageHandler(DamageHandler handler) {
        damageHandler = handler;
    }

    /**
     * @return if the custom item has a DropHandler
     */
    public boolean hasDropHandler() {
        return dropHandler != null;
    }

    /**
     * @return the DropHandler
     */
    public DropHandler getDropHandler() {
        return dropHandler;
    }

    /**
     * @param handler the handler to set
     */
    public void setDropHandler(DropHandler handler) {
        dropHandler = handler;
    }

    /**
     * @return if the custom item has a HitHandler
     */
    public boolean hasHitHandler() {
        return hitHandler != null;
    }

    /**
     * @return the HitHandler
     */
    public HitHandler getHitHandler() {
        return hitHandler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHitHandler(HitHandler handler) {
        hitHandler = handler;
    }

    /**
     * @return if the custom item has a RightClickHandler
     */
    public boolean hasRightClickHandler() {
        return rightClickHandler != null;
    }

    /**
     * @return the RightClickHandler
     */
    public RightClickHandler getRightClickHandler() {
        return rightClickHandler;
    }

    /**
     * @param handler the handler to set
     */
    public void setRightClickHandler(RightClickHandler handler) {
        rightClickHandler = handler;
    }

    /* Actions */
    /**
     * Registers the item sothat it can be fetched through the getter methods
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
     * Registers the item sothat it can be fetched through the getter methods
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
        return config;
    }

    /**
     * @return the custom item as an ItemStack
     */
    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = base.toItemStack(amount);
        itemStack.setItemMeta(meta.clone());
        if (data != Short.MIN_VALUE) {
            itemStack.setDurability(data);
        }
        return itemStack;
    }

}
