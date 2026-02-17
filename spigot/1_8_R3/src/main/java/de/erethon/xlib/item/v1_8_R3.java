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
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class v1_8_R3 implements InternalsProvider {

    @Override
    public ItemStack newPlayerHead(int amount) {
        return new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
    }

    @Override
    public String getTextureValue(ItemStack item) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

        NBTTagCompound tag = nmsStack.getTag();
        if (tag == null) {
            return null;
        }

        NBTTagCompound skullOwner = tag.getCompound("SkullOwner");
        if (skullOwner == null) {
            return null;
        }
        NBTTagCompound properties = skullOwner.getCompound("Properties");
        if (properties == null) {
            return null;
        }
        NBTTagList textures = properties.getList("textures", 10);
        if (textures == null) {
            return null;
        }

        for (int i = 0; i < textures.size(); i++) {
            NBTBase base = textures.get(i);
            if (base instanceof NBTTagCompound && ((NBTTagCompound) base).hasKeyOfType("Value", 8)) {
                return ((NBTTagCompound) base).getString("Value");
            }
        }
        return null;
    }

    @Override
    public ItemStack setSkullOwner(ItemStack item, Object compound) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsStack.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        tag.set("SkullOwner", (NBTBase) compound);
        nmsStack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    @Override
    public NBTTagCompound createOwnerCompound(String id, String textureValue) {
        NBTTagCompound skullOwner = new NBTTagCompound();
        skullOwner.setString("Id", id);
        NBTTagCompound properties = new NBTTagCompound();
        NBTTagList textures = new NBTTagList();
        NBTTagCompound value = new NBTTagCompound();
        value.setString("Value", textureValue);
        textures.add(value);
        properties.set("textures", textures);
        skullOwner.set("Properties", properties);
        return skullOwner;
    }

}
