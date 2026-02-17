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
package de.erethon.xlib.runtime.listener;

import de.erethon.xlib.XLib;
import de.erethon.xlib.category.Category;
import de.erethon.xlib.item.ExItem;
import de.erethon.xlib.loottable.LootTable;
import de.erethon.xlib.mob.CustomMob;
import de.erethon.xlib.mob.ExMob;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class MobListener implements Listener {

    private XLib api;

    public MobListener(XLib api) {
        this.api = api;
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        ExMob damager = api.getExMob(event.getDamager());
        if (damager instanceof CustomMob && ((CustomMob) damager).hasAttackHandler()) {
            ((CustomMob) damager).getAttackHandler().onAttack(event.getDamager(), event.getEntity());
        }

        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();
        ItemStack itemStack = player.getInventory().getItemInHand();

        if (itemStack.getType() == Material.AIR) {
            return;
        }

        ExItem item = api.getExItem(itemStack);
        ExMob mob = api.getExMob(event.getEntity());

        if (item == null || mob == null) {
            return;
        }

        if (item.getMobDamageModifier(mob) == 1) {

            for (Category<ExMob> mobCategory : item.getCategoryDamageModifiers().keySet()) {
                if (mobCategory != null && mobCategory.getElements().contains(mob)) {
                    event.setDamage(event.getDamage() * item.getCategoryDamageModifier(mobCategory));
                }
            }

        } else {
            event.setDamage(event.getDamage() * item.getMobDamageModifier(mob));
        }

        if (mob.getItemDamageModifier(item) == 1) {

            for (Category<ExItem> itemCategory : mob.getCategoryDamageModifiers().keySet()) {
                if (itemCategory.getElements().contains(item)) {
                    event.setDamage(event.getDamage() * mob.getCategoryDamageModifier(itemCategory));
                }
            }

        } else {
            event.setDamage(event.getDamage() * mob.getItemDamageModifier(item));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        ExMob exMob = api.getExMob(entity);
        if (!(exMob instanceof CustomMob)) {
            return;
        }

        CustomMob cMob = (CustomMob) exMob;
        Entity attacker = event instanceof EntityDamageByEntityEvent ? ((EntityDamageByEntityEvent) event).getDamager() : null;

        if (cMob.hasDamageHandler()) {
            cMob.getDamageHandler().onDamage(entity, event.getCause(), event.getDamage(), attacker);
        }

        if (!cMob.hasDeathHandler() || !(entity instanceof LivingEntity)) {
            return;
        }
        if (event.getDamage() > ((LivingEntity) entity).getHealth()) {
            cMob.getDeathHandler().onDeath(entity, event.getCause(), attacker);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        ExMob exMob = api.getExMob(event.getEntity());
        if (!(exMob instanceof CustomMob)) {
            return;
        }
        LootTable drops = ((CustomMob) exMob).getDrops();
        if (drops == null) {
            return;
        }
        event.getDrops().clear();
        event.getDrops().addAll(drops.generateLootList());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        ExMob mob = api.getExMob(event.getRightClicked());
        if (mob instanceof CustomMob && ((CustomMob) mob).hasInteractHandler()) {
            ((CustomMob) mob).getInteractHandler().onInteract(event.getRightClicked(), event.getPlayer());
        }
    }

}
