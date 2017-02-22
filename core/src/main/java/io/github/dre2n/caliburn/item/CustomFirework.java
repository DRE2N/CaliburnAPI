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
import io.github.dre2n.commons.util.EnumUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class CustomFirework extends CustomItem {

    private int power;
    private List<FireworkEffect> effects = new ArrayList<>();

    public CustomFirework(Map<String, Object> args) {
        super(args);

        Object power = args.get("power");
        if (power instanceof Integer) {
            setPower((Integer) power);
        }

        Object effects = args.get("effects");
        if (effects instanceof List) {
            for (Object object : (List) effects) {
                if (!(object instanceof Map)) {
                    return;
                }
                Map entry = (Map) object;

                Object typeObj = entry.get("type");
                FireworkEffect.Type type = null;

                if (typeObj instanceof String && EnumUtil.isValidEnum(FireworkEffect.Type.class, (String) typeObj)) {
                    type = FireworkEffect.Type.valueOf((String) typeObj);
                }

                if (type == null) {
                    continue;
                }

                Object colorsObj = entry.get("colors");
                List<Color> colors = new ArrayList<>();
                if (colorsObj instanceof List) {
                    for (Object color : (List) colorsObj) {
                        if (color instanceof Integer) {
                            colors.add(Color.fromBGR((Integer) color));
                        }
                    }
                }

                Object faceColorsObj = entry.get("fadeColors");
                List<Color> fadeColors = new ArrayList<>();
                if (faceColorsObj instanceof List) {
                    for (Object color : (List) faceColorsObj) {
                        if (color instanceof Integer) {
                            fadeColors.add(Color.fromBGR((Integer) color));
                        }
                    }
                }

                Object hasFlickerObj = entry.get("hasFlicker");
                boolean hasFlicker = false;
                if (hasFlickerObj instanceof Boolean) {
                    hasFlicker = (Boolean) hasFlickerObj;
                }

                Object hasTrailObj = entry.get("hasTrail");
                boolean hasTrail = false;
                if (hasTrailObj instanceof Boolean) {
                    hasTrail = (Boolean) hasTrailObj;
                }

                FireworkEffect effect = FireworkEffect.builder().flicker(hasFlicker).trail(hasTrail).withFade(fadeColors).withColor(colors).with(type).build();
                addEffect(effect);
            }
        }
    }

    public CustomFirework(CaliburnAPI api, String id, Material material, short durability) {
        super(api, id, material, durability);
    }

    public CustomFirework(CaliburnAPI api, String id, CaliConfiguration config) {
        this(config.getArgs());

        this.api = api;
        this.id = id;
        this.config = config;
    }

    /* Getters and setters */
    /**
     * @return
     * the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @param power
     * the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @return
     * the effects
     */
    public List<FireworkEffect> getEffects() {
        return effects;
    }

    /**
     * @param effects
     * the effect to add
     */
    public void addEffect(FireworkEffect effect) {
        this.effects.add(effect);
    }

    /**
     * @param effects
     * the effects to add
     */
    public void addEffect(List<FireworkEffect> effects) {
        this.effects.addAll(effects);
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
     * the CustomFirework as org.bukkit.inventory.ItemStack
     */
    @Override
    public ItemStack toItemStack(int amount) {
        ItemStack itemStack = super.toItemStack(amount);

        FireworkMeta itemMeta = (FireworkMeta) itemStack.getItemMeta();
        itemMeta.setPower(getPower());
        itemMeta.addEffects(getEffects());

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
