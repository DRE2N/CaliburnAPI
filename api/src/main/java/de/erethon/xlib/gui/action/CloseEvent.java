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
package de.erethon.xlib.gui.action;

import de.erethon.xlib.gui.GUI;
import org.bukkit.entity.Player;

/**
 * Created internally when a player closes a {@link de.erethon.xlib.gui.GUI}.
 *
 * @author Daniel Saukel
 */
public class CloseEvent {

    private GUI gui;
    private Player player;

    public CloseEvent(GUI gui, Player player) {
        this.gui = gui;
        this.player = player;
    }

    /**
     * Returns the GUI that was closed.
     *
     * @return the GUI that was closed
     */
    public GUI getGUI() {
        return gui;
    }

    /**
     * Returns the player who closed the GUI.
     *
     * @return the player who closed the GUI
     */
    public Player getPlayer() {
        return player;
    }

}
