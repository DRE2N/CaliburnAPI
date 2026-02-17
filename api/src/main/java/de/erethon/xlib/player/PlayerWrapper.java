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
package de.erethon.xlib.player;

import de.erethon.xlib.gui.request.RequestParticipator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public interface PlayerWrapper extends RequestParticipator {

    /**
     * Returns the Bukkit Player object.
     *
     * @return the Bukkit Player object
     */
    Player getPlayer();

    @Override
    default Collection<Player> getRequestPlayers() {
        return new ArrayList<>(Arrays.asList(getPlayer()));
    }

    /**
     * Returns the player's name
     *
     * @return the player's name
     */
    String getName();

    /**
     * Returns the player's unique ID
     *
     * @return the player's unique ID
     */
    UUID getUniqueId();

}
