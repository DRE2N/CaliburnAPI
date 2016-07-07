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

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.util.NumberUtil;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class ItemUtil {

    static InternalsProvider internals;

    static {
        switch (CompatibilityHandler.getInstance().getInternals()) {
            case v1_10_R1:
                internals = new v1_10_R1();
                break;
            case v1_9_R2:
                internals = new v1_9_R2();
                break;
            case v1_9_R1:
                internals = new v1_9_R1();
                break;
            default:
                internals = new InternalsProvider();
        }
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param attribute
     * the Attribute to add
     * @param modifier
     * the attribute values
     * @param slots
     * the slot where the attribute affects the player
     * @return
     * a new Bukkit ItemStack with the attribute
     */
    public static ItemStack setAttribute(ItemStack itemStack, Attribute attribute, AttributeModifier modifier, Set<Slot> slots) {
        Set<String> slotStrings = new HashSet<>();
        for (Slot slot : slots) {
            slotStrings.add(slot.getInternalName());
        }
        return setAttribute(itemStack, getInternalAttributeName(attribute), modifier.getAmount(), getInternalOperationValue(modifier.getOperation()), slotStrings);
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param attributeName
     * the Attribute name
     * @param amount
     * the Attribute amount
     * @param operation
     * the modifier operation
     * @param slots
     * the slot where the attribute affects the player
     * @return
     * a new Bukkit ItemStack with the attribute
     */
    public static ItemStack setAttribute(ItemStack itemStack, String attributeName, double amount, byte operation, Set<String> slots) {
        return internals.setAttribute(itemStack, attributeName, amount, operation, slots);
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param unbreakable
     * set the stack unbreakable or not
     * @return
     * a new Bukkit ItemStack which is unbreakable
     */
    public static ItemStack setUnbreakable(ItemStack itemStack, boolean unbreakable) {
        return internals.setUnbreakable(itemStack, (byte) (unbreakable ? 1 : 0));
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param unbreakable
     * set the stack unbreakable or not
     * @return
     * a new Bukkit ItemStack which is unbreakable
     */
    public static ItemStack setUnbreakable(ItemStack itemStack, byte unbreakable) {
        return internals.setUnbreakable(itemStack, unbreakable);
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param flags
     * the flags number
     * @return
     * a new Bukkit ItemStack with the flags
     */
    public static ItemStack setHideFlags(ItemStack itemStack, byte flags) {
        return internals.setHideFlags(itemStack, flags);
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
    @SuppressWarnings("deprecation")
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

    /**
     * @param operation
     * the attribute operation to "translate"
     * @return
     * the internal ID of the operation, useful for NMS.
     */
    public static byte getInternalOperationValue(Operation operation) {
        switch (operation) {
            case ADD_NUMBER:
                return 0;
            case ADD_SCALAR:
                return 1;
            case MULTIPLY_SCALAR_1:
                return 2;
        }

        return 0;
    }

}
