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

import de.erethon.xlib.gui.action.InteractionListener;
import de.erethon.xlib.gui.context.ContextModifier;
import java.util.ArrayList;
import java.util.List;

/**
 * A builder for one-line instantiation of {@link Button}s.
 *
 * @author Daniel Saukel
 * @param <THIS> the ButtonBuilder implementation
 * @param <TYPE> the Button implementation
 */
public abstract class ButtonBuilder<THIS extends ButtonBuilder<THIS, TYPE>, TYPE extends Button> {

    protected String title;
    protected String sound;
    protected InteractionListener interactionListener;
    protected List<ContextModifier<TYPE>> contextModifiers = new ArrayList<>();

    /**
     * Sets the title to a text.
     * <p>
     * The text may be formatted or unformatted.
     *
     * @param text the text to set
     * @return the builder
     */
    public THIS title(String text) {
        title = text;
        return (THIS) this;
    }

    /**
     * Sets the sound String played to the player when the button is clicked
     *
     * @param sound the sound String
     * @return the builder
     */
    public THIS sound(String sound) {
        this.sound = sound;
        return (THIS) this;
    }

    /**
     * Sets the InteractionListener attached to this button.
     *
     * @param listener the listener to set
     * @return the builder
     */
    public THIS onInteract(InteractionListener listener) {
        interactionListener = listener;
        return (THIS) this;
    }

    /**
     * Adds a context modifier to the button.
     *
     * @param ctxt the context modifier
     * @return the builder
     */
    public THIS contextModifier(ContextModifier<TYPE> ctxt) {
        contextModifiers.add(ctxt);
        return (THIS) this;
    }

    /**
     * Builds a {@link Button}.
     *
     * @throws IllegalStateException if obligatory attributes have not been set
     * @return the built {@link Button}
     */
    public abstract TYPE build();

}
