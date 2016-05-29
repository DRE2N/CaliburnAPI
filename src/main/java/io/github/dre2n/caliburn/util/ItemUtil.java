/*
 * Copyright (C) 2015-2016 Daniel Saukel
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

import io.github.dre2n.commons.util.NumberUtil;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class ItemUtil {

    static InternalsProvider internals = InternalsProvider.getInstance();

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param attribute
     * the Attribute to add
     * @param value
     * the attribute value
     * @param slots
     * the slot where the attribute affects the player
     * @return
     * a new Bukkit ItemStack with the attribute
     */
    public static ItemStack setAttribute(ItemStack itemStack, Attribute attribute, AttributeModifier modifier, Set<Slot> slots) {
        return internals.setAttribute(itemStack, attribute, modifier, slots);
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @return
     * the ItemStack, but unbreakable
     */
    public static ItemStack setUnbreakable(ItemStack itemStack) {
        return internals.setUnbreakable(itemStack);
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param id
     * the UUID of the SkullOwner
     * @param textureValue
     * the texture value
     * @return
     */
    public static ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
        return internals.setSkullOwner(itemStack, id, textureValue);
    }

    /**
     * @param string
     * the ID as an unknown string
     * @return
     * the proper item ID
     */
    public static int getId(String string) {
        if (NumberUtil.parseInt(string) > 0) {
            return NumberUtil.parseInt(string);

        } else if (Material.getMaterial(string) != null) {
            return Material.getMaterial(string).getId();

        } else {
            return 267;
        }
    }

    /**
     * @param attribute
     * the attribute to "translate"
     * @return
     * the internal name of the attribute, useful for NMS.
     */
    public static String getInternalAttributeName(Attribute attribute) {
        switch (attribute) {
            case GENERIC_ARMOR:
                return "generic.armor";
            case GENERIC_ATTACK_DAMAGE:
                return "generic.attackDamage";
            case GENERIC_ATTACK_SPEED:
                return "generic.attackSpeed";
            case GENERIC_FOLLOW_RANGE:
                return "generic.followRange";
            case GENERIC_KNOCKBACK_RESISTANCE:
                return "generic.knockbackResistance";
            case GENERIC_LUCK:
                return "generic.luck";
            case GENERIC_MAX_HEALTH:
                return "generic.maxHealth";
            case GENERIC_MOVEMENT_SPEED:
                return "generic.movementSpeed";
            case HORSE_JUMP_STRENGTH:
                return "horse.jumpStrength";
            case ZOMBIE_SPAWN_REINFORCEMENTS:
                return "zombie.spawnReinforcements";
        }

        return null;
    }

}
