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
package de.erethon.xlib.util;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

/**
 * Utils only available in 1.13+
 *
 * @author Daniel Saukel
 */
class Bukkit1_13 {

    static Block getAttachedBlock(Block block) {
        if (block.getBlockData() instanceof Directional) {
            Directional data = (Directional) block.getBlockData();
            if (data.getFaces().size() == 4) {
                return block.getRelative(data.getFacing().getOppositeFace());
            }
        }
        return block.getRelative(BlockFace.DOWN);
    }

}
