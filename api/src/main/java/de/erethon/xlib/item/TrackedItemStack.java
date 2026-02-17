/*
 * Copyright (C) 2015-2026 Daniel Saukel.
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
package de.erethon.xlib.item;

import de.erethon.xlib.XLib;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

/**
 * Wraps an {@link org.bukkit.inventory.ItemStack} to add stack-specific custom item data, in contrast to {@link CustomItem} that wraps
 * {@link org.bukkit.Material} to add custom mechanics all item stacks of a type share. Each TrackedItemStack is uniquely identified by its
 * {@link #getDataKey() data key}.
 *
 * @author Daniel Saukel
 */
public class TrackedItemStack {

    /**
     * The key used to store the ID of the stack.
     */
    public static final NamespacedKey DATA_KEY = new NamespacedKey(XLib.NAMESPACE, "data_key");
    /**
     * The key used to store the date when the stack was updated for the last time.
     */
    public static final NamespacedKey LAST_UPDATE = new NamespacedKey(XLib.NAMESPACE, "last_update");

    private static long lastKey;

    private long dataKey = Long.MIN_VALUE;
    private long lastUpdate = Long.MIN_VALUE;
    private CustomItem type;
    private ItemStack wrapped;
    private List<CustomAttribute.Instance> dynamicAttributes = new ArrayList<>();

    /**
     * For internal use. See {@link XLib#wrap(org.bukkit.inventory.ItemStack)} and
     * {@link XLib#wrap(de.erethon.caliburn.item.CustomItem, org.bukkit.inventory.ItemStack)}
     *
     * @param api     the api instance
     * @param type    the type
     * @param wrapped the stack to wrap
     */
    public TrackedItemStack(XLib api, CustomItem type, ItemStack wrapped) {
        this.type = type;
        this.wrapped = wrapped;

        ItemMeta meta = wrapped.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (container.has(DATA_KEY, PersistentDataType.LONG)) {
            dataKey = container.get(DATA_KEY, PersistentDataType.LONG);
        }

        if (container.has(LAST_UPDATE, PersistentDataType.LONG)) {
            lastUpdate = container.get(LAST_UPDATE, PersistentDataType.LONG);
        }
        if (lastUpdate < type.getUpdateTimestamp()) {
            // Get stack-specific data
            int damage = Integer.MIN_VALUE;
            if (meta instanceof Damageable) {
                damage = ((Damageable) meta).getDamage();
            }
            type.apply(wrapped);

            // Get new meta from type
            meta = wrapped.getItemMeta();
            container = meta.getPersistentDataContainer();

            // Reapply stack-specific data
            if (meta instanceof Damageable) {
                ((Damageable) meta).setDamage(damage);
            }
            getAttributes().forEach(a -> a.apply(this));
            lastUpdate = System.currentTimeMillis();
            container.set(LAST_UPDATE, PersistentDataType.LONG, lastUpdate);
        }

        if (!container.has(DATA_KEY, PersistentDataType.LONG)) {
            dataKey = System.currentTimeMillis();
            while (dataKey <= lastKey) {
                dataKey++;
            }
            container.set(DATA_KEY, PersistentDataType.LONG, dataKey);
            lastKey = dataKey;
        }
    }

    /**
     * Returns the key that uniquely identifies the stack.
     *
     * @return the key that uniquely identifies the stack
     */
    public long getDataKey() {
        return dataKey;
    }

    /**
     * Returns the CustomItem that this stack belongs to.
     *
     * @return the CustomItem that this stack belongs to
     */
    public CustomItem getType() {
        return type;
    }

    /**
     * Returns the ItemStack wrapped by this TrackedItemStack.
     *
     * @return the ItemStack wrapped by this TrackedItemStack
     */
    public ItemStack getWrapped() {
        return wrapped;
    }

    /**
     * Returns a copy of the list of attributes, excluding the {@link #getType() type's} {@link CustomItem#getStaticAttributes() static attributes}.
     *
     * @return a copy of the list of attributes
     */
    public List<CustomAttribute.Instance> getDynamicAttributes() {
        return new ArrayList<>(dynamicAttributes);
    }

    /**
     * Returns a List of all attributes this stack has.
     * <p>
     * This includes both the stack's {@link #getDynamicAttributes() dynamic} and the
     * {@link #getType() type's} {@link CustomItem#getStaticAttributes() static attributes}.
     *
     * @return a List of all attributes this stack has
     */
    public List<CustomAttribute.Instance> getAttributes() {
        List<CustomAttribute.Instance> allAttributes = getDynamicAttributes();
        allAttributes.addAll(type.getStaticAttributes());
        return allAttributes;
    }

    /**
     * Adds the given attribute.
     *
     * @param attribute the attribute
     */
    public void addAttribute(CustomAttribute.Instance attribute) {
        dynamicAttributes.add(attribute);
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
        dynamicAttributes.remove(attribute);
    }

}
