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

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.erethon.xlib.item.CustomHead.InternalsProvider;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ResolvableProfile;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_21_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class v1_21_R1 implements InternalsProvider {

    @Override
    public ItemStack newPlayerHead(int amount) {
        return new ItemStack(Material.PLAYER_HEAD, amount);
    }

    @Override
    public String getTextureValue(ItemStack item) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

        if (!nmsStack./*has*/b(DataComponents./*PROFILE*/W)) {
            return null;
        }

        ResolvableProfile profile = nmsStack./*get*/a(DataComponents./*PROFILE*/W);
        if (profile == null || profile./*gameProfile*/f() == null) {
            return null;
        }

        Collection<Property> properties = profile./*gameProfile*/f().getProperties().get("textures");
        if (properties == null || properties.isEmpty()) {
            return null;
        }

        return properties.iterator().next().value();
    }

    @Override
    public ItemStack setSkullOwner(ItemStack item, Object compound) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        nmsStack./*set*/a(DataComponents./*PROFILE*/W, (ResolvableProfile) compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    @Override
    public ResolvableProfile createOwnerCompound(String id, String textureValue) {
        UUID uuid = UUID.fromString(id);
        GameProfile gameProfile = new GameProfile(uuid, "XLib");
        gameProfile.getProperties().put("textures", new Property("textures", textureValue));
        return new ResolvableProfile(gameProfile);
    }

}
