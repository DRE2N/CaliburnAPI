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
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class InternalsProvider {

    ItemStack setAttribute(ItemStack itemStack, String attributeName, double amount, byte operation, Set<String> slot) {
        return itemStack;
    }

    ItemStack setHideFlags(ItemStack itemStack, byte flags) {
        return itemStack;
    }

    ItemStack setUnbreakable(ItemStack itemStack, byte unbreakable) {
        if (CompatibilityHandler.getInstance().isSpigot()) {
            itemStack.getItemMeta().spigot().setUnbreakable(unbreakable == 1);
        }

        return itemStack;
    }

    ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
        return itemStack;
    }

}
