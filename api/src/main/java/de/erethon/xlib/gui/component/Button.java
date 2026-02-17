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
package de.erethon.xlib.gui.component;

import de.erethon.xlib.gui.GUI;
import de.erethon.xlib.gui.action.InteractionListener;

/**
 * A {@link Component} that allows actions to be performed upon interaction.
 *
 * @param <THIS> the type itself
 * @param <TYPE> the GUI implementation
 * @author Daniel Saukel
 */
public interface Button<THIS extends Button<THIS, TYPE>, TYPE extends GUI> extends Component<THIS, TYPE> {

    /**
     * Returns the formatted title of the button.
     *
     * @return the formatted title of the button
     */
    String getTitle();

    /**
     * Sets the title to a text.
     * <p>
     * The text may be formatted or unformatted.
     *
     * @param text the text to set
     */
    void setTitle(String text);

    /**
     * Returns the sound String played to the player when the button is clicked.
     *
     * @return the sound String played to the player when the button is clicked
     */
    String getSound();

    /**
     * Sets the sound String played to the player when the button is clicked.
     *
     * @param sound the sound String
     */
    void setSound(String sound);

    /**
     * Returns the InteractionListener attached to this button.
     *
     * @return the InteractionListener attached to this button
     */
    InteractionListener getInteractionListener();

    /**
     * Sets the InteractionListener attached to this button.
     *
     * @param listener the listener to set
     */
    void setInteractionListener(InteractionListener listener);

}
