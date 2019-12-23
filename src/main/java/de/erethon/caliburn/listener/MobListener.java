/*
 * Copyright (C) 2015-2019 Daniel Saukel.
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
import de.erethon.caliburn.category.Category;
import de.erethon.caliburn.item.ExItem;
import de.erethon.caliburn.loottable.LootTable;
import de.erethon.caliburn.mob.CustomMob;
import de.erethon.caliburn.mob.ExMob;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * TODO: IMPLEMENT ATTACK HANDLER AND INTERACT HANDLER
 *
 * @author Daniel Saukel
 */
public class MobListener implements Listener {

    private CaliburnAPI api;

    public MobListener(CaliburnAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || event.getCause() != DamageCause.ENTITY_ATTACK) {
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

}
