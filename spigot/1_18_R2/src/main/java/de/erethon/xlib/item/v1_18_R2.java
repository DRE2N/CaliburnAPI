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

import de.erethon.xlib.item.CustomHead.InternalsProvider;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class v1_18_R2 implements InternalsProvider {

    @Override
    public ItemStack newPlayerHead(int amount) {
        return new ItemStack(Material.PLAYER_HEAD, amount);
    }

    @Override
    public String getTextureValue(ItemStack item) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

        CompoundTag tag = nmsStack.getTag();
        if (tag == null) {
            return null;
        }

        CompoundTag skullOwner = tag.getCompound("SkullOwner");
        if (skullOwner == null) {
            return null;
        }
        CompoundTag properties = skullOwner.getCompound("Properties");
        if (properties == null) {
            return null;
        }
        ListTag textures = properties.getList("textures", 10);
        if (textures == null) {
            return null;
        }

        for (Tag base : textures) {
            if (base instanceof CompoundTag && ((CompoundTag) base).contains("Value", 8)) {
                return ((CompoundTag) base).getString("Value");
            }
        }
        return null;
    }

    @Override
    public ItemStack setSkullOwner(ItemStack item, Object compound) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        nmsStack.getOrCreateTag().put("SkullOwner", (Tag) compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    @Override
    public CompoundTag createOwnerCompound(String id, String textureValue) {
        CompoundTag skullOwner = new CompoundTag();
        skullOwner.putUUID("Id", UUID.fromString(id));
        CompoundTag properties = new CompoundTag();
        ListTag textures = new ListTag();
        CompoundTag value = new CompoundTag();
        value.putString("Value", textureValue);
        textures.add(value);
        properties.put("textures", textures);
        skullOwner.put("Properties", properties);
        return skullOwner;
    }

}
