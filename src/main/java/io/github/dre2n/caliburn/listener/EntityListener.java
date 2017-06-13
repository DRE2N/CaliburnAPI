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
package io.github.dre2n.caliburn.listener;

import io.github.dre2n.caliburn.CaliburnAPI;
import io.github.dre2n.caliburn.item.ItemCategory;
import io.github.dre2n.caliburn.item.UniversalItem;
import io.github.dre2n.caliburn.mob.MobCategory;
import io.github.dre2n.caliburn.mob.UniversalMob;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class EntityListener implements Listener {

    CaliburnAPI api;

    public EntityListener(CaliburnAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onDamageByPlayer(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || event.getCause() != DamageCause.ENTITY_ATTACK) {
            return;
        }

        Player player = (Player) event.getDamager();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack.getType() == Material.AIR) {
            return;
        }

        UniversalItem item = api.getItems().getById(api.getItems().getCustomItemId(itemStack));
        UniversalMob mob = api.getMobs().getByEntity(event.getEntity());

        if (item == null || mob == null) {
            return;
        }

        if (item.getMobDamageModifier(mob) == 1) {

            for (MobCategory mobCategory : item.getCategoryDamageModifiers().keySet()) {
                if (mobCategory != null && mobCategory.getMobs().contains(mob)) {
                    event.setDamage(event.getDamage() * item.getCategoryDamageModifier(mobCategory));
                }
            }

        } else {
            event.setDamage(event.getDamage() * item.getMobDamageModifier(mob));
        }

        if (mob.getItemDamageModifier(item) == 1) {

            for (ItemCategory itemCategory : mob.getCategoryDamageModifiers().keySet()) {
                if (itemCategory.getItems().contains(item)) {
                    event.setDamage(event.getDamage() * mob.getCategoryDamageModifier(itemCategory));
                }
            }

        } else {
            event.setDamage(event.getDamage() * mob.getItemDamageModifier(item));
        }
    }

}
