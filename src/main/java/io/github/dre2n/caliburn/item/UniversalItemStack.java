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
import io.github.dre2n.commons.misc.NumberUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class UniversalItemStack implements ConfigurationSerializable {

    private UniversalItem item;
    private int amount;

    public UniversalItemStack(Map<String, Object> args) {
        ItemType type = ItemType.valueOf((String) args.get("type"));
        item = type.instantiate(args);
        amount = (int) args.get("amount");
    }

    public UniversalItemStack(UniversalItem item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public UniversalItemStack(Items items, ItemStack stack) {
        item = items.getById(items.getCustomItemId(stack));
        amount = stack.getAmount();
    }

    /* Getters and setters */
    /**
     * @return
     * the item type of this stack
     */
    public UniversalItem getItem() {
        return item;
    }

    /**
     * @param item
     * the item type of this stack to set
     */
    public void setItem(UniversalItem item) {
        this.item = item;
    }

    /**
     * @return
     * the item amount of this stack
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount
     * the item amount of this stack to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /* Actions */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = item.serialize();
        args.put("amount", amount);
        return args;
    }

    /**
     * @return
     * a Bukkit ItemStack that with the values of this UniversalItemStack
     */
    public ItemStack toItemStack() {
        return item.toItemStack(amount);
    }

    /**
     * Deserializes Caliburn, Bukkit and simple Strings like in {@link #deserializeSimple(java.lang.String)}.
     *
     * @param serialized
     * the serialized List of ItemStacks
     * @return
     * the deserialized List of ItemStacks
     */
    public static List<ItemStack> deserializeList(List serialized) {
        List<ItemStack> stacks = new ArrayList<>();
        for (Object entry : serialized) {
            ItemStack stack = null;

            if (entry instanceof ItemStack) {
                stack = (ItemStack) entry;

            } else if (entry instanceof UniversalItemStack) {
                stack = ((UniversalItemStack) entry).toItemStack();

            } else if (entry instanceof String) {
                UniversalItemStack universalStack = deserializeSimple((String) entry);
                if (universalStack != null) {
                    stack = universalStack.toItemStack();
                }
            }

            stacks.add(stack);
        }
        return stacks;
    }

    /**
     * Uses a simple "UniversalItem,amount,durability" format.
     *
     * @param serialized
     * the serialized String
     * @return
     * the UniversalItemStack
     */
    public static UniversalItemStack deserializeSimple(String serialized) {
        String[] args = serialized.split(",");

        UniversalItem item = CaliburnAPI.getInstance().getItems().getById(args[0]);
        if (item == null) {
            return null;
        }

        int amount = 1;
        if (args.length >= 2) {
            amount = NumberUtil.parseInt(args[1], 1);
        }

        if (args.length == 3) {
            item.setDurability((short) NumberUtil.parseInt(args[2]));
        }

        return new UniversalItemStack(item, amount);
    }

}
