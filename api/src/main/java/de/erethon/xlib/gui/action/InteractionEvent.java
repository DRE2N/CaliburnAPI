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
import de.erethon.xlib.gui.component.Button;
import org.bukkit.entity.Player;

/**
 * Created internally when a player interacts with a GUI {@link de.erethon.xlib.gui.component.Component}.
 *
 * @author Daniel Saukel
 */
public class InteractionEvent {

    private GUI gui;
    private Button button;
    private Player player;
    private Action action;
    private boolean clickCancelled;

    public InteractionEvent(GUI gui, Button button, Player player, Action action) {
        this.gui = gui;
        this.button = button;
        this.player = player;
        this.action = action;
    }

    /**
     * Returns the GUI that was interacted with.
     *
     * @return the GUI that was interacted with
     */
    public GUI getGUI() {
        return gui;
    }

    /**
     * Returns the Button that was interacted with.
     *
     * @return the Button that was interacted with
     */
    public Button getButton() {
        return button;
    }

    /**
     * Returns the player who interacted with the GUI.
     *
     * @return the player who interacted with the GUI
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns an Action that matchs the action that occurred.
     *
     * @return an Action that matchs the action that occurred
     */
    public Action getAction() {
        return action;
    }

    /**
     * Returns if the click itself is cancelled after this event is called.
     *
     * @see #setClickCancelled(boolean)
     * @return if the click itself is cancelled after this event is called.
     */
    public boolean isClickCancelled() {
        return clickCancelled;
    }

    /**
     * Sets if the click itself is cancelled after this event is cancelled.
     *
     * @param cancelled if the click itself is cancelled after this event is cancelled
     */
    public void setClickCancelled(boolean cancelled) {
        clickCancelled = cancelled;
    }

}
