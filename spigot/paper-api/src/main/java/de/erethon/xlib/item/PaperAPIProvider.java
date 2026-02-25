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

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import de.erethon.xlib.item.CustomHead.InternalsProvider;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * @author Daniel Saukel
 */
class PaperAPIProvider implements InternalsProvider {

    @Override
    public ItemStack newPlayerHead(int amount) {
        return new ItemStack(Material.PLAYER_HEAD, amount);
    }

    @Override
    public String getTextureValue(ItemStack item) {
        if (!(item.getItemMeta() instanceof SkullMeta)) {
            return null;
        }

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        PlayerProfile profile = meta.getPlayerProfile();
        if (profile == null) {
            return null;
        }

        for (ProfileProperty property : profile.getProperties()) {
            if (property.getName().equals("textures")) {
                return property.getValue();
            }
        }
        return null;
    }

    @Override
    public ItemStack setSkullOwner(ItemStack item, Object compound) {
        String uuid = (String) ((Object[]) compound)[0];
        String value = (String) ((Object[]) compound)[1];
        PlayerProfile profile = Bukkit.createProfile(UUID.fromString(uuid));
        profile.setProperty(new ProfileProperty("textures", value));
        profile.complete();

        ItemStack clone = item.clone();
        SkullMeta meta = (SkullMeta) clone.getItemMeta();
        meta.setPlayerProfile(profile);
        clone.setItemMeta(meta);
        return clone;
    }

    @Override
    public Object createOwnerCompound(String id, String textureValue) {
        return new Object[]{id, textureValue};
    }

}
