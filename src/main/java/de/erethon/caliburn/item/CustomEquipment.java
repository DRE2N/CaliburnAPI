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
import de.erethon.caliburn.item.actionhandler.DamageHandler;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomEquipment extends CustomItem {

    private short durability;
    private boolean unbreakable;

    private DamageHandler damageHandler;

    public CustomEquipment(Map<String, Object> args) {
        super(args);

        Object durability = args.get("durability");
        if (durability instanceof Short) {
            this.durability = (Short) durability;
        }

        Object unbreakable = args.get("unbreakable");
        if (unbreakable instanceof Boolean) {
            this.unbreakable = (Boolean) unbreakable;
        }
    }

    public CustomEquipment(CaliburnAPI api, String id) {
        super(api, id);
    }

    /* Getters and setters */
    /**
     * @return the default durability of this item
     */
    public short getDurability() {
        return durability;
    }

    /**
     * @param durability the default durability to set
     */
    public void setDurability(short durability) {
        this.durability = durability;
    }

    /**
     * @return if the item is unbreakable
     */
    public boolean isUnbreakable() {
        return unbreakable;
    }

    /**
     * @param unbreakable set the item (un-) breakable
     */
    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
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
     * @return the CustomEquipment as org.bukkit.inventory.ItemStack
     */
    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = super.toItemStack(amount);

        itemStack.setDurability(durability);
        ItemMeta meta = itemStack.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

}
