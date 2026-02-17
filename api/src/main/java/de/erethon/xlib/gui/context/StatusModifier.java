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
package de.erethon.xlib.gui.context;

/**
 * A class that is supposed to be attached to a {@link Contextualized} object.
 * It adds further information to the status of the object.
 * <p>
 * A usage example can be a GUI that is used to set something up
 * where closing it means finishing the setup process.
 * However, some settings might require a submenu opened through a button
 * in the main setup menu for more complex setups.
 * But opening the submenu would also close the main menu and thus trigger
 * the {@link de.erethon.xlib.gui.action.CloseListener} that wouldn't
 * be able to differentiate between when the GUI is closed in favor of a
 * submenu or to finish the setup. A StatusModifier can be added as a
 * marker to make the listener aware of this:
 * <pre>{@code
 * private static final StatusModifier OPENING_SUBMENU = new StatusModifier("OPENING_SUBMENU");
 * ...
 * buttonToOpenSubMenu.addInteractionListener(e -> {
 *     e.getGUI().addStatusModifier(OPENING_SUBMENU);
 *     ...
 * });
 * ...
 * mainMenuGUI.addCloseListener(e -> {
 *     if (e.getGUI().hasStatusModifier(OPENING_SUBMENU)) {
 *         return;
 *     } else ...
 * }
 * }</pre>
 *
 * @param <T> the data type of this modifier
 * @author Daniel Saukel
 */
public class StatusModifier<T> {

    private String key;
    private T value;

    /**
     * Instantiates the StatusModifier without a value.
     * <p>
     * This can be used instead of boolean values where the check that the modifier exists is enough information.
     *
     * @param key the key
     */
    public StatusModifier(String key) {
        this.key = key;
    }

    public StatusModifier(String key, T value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key String used to identify this StatusModifier.
     *
     * @return the key String used to identify this StatusModifier
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the content of this StatusModifier.
     * <p>
     * These values may be used to add further complexity to a status.
     *
     * @return the content of this StatusModifier
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the content of this StatusModifier.
     * <p>
     * These values may be used to add further complexity to a status.
     *
     * @param value the value
     */
    public void setValue(T value) {
        this.value = value;
    }

}
