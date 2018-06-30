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
package de.erethon.caliburn.listener;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.CustomItem;
import de.erethon.caliburn.item.ExItem;
import net.sothatsit.blockstore.BlockStoreApi;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author Daniel Saukel
 */
public class BlockListener implements Listener {

    private CaliburnAPI api;

    public BlockListener(CaliburnAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ExItem item = api.getExItem(event.getItemInHand());
        if (!(item instanceof CustomItem)) {
            return;
        }
        Block block = item.toBlock(event.getBlock());
        String value = api.getSimpleSerialization().serialize(item.toItemStack());
        BlockStoreApi.setBlockMeta(block, api.getImplementation(), CaliburnAPI.META_KEY, value);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        ExItem item = api.getExItem(block);
        if (!(item instanceof CustomItem)) {
            return;
        }
        event.setDropItems(false);
        block.getWorld().dropItemNaturally(block.getLocation(), item.toItemStack());
    }

}
