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
import io.github.dre2n.caliburn.util.CaliConfiguration;
import io.github.dre2n.commons.item.ItemUtil;
import io.github.dre2n.commons.misc.PlayerUtil;
import java.util.Map;
import org.bukkit.Material;
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

    public CustomHead(CaliburnAPI api, String id) {
        super(api, id, Material.SKULL_ITEM, (short) 3);
    }

    public CustomHead(CaliburnAPI api, String id, CaliConfiguration config) {
        this(config.getArgs());

        this.api = api;
        this.id = id;
        this.config = config;
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
