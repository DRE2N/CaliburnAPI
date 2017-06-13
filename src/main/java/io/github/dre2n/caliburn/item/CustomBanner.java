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
import io.github.dre2n.commons.misc.EnumUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class CustomBanner extends CustomItem {

    private DyeColor baseColor;
    private List<Pattern> patterns = new ArrayList<>();

    public CustomBanner(Map<String, Object> args) {
        super(args);

        Object baseColor = args.get("baseColor");
        if (baseColor instanceof String && EnumUtil.isValidEnum(DyeColor.class, config.getString("baseColor"))) {
            this.baseColor = DyeColor.valueOf((String) baseColor);
        }

        Object patterns = args.get("patterns");
        if (patterns instanceof Map) {
            for (Object pattern : ((Map) patterns).entrySet()) {
                DyeColor color = DyeColor.valueOf((String) ((Entry) pattern).getKey());
                PatternType type = PatternType.valueOf((String) ((Entry) pattern).getValue());
                addPattern(color, type);
            }
        }
    }

    public CustomBanner(CaliburnAPI api, String id, Material material, short durability) {
        super(api, id, material, durability);
    }

    public CustomBanner(CaliburnAPI api, String id, CaliConfiguration config) {
        this(config.getArgs());

        this.api = api;
        this.id = id;
        this.config = config;
    }

    /* Getters and setters */
    /**
     * @return
     * the baseColor
     */
    public DyeColor getBaseColor() {
        return baseColor;
    }

    /**
     * @param baseColor
     * the baseColor to set
     */
    public void setBaseColor(DyeColor baseColor) {
        this.baseColor = baseColor;
    }

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
        itemMeta.setBaseColor(getBaseColor());
        itemMeta.setPatterns(getPatterns());

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
