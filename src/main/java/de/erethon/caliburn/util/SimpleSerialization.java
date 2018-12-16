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
package de.erethon.caliburn.util;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.ExItem;
import static de.erethon.caliburn.util.SimpleSerialization.ItemModifier.*;
import de.erethon.commons.misc.EnumUtil;
import de.erethon.commons.misc.NumberUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * A simple but powerful one-line serialization format for ItemStacks
 *
 * @author Daniel Saukel
 */
public class SimpleSerialization {

    public static final String PREFIX = "item:";
    private CaliburnAPI api;

    public enum ItemModifier {

        ENCHANTMENT("ENCHANTMENT:", "ENCHANT:", "E:"),
        FLAG("ITEMFLAG:", "FLAG:", "F:"),
        LORE("LORE", "L:"),
        NAME("NAME:", "N:"),
        UNBREAKABLE("UNBREAKABLE", "U");

        private String[] prefixes;

        ItemModifier(String... prefixes) {
            this.prefixes = prefixes;
        }

        public String[] getPrefixes() {
            return prefixes;
        }

        public String stripPrefix(String string) {
            String stripped = string;
            for (String prefix : prefixes) {
                stripped = stripped.replace(prefix, "");
            }
            return stripped;
        }

        public static ItemModifier fromString(String string) {
            for (ItemModifier mod : values()) {
                for (String prefix : mod.prefixes) {
                    if (string.toUpperCase().startsWith(prefix)) {
                        return mod;
                    }
                }
            }
            return null;
        }
    }

    public SimpleSerialization(CaliburnAPI api) {
        this.api = api;
    }

    public List<String> serialize(Collection<ItemStack> items) {
        List<String> serialized = new ArrayList<>();
        items.forEach(i -> serialized.add(serialize(i)));
        return serialized;
    }

    public String serialize(ItemStack item) {
        String serialized = PREFIX;
        ExItem baseItem = api.getExItem(item);
        serialized += baseItem.getId();
        if (item.getAmount() != 1) {
            serialized += "," + item.getAmount();
        }
        if (item.getDurability() != 0) {
            serialized += "," + item.getDurability();
        }

        ItemMeta meta = item.getItemMeta();
        for (Entry<Enchantment, Integer> ench : meta.getEnchants().entrySet()) {
            serialized += "," + ENCHANTMENT.getPrefixes()[0] + ench.getKey().getName() + "#" + ench.getValue();
        }
        for (ItemFlag flag : meta.getItemFlags()) {
            serialized += "," + FLAG.getPrefixes()[0] + flag.name();
        }
        if (meta.hasLore()) {
            serialized += "," + LORE.getPrefixes()[0];
            for (String lore : meta.getLore()) {
                serialized += unuseCC(lore);
            }
        }
        if (meta.hasDisplayName()) {
            serialized += "," + NAME.getPrefixes()[0] + unuseCC(meta.getDisplayName());
        }
        if (meta.spigot().isUnbreakable()) {
            serialized += "," + UNBREAKABLE.getPrefixes()[0];
        }
        return serialized;
    }

    public List<ItemStack> deserialize(Collection<String> strings) {
        List<ItemStack> items = new ArrayList<>();
        strings.forEach(s -> items.add(deserialize(s)));
        return items;
    }

    public ItemStack deserialize(String string) {
        if (string.startsWith(PREFIX)) {
            string = string.replaceFirst(PREFIX, "");
        }
        String[] args = string.split(",");
        ExItem baseItem = api.getExItem(args[0]);
        if (baseItem == null) {
            return null;
        }
        int amount = args.length >= 2 ? NumberUtil.parseInt(args[1], 1) : 1;
        ItemStack item = baseItem.toItemStack(amount);

        ItemMeta meta = item.getItemMeta();
        for (String arg : args) {
            if (arg == args[0] || (arg == args[1] && isValidAmount(arg))) {
                continue;
            }

            try {
                short dmg = Short.parseShort(arg);
                item.setDurability(dmg);
                continue;
            } catch (NumberFormatException exception) {
            }

            ItemModifier mod = fromString(arg);
            if (mod == ENCHANTMENT) {
                String[] s = ENCHANTMENT.stripPrefix(arg).split("#");
                Enchantment ench = Enchantment.getByName(s[0]);
                Integer level = NumberUtil.parseInt(s[1], 1);
                if (ench != null && level != null) {
                    meta.addEnchant(ench, level, true);
                }
            } else if (mod == FLAG) {
                ItemFlag flag = EnumUtil.getEnumIgnoreCase(ItemFlag.class, FLAG.stripPrefix(arg));
                if (flag != null) {
                    meta.addItemFlags(flag);
                }
            } else if (mod == LORE) {
                String s = useCC(LORE.stripPrefix(arg));
                if (s.contains("<br>")) {
                    meta.setLore(Arrays.asList(s.split("<br>")));
                } else {
                    meta.setLore(Arrays.asList(s));
                }
            } else if (mod == NAME) {
                meta.setDisplayName(useCC(NAME.stripPrefix(arg)));
            } else if (mod == UNBREAKABLE) {
                meta.spigot().setUnbreakable(true);
            }
        }
        item.setItemMeta(meta);
        return item;
    }

    private boolean isValidAmount(String string) {
        return string.matches("[1-9]") || string.matches("[1-5][0-9]") || string.matches("6[0-4]");
    }

    private String useCC(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    private String unuseCC(String string) {
        return string.replace("\u00a7", "&");
    }

}
