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

import java.util.List;
import java.util.Set;
import org.bukkit.entity.Player;

/**
 * Represents an object that can be modified by {@link ContextModifier}s.
 *
 * @param <T> the type itself
 * @author Daniel Saukel
 */
public interface Contextualized<T extends Contextualized<T>> {

    /**
     * Returns the {@link ContextModifier}s.
     * <p>
     * {@link #applyAllContextModifiers(org.bukkit.entity.Player)} applies
     * the modifiers from the lowest to the highest index.
     *
     * @return the {@link ContextModifier}s
     */
    List<ContextModifier<T>> getContextModifiers();

    /**
     * Adds a {@link ContextModifier} to the contextualized object.
     *
     * @param ctxt the modifier to add
     */
    void addContextModifier(ContextModifier<T> ctxt);

    /**
     * Removes a {@link ContextModifier} from the contextualized object.
     *
     * @param ctxt the modifier to remove
     */
    void removeContextModifier(ContextModifier<T> ctxt);

    /**
     * Sets the {@link ContextModifier}s.
     *
     * @param ctxts the modifiers to set
     */
    void setContextModifiers(List<ContextModifier<T>> ctxts);

    /**
     * Applies all {@link ContextModifier}s from the lowest to the highest
     * index of {@link #getContextModifiers()} to the contextualized object.
     * <p>
     * Consider making a {@link #copy()} first as this changes the object's state.
     *
     * @param player the player
     */
    default void applyAllContextModifiers(Player player) {
        getContextModifiers().forEach(m -> m.apply((T) this, player));
    }

    /**
     * Returns if the object has the given {@link StatusModifier}.
     *
     * @param status the modifier
     * @return if the object has the given {@link StatusModifier}
     */
    boolean hasStatusModifier(StatusModifier<?> status);

    /**
     * Returns if the object has a {@link StatusModifier} with the given key.
     *
     * @param key the key
     * @return if the object has a {@link StatusModifier} with the given key
     */
    default boolean hasStatusModifier(String key) {
        return getStatusModifier(key) != null;
    }

    /**
     * Returns the {@link StatusModifier}s.
     *
     * @return the {@link StatusModifier}s
     */
    Set<StatusModifier<?>> getStatusModifiers();

    /**
     * Returns the {@link StatusModifier} with the given key.
     *
     * @param key the key
     * @return the {@link StatusModifier} with the given key
     */
    default StatusModifier<?> getStatusModifier(String key) {
        for (StatusModifier<?> status : getStatusModifiers()) {
            if (status.getKey().equals(key)) {
                return status;
            }
        }
        return null;
    }

    /**
     * Adds a {@link StatusModifier}.
     *
     * @param status the modifier to add
     */
    void addStatusModifier(StatusModifier<?> status);

    /**
     * Removes a {@link StatusModifier}.
     *
     * @param status the modifier to remove
     */
    void removeStatusModifier(StatusModifier<?> status);

    /**
     * Removes all {@link StatusModifier}s that have the given key.
     *
     * @param key the key
     */
    default void removeStatusModifier(String key) {
        getStatusModifiers().removeIf(s -> s.getKey().equals(key));
    }

    /**
     * Sets the {@link StatusModifier}s.
     *
     * @param status the modifiers to set
     */
    void setStatusModifiers(Set<StatusModifier<?>> status);

    /**
     * Returns an exact copy of the contextualized object.
     *
     * @return an exact copy of the contextualized object
     */
    Contextualized<T> copy();

}
