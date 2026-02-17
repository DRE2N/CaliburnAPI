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

import de.erethon.xlib.item.actionhandler.DamageHandler;
import de.erethon.xlib.item.actionhandler.DropHandler;
import de.erethon.xlib.item.actionhandler.HitHandler;
import de.erethon.xlib.item.actionhandler.RightClickHandler;

/**
 * A class to standardize item modifiers. Many effects can be realized through the {@link de.erethon.xlib.item.actionhandler action handler API}. Attributes
 * may be assigned on {@link CustomItem material} or {@link TrackedItemStack stack level}. The outer class represents the attribute template, {@link Instance}
 * represents an attribute applied to a specific attribute holder.
 *
 * @param <V> the type of the value the attribute stores
 * @author Daniel Saukel
 */
public class CustomAttribute<V> {

    /**
     * Represents an attribute applied to a specific attribute holder.
     *
     * @see #instantiate(java.lang.Object)
     */
    public class Instance {
        private V value;

        private Instance() {
        }

        /**
         * Returns the value of this attribute instance.
         *
         * @return the value of this attribute instance
         */
        public V getValue() {
            return value;
        }

        /**
         * Returns the outer class instance.
         *
         * @return the outer class instance
         */
        public CustomAttribute<V> getType() {
            return CustomAttribute.this;
        }

        /**
         * Applies the attribute to an item stack.
         *
         * @see Applicator
         * @see CustomAttribute#apply(Instance, TrackedItemStack)
         * @param itemStack the ItemStack
         */
        public void apply(TrackedItemStack itemStack) {
            CustomAttribute.this.apply(this, itemStack);
        }
    }

    /**
     * Specifies what happens on {@link #apply(Instance, TrackedItemStack) application}.
     */
    @FunctionalInterface
    public interface Applicator {
        void onApplication(CustomAttribute.Instance instance, TrackedItemStack itemStack);
    }

    private String key;
    private Applicator applicator;
    private DamageHandler damageHandler;
    private DropHandler dropHandler;
    private HitHandler hitHandler;
    private RightClickHandler rightClickHandler;

    /**
     * @param applicator specifies what happens on {@link #apply(Instance, TrackedItemStack) application}
     */
    public CustomAttribute(Applicator applicator) {
        if (applicator == null) {
            throw new IllegalArgumentException("Applicator must not be null");
        }
        this.applicator = applicator;
    }

    /**
     * Returns the key used in configuration files and under which the {@link Instance#getValue() value} may be stored.
     *
     * @return the key used in configuration files and under which the {@link Instance#getValue() value} may be stored
     */
    public String getKey() {
        return key;
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
     * Returns an {@link Instance} with the given value.
     *
     * @param value the value to set
     * @return an {@link Instance} with the given value
     */
    public Instance instantiate(V value) {
        Instance instance = new Instance();
        instance.value = value;
        return instance;
    }

    /**
     * Applies the attribute to an item stack.
     *
     * @param value     the value to set
     * @param itemStack the item stack
     * @return the instance created
     */
    public Instance apply(V value, TrackedItemStack itemStack) {
        Instance instance = instantiate(value);
        apply(instance, itemStack);
        return instance;
    }

    /**
     * Applies the attribute to an item stack.
     *
     * @param instance  the instance to set
     * @param itemStack the item stack
     */
    public void apply(Instance instance, TrackedItemStack itemStack) {
        applicator.onApplication(instance, itemStack);
    }

}
