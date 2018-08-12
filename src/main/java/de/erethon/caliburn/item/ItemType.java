/*
 * Copyright (C) 2015-2018 Daniel Saukel.
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
package de.erethon.caliburn.item;

import de.erethon.commons.chat.MessageUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daniel Saukel
 */
public class ItemType<T extends CustomItem> {

    public static final Map<String, ItemType> REGISTERED = new HashMap<>();

    public static final ItemType DEFAULT = new ItemType<CustomItem>("DEFAULT", CustomItem.class);
    public static final ItemType BANNER = new ItemType<CustomBanner>("BANNER", CustomBanner.class);
    public static final ItemType ENCHANTED_BOOK = new ItemType<CustomEnchantedBook>("ENCHANTED_BOOK", CustomEnchantedBook.class);
    public static final ItemType EQUIPMENT = new ItemType<CustomEquipment>("EQUIPMENT", CustomEquipment.class);
    public static final ItemType FIREWORK = new ItemType<CustomFirework>("FIREWORK", CustomFirework.class);
    public static final ItemType HEAD = new ItemType<CustomHead>("HEAD", CustomHead.class);

    private Class<? extends ExItem> handler;

    public ItemType(String identifier, Class<? extends CustomItem> handler) {
        REGISTERED.put(identifier, this);
        this.handler = handler;
    }

    /**
     * Used for deserialization
     *
     * @return a new instance of the handler class
     */
    public T instantiate(Map<String, Object> args) {
        try {
            Constructor<T> constructor = (Constructor<T>) handler.getConstructor(Map.class);
            return constructor.newInstance(args);

        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException exception) {
            MessageUtil.log("An error occurred while accessing the handler class of the item type " + toString() + ": " + exception.getClass().getSimpleName());
            exception.printStackTrace();
            return null;
        }
    }

}
