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

import de.erethon.xlib.runtime.XLibRuntime;
import de.erethon.xlib.runtime.ItemBox;
import de.erethon.xlib.compatibility.Version;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class ItemBoxListener implements Listener {

    private XLibRuntime plugin;

    public ItemBoxListener(XLibRuntime plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        ItemStack itemStack;
        if (Version.isAtLeast(Version.MC1_9)) {
            itemStack = event.getPlayer().getInventory().getItemInMainHand();
        } else {
            itemStack = event.getPlayer().getInventory().getItemInHand();
        }
        ItemBox box = ItemBox.getByItemStack(plugin, itemStack);

        if (box != null) {
            box.open(event.getPlayer());
            event.setCancelled(true);
        }
    }

}
