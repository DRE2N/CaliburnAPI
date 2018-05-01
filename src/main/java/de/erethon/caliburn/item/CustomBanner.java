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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class CustomBanner extends CustomItem {

    private List<Pattern> patterns = new ArrayList<>();

    public CustomBanner(Map<String, Object> args) {
        super(args);

        Object patterns = args.get("patterns");
        if (patterns instanceof Map) {
            for (Object pattern : ((Map) patterns).entrySet()) {
                DyeColor color = DyeColor.valueOf((String) ((Entry) pattern).getKey());
                PatternType type = PatternType.valueOf((String) ((Entry) pattern).getValue());
                addPattern(color, type);
            }
        }
    }

    /* Getters and setters */
    /**
     * @return
     * the banner patterns
     */
    public List<Pattern> getPatterns() {
        return patterns;
    }

    /**
     * @param color
     * the color
     * @param type
     * the PatternType
     */
    public void addPattern(DyeColor color, PatternType type) {
        this.patterns.add(new Pattern(color, type));
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
     * the CustomBanner as org.bukkit.inventory.ItemStack
     */
    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = super.toItemStack(amount);

        BannerMeta itemMeta = (BannerMeta) itemStack.getItemMeta();
        itemMeta.setPatterns(getPatterns());
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
