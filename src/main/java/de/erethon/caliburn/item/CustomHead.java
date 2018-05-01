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

import de.erethon.commons.item.ItemUtil;
import de.erethon.commons.player.PlayerUtil;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CustomHead extends CustomItem {

    private String skullOwner;
    private String textureValue;

    public CustomHead(Map<String, Object> args) {
        super(args);

        Object skullOwner = args.get("skullOwner");
        if (skullOwner instanceof String) {
            setSkullOwner((String) skullOwner);
        }

        Object textureValue = args.get("textureValue");
        if (textureValue instanceof String) {
            setSkullOwner((String) textureValue);
        }
    }

    /* Getters and setters */
    /**
     * @return
     * the skullOwner
     */
    public String getSkullOwner() {
        return skullOwner;
    }

    /**
     * @param skullOwner
     * the skullOwner to set
     */
    public void setSkullOwner(String skullOwner) {
        this.skullOwner = skullOwner;
    }

    /**
     * @return
     * the textureValue
     */
    public String getTextureValue() {
        return textureValue;
    }

    /**
     * @param textureValue
     * the textureValue to set
     */
    public void setTextureValue(String textureValue) {
        this.textureValue = textureValue;
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
     * @return
     * the CustomHead as org.bukkit.inventory.ItemStack
     */
    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = super.toItemStack(amount);

        if (!PlayerUtil.isValidUUID(skullOwner)) {
            SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
            itemMeta.setOwner(getSkullOwner());

            itemStack.setItemMeta(itemMeta);
            return itemStack;

        } else {
            return ItemUtil.setSkullOwner(itemStack, skullOwner, textureValue);
        }

    }

}
