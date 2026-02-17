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
package de.erethon.xlib.gui.layout;

import de.erethon.xlib.gui.GUI;
import de.erethon.xlib.gui.component.Component;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * Contains methods to arrange the {@link de.erethon.xlib.gui.component.Component}s of a GUI in a certain reproducible way.
 *
 * @param <T> the GUI implementation
 * @author Daniel Saukel
 */
public interface Layout<T extends GUI> {

    /**
     * Returns the GUI attached to this layout
     *
     * @return the GUI attached to this layout
     */
    T getGUI();

    /**
     * Returns a Collection of the Components.
     *
     * @return a Collection of the Components
     */
    Collection<Component<?, T>> getComponents();

    /**
     * Adds a Component to the GUI.
     *
     * @param component the Component to add
     * @return if adding the Component was successful
     */
    boolean add(Component<?, T> component);

    /**
     * Attempts to remove a Component from the GUI.
     * <p>
     * Fails silently if the GUI does not contain it.
     *
     * @param component the Component to remove
     * @return if removing the Component was successful
     */
    boolean remove(Component<?, T> component);

    /**
     * Attempts to remove all Components of the GUI that satisfy the given predicate.
     *
     * @param filter a predicate which returns true for Components to be removed
     */
    default void removeIf(Predicate<? super Component<?, T>> filter) {
        getComponents().stream().filter(filter).forEach(c -> remove(c));
    }

    /**
     * Removes all Components from the GUI.
     */
    void clear();

    /**
     * Returns if there is still space for further components in the {@link GUI} attached to this layout
     *
     * @return if there is still space for further components in this GUI
     */
    boolean hasSpaceLeft();

    /**
     * Returns a copy of the Layout.
     * <p>
     * {@link de.erethon.xlib.gui.component.Component}s are copied as well.
     *
     * @param gui the GUI that is to be attached to the copy
     * @return a copy of the Layout
     */
    Layout<T> copy(T gui);

}
