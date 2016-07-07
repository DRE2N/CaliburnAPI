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
package io.github.dre2n.caliburn.item;

import io.github.dre2n.caliburn.CaliburnAPI;
import io.github.dre2n.commons.util.EnumUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class CustomBanner extends CustomItem {

    private DyeColor baseColor;
    private List<Pattern> patterns = new ArrayList<>();

    public CustomBanner(CaliburnAPI api, String id, Material material, short durability) {
        super(api, id, material, durability);
    }

    public CustomBanner(CaliburnAPI api, String id, ConfigurationSection config) {
        super(api, id, config);

        if (config.contains("baseColor") && EnumUtil.isValidEnum(DyeColor.class, config.getString("baseColor"))) {
            baseColor = DyeColor.valueOf(config.getString("baseColor"));
        }

        if (config.contains("patterns")) {
            Map<String, Object> patterns = config.getConfigurationSection("patterns").getValues(false);
            for (Entry<String, Object> pattern : patterns.entrySet()) {
                DyeColor color = DyeColor.valueOf(pattern.getKey());
                PatternType type = PatternType.valueOf((String) pattern.getValue());
                addPattern(color, type);
            }
        }
    }

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
