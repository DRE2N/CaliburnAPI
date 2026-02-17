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
package de.erethon.xlib.gui;

import de.erethon.xlib.XLib;
import de.erethon.xlib.gui.action.CloseListener;
import de.erethon.xlib.gui.component.Component;
import de.erethon.xlib.gui.context.Contextualized;
import de.erethon.xlib.gui.layout.Layout;
import de.erethon.xlib.gui.request.RequestParticipator;
import java.util.Collection;
import java.util.function.Predicate;
import org.bukkit.entity.Player;

/**
 * Represents a graphical user interface.
 *
 * @param <T> the type itself
 * @author Daniel Saukel
 */
public interface GUI<T extends GUI<T>> extends Contextualized<T> {

    /**
     * Returns the title text.
     *
     * @return the title text
     */
    String getTitle();

    /**
     * Sets the title to a text.
     *
     * @param text the text to set
     */
    void setTitle(String text);

    /**
     * Returns a Collection of the Components.
     *
     * @return a Collection of the Components
     */
    default Collection<Component<?, T>> getComponents() {
        if (getLayout() == null) {
            throw new IllegalStateException("The GUI " + toString() + "has no layout set");
        }
        return getLayout().getComponents();
    }

    /**
     * Adds a Component to the GUI.
     *
     * @param component the Component to add
     * @return if adding the Component was successful
     */
    default boolean add(Component<?, T> component) {
        if (getLayout() == null) {
            throw new IllegalStateException("The GUI " + toString() + " has no layout set");
        }
        return getLayout().add(component);
    }

    /**
     * Attempts to remove a Component from the GUI.
     * <p>
     * Fails silently if the GUI does not contain it.
     *
     * @param component the Component to remove
     * @return if removing the Component was successful
     */
    default boolean remove(Component<?, T> component) {
        if (getLayout() == null) {
            throw new IllegalStateException("The GUI " + toString() + "has no layout set");
        }
        return getLayout().remove(component);
    }

    /**
     * Attempts to remove all Components of the GUI that satisfy the given predicate.
     *
     * @param filter a predicate which returns true for Components to be removed
     */
    default void removeIf(Predicate<? super Component<?, T>> filter) {
        if (getLayout() == null) {
            throw new IllegalStateException("The GUI " + toString() + "has no layout set");
        }
        getLayout().removeIf(filter);
    }

    /**
     * Removes all Components from the GUI.
     */
    default void clear() {
        if (getLayout() == null) {
            throw new IllegalStateException("The GUI " + toString() + "has no layout set");
        }
        getLayout().clear();
    }

    /**
     * Returns if there is still space for further components in this GUI
     *
     * @return if there is still space for further components in this GUI
     */
    default boolean hasSpaceLeft() {
        if (getLayout() == null) {
            throw new IllegalStateException("The GUI " + toString() + "has no layout set");
        }
        return getLayout().hasSpaceLeft();
    }

    /**
     * Returns the Layout.
     *
     * @return the Layout
     */
    Layout<T> getLayout();

    /**
     * Sets the Layout.
     *
     * @param layout the Layout to set
     */
    void setLayout(Layout<T> layout);

    /**
     * Returns the CloseListener attached to this GUI.
     *
     * @return the CloseListener attached to this GUI
     */
    CloseListener getCloseListener();

    /**
     * Sets the CloseListener attached to this GUI.
     *
     * @param listener the listener to set
     */
    void setCloseListener(CloseListener listener);

    /**
     * If this GUI shall be unregistered automatically when closed.
     *
     * @return if this GUI shall be unregistered automatically when closed.
     */
    boolean isTransient();

    /**
     * Sets if this GUI shall be unregistered automatically when closed.
     *
     * @param isTransient if this GUI shall be unregistered automatically when closed.
     */
    void setTransient(boolean isTransient);

    /**
     * Returns a transient copy if the GUI has context modifiers and applies all of them; returns the GUI itself if not.
     *
     * @param viewer the viewer
     * @return a transient copy if the GUI has context modifiers and applies all of them; returns the GUI itself if not
     */
    GUI getContextualizedCopy(Player viewer);

    /**
     * Opens the GUI to a player and adds him to the viewers Collection.
     * <p>
     * Fails silently if the player is not online.
     * <p>
     * Triggers all associated {@link de.erethon.xlib.gui.context.ContextModifier}s.
     *
     * @throws IllegalStateException if the GUI is not registered
     * @param player the Player
     * @return the opened GUI. It might be a copy of this object depending on the implementation
     */
    T open(Player player);

    /**
     * Opens the GUI to an array of players and adds them to the viewers Collection.
     * <p>
     * Ignores Players who are not online.
     * <p>
     * Triggers all associated {@link de.erethon.xlib.gui.context.ContextModifier}s.
     *
     * @throws IllegalStateException if the GUI is not registered
     * @param players the Players
     */
    default void open(Player... players) {
        for (Player player : players) {
            open(player);
        }
    }

    /**
     * Opens the GUI to all players represented by an array of RequestParticipators and adds them to the viewers Collection.
     * <p>
     * Ignores Players who are not online.
     * <p>
     * Triggers all associated {@link de.erethon.xlib.gui.context.ContextModifier}s
     *
     * @throws IllegalStateException if the GUI is not registered
     * @param requestParticipators the RequestParticipators
     */
    default void open(RequestParticipator... requestParticipators) {
        for (RequestParticipator rp : requestParticipators) {
            Collection<Player> players = rp.getRequestPlayers();
            open(players.toArray(new Player[players.size()]));
        }
    }

    /**
     * Returns a Collection of the Players viewing the GUI.
     * <p>
     * To remove Players from this Collection, use {@link #close(Player...)}.
     *
     * @return the Players who are viewing the GUI
     */
    Collection<Player> getViewers();

    /**
     * Closes the GUI to an array of Players.
     * <p>
     * If the array is empty, the GUI is closed to all players.
     * <p>
     * Ignores Players who are not online.
     *
     * @param players the Players to remove from the viewers' Collection
     */
    void close(Player... players);

    /**
     * Starts tracking the GUI.
     */
    default void register() {
        XLib.getInstance().register(this);
    }

    /**
     * Stops tracking the GUI.
     */
    default void unregister() {
        XLib.getInstance().unregister(this);
    }

    /**
     * Returns if the GUI is registered.
     *
     * @return if the GUI is registered
     */
    default boolean isRegistered() {
        return XLib.getInstance().isRegistered(this);
    }

    /**
     * Returns an exact copy of the GUI.
     *
     * @return an exact copy of the GUI
     */
    @Override
    T copy();

}
