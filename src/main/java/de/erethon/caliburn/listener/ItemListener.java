/*
 * Copyright (C) 2015-2020 Daniel Saukel.
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
package de.erethon.caliburn.listener;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.CustomItem;
import de.erethon.caliburn.item.ExItem;
import de.erethon.caliburn.item.VanillaItem;
import de.erethon.commons.compatibility.Version;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

/**
 * @author Daniel Saukel
 */
public class ItemListener implements Listener {

    private CaliburnAPI api;

    public ItemListener(CaliburnAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        boolean arrow = false;
        if (!(damager instanceof Player)) {
            if (damager instanceof Arrow) {
                arrow = true;
                ProjectileSource shooter = ((Arrow) damager).getShooter();
                if (!(shooter instanceof Player)) {
                    return;
                }
                damager = (Entity) shooter;
            } else {
                return;
            }
        }
        Player player = (Player) damager;

        ItemStack weapon;
        ItemStack mainHand = player.getInventory().getItemInHand();
        if (!arrow || !Version.isAtLeast(Version.MC1_9)) {
            weapon = mainHand;
        } else {
            if (VanillaItem.BOW.is(mainHand)) {
                weapon = mainHand;
            } else {
                weapon = player.getInventory().getItemInOffHand();
            }
        }
        if (weapon == null) {
            return;
        }

        ExItem exItem = api.getExItem(weapon);
        if (!(exItem instanceof CustomItem)) {
            return;
        }
        if (((CustomItem) exItem).hasHitHandler()) {
            ((CustomItem) exItem).getHitHandler().onHit(weapon, player, event.getEntity(), event.getDamage());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        ItemStack stack = event.getItem();
        if (stack == null) {
            return;
        }

        ExItem exItem = api.getExItem(stack);
        if (!(exItem instanceof CustomItem)) {
            return;
        }

        if (((CustomItem) exItem).hasRightClickHandler()) {
            ((CustomItem) exItem).getRightClickHandler().onRightClick(stack, event.getPlayer());
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Item entity = event.getItemDrop();
        ItemStack stack = entity.getItemStack();
        if (stack == null) {
            return;
        }

        ExItem exItem = api.getExItem(stack);
        if (!(exItem instanceof CustomItem)) {
            return;
        }

        if (((CustomItem) exItem).hasDropHandler()) {
            ((CustomItem) exItem).getDropHandler().onDrop(stack, entity, event.getPlayer());
        }
    }

    public class Spigot implements Listener {

        @EventHandler
        public void onPlayerItemDamage(PlayerItemDamageEvent event) {
            ItemStack tool = event.getPlayer().getInventory().getItemInHand();
            ExItem exItem = api.getExItem(tool);
            if (!(exItem instanceof CustomItem)) {
                return;
            }
            boolean broken = tool.getDurability() + event.getDamage() >= tool.getType().getMaxDurability();
            if (((CustomItem) exItem).hasDamageHandler()) {
                ((CustomItem) exItem).getDamageHandler().onDamage(tool, event.getPlayer(), broken);
            }
        }

    }

}
