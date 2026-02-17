/*
 * Copyright (C) 2015-2022 Daniel Saukel.
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
package de.erethon.caliburn.exampleplugin;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.category.IdentifierType;
import de.erethon.caliburn.item.CustomAttribute;
import de.erethon.caliburn.item.CustomItem;
import de.erethon.caliburn.item.TrackedItemStack;
import de.erethon.caliburn.item.VanillaItem;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Daniel Saukel
 */
public class ExamplePlugin extends JavaPlugin {

    CaliburnAPI api;

    static final String LEGENDARY = ChatColor.GOLD + "legendary";

    CustomItem legendarySword;
    CustomAttribute<String> rarity;
    CustomAttribute chicken;

    CustomItem legendarySword() {
        if (legendarySword == null) {
            legendarySword = new CustomItem(api, IdentifierType.PERSISTENT_DATA_CONTAINER, "legendary_sword", VanillaItem.DIAMOND_SWORD);
            legendarySword.setCustomModelData(1);
            legendarySword.addLore(ChatColor.ITALIC + "*glisten*");
            // Static attribute
            legendarySword.addAttribute(rarity(), LEGENDARY);
        }
        return legendarySword;
    }

    CustomAttribute<String> rarity() {
        if (rarity == null) {
            rarity = new CustomAttribute<>((i, s) -> {
                ItemStack wrapped = s.getWrapped();
                ItemMeta meta = wrapped.getItemMeta();
                List<String> lore = meta.getLore();
                lore.add(ChatColor.GREEN + "Rarity: " + ChatColor.GRAY + i.getValue());
                meta.setLore(lore);
                wrapped.setItemMeta(meta);
            });
            rarity.setHitHandler((a, w, d, e) -> {
                if (!a.getValue().equals(LEGENDARY)) {
                    return;
                }
                Location location = e.getEntity().getLocation();
                World world = location.getWorld();
                double r = 0f, g = 1f, b = 0f;
                world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 2, 0), 0, r, g, b);
                world.spawnParticle(Particle.REDSTONE, location.clone().add(1, 1.75, 0), 0, r, g, b);
                world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 1.5, 1), 0, r, g, b);
                world.spawnParticle(Particle.REDSTONE, location.clone().add(-1, 1.25, 0), 0, r, g, b);
                world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 1, -1), 0, r, g, b);
            });
        }
        return rarity;
    }

    CustomAttribute chicken() {
        if (chicken == null) {
            chicken = new CustomAttribute<>((i, s) -> {
                ItemStack wrapped = s.getWrapped();
                ItemMeta meta = wrapped.getItemMeta();
                List<String> lore = meta.getLore();
                lore.add(ChatColor.ITALIC + "*suspicious cock-a-doodle-doo*");
                meta.setLore(lore);
                wrapped.setItemMeta(meta);
            });
            chicken.setDropHandler((a, e) -> {
                Item drop = e.getItemDrop();
                drop.remove();
                drop.getLocation().getWorld().spawnEntity(drop.getLocation(), EntityType.CHICKEN);
            });
        }
        return chicken;
    }

    @Override
    public void onEnable() {
        api = CaliburnAPI.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command may only be used by an in-game player.");
            return true;
        }

        ItemStack item = legendarySword().toItemStack();
        TrackedItemStack tracked = api.wrap(legendarySword(), item);
        tracked.addAttribute(chicken, null);
        ((Player) sender).getInventory().addItem(item);
        return true;
    }

}
