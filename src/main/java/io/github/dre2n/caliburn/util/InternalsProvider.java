/*
 * Copyright (C) 2016 Daniel Saukel
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
package io.github.dre2n.caliburn.util;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import java.util.Set;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class InternalsProvider {

    private static InternalsProvider instance;

    static InternalsProvider getInstance() {
        switch (CompatibilityHandler.getInstance().getInternals()) {
            case v1_10_R1:
                instance = new v1_10_R1();
                break;
            case v1_9_R2:
                instance = new v1_9_R2();
                break;
            case v1_9_R1:
                instance = new v1_9_R1();
                break;
            default:
                instance = new InternalsProvider();
                break;
        }

        return instance;
    }

    ItemStack setAttribute(ItemStack itemStack, Attribute attribute, AttributeModifier modifier, Set<Slot> slot) {
        return itemStack;
    }

    ItemStack setUnbreakable(ItemStack itemStack) {
        if (CompatibilityHandler.getInstance().isSpigot()) {
            itemStack.getItemMeta().spigot().setUnbreakable(true);
        }

        return itemStack;
    }

    ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
        return itemStack;
    }

}
