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

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Represents a cache or registry of objects.
 *
 * @param <K> the key type
 * @param <E> the element type
 * @author Daniel Saukel
 */
public class Registry<K, E> implements Iterable<E> {

    protected BiMap<K, E> elements = HashBiMap.<K, E>create();

    /**
     * Returns the element registered under the given key.
     *
     * @param key the key to check
     * @return the element registered under the given key
     */
    public E get(K key) {
        return elements.get(key);
    }

    /**
     * Returns the first element that satisfies the given predicate.
     *
     * @param predicate the predicate to check
     * @return the first element that satisfies the given predicate
     */
    public E getFirstIf(Predicate<E> predicate) {
        for (E element : elements.values()) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return null;
    }

    /**
     * Returns all elements that satisfy the given predicate.
     *
     * @param predicate the predicate to check
     * @return all elements that satisfy the given predicate
     */
    public Collection<E> getAllIf(Predicate<E> predicate) {
        Collection<E> checked = new ArrayList<>();
        for (E element : elements.values()) {
            if (predicate.test(element)) {
                checked.add(element);
            }
        }
        return checked;
    }

    /**
     * Returns all elements.
     *
     * @return all elements
     */
    public Collection<E> getAll() {
        return new HashSet<>(elements.values());
    }

    /**
     * Returns true if the Registry contains the key, false if not.
     *
     * @param key the key
     * @return true if the Registry contains the key, false if not
     */
    public boolean containsKey(K key) {
        return elements.containsKey(key);
    }

    /**
     * Returns true if the Registry contains the element, false if not.
     *
     * @param element the element
     * @return true if the Registry contains the element, false if not
     */
    public boolean contains(E element) {
        return elements.containsValue(element);
    }

    /**
     * Adds an element to the Registry.
     *
     * @param key     the key of the element
     * @param element the element
     */
    public void add(K key, E element) {
        elements.put(key, element);
    }

    /**
     * Removes the element that matchs the given key from the Registry.
     *
     * @param key key of the element to remove
     */
    public void removeKey(K key) {
        elements.remove(key);
    }

    /**
     * Removes an element from the Registry.
     *
     * @param element the element to remove
     */
    public void remove(E element) {
        elements.values().remove(element);
    }

    /**
     * Returns the Set of entries in the Registry.
     *
     * @return the Set of entries in the Registry
     */
    public Set<Entry<K, E>> entrySet() {
        return elements.entrySet();
    }

    /**
     * Returns a Stream of all elements.
     *
     * @return a Stream of all elements
     */
    public Stream<E> stream() {
        return elements.values().stream();
    }

    /**
     * Returns an Iterator of the elements.
     *
     * @return an Iterator of the elements
     */
    @Override
    public Iterator<E> iterator() {
        return elements.values().iterator();
    }

    /**
     * Returns the amount of elements.
     *
     * @return the amount of elements
     */
    public int size() {
        return elements.size();
    }

}
