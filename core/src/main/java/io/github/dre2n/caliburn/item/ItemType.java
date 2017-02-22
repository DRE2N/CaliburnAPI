/*
 * Copyright (C) 2015-2017 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.caliburn.item;

import io.github.dre2n.caliburn.CaliburnAPI;
import io.github.dre2n.caliburn.util.CaliConfiguration;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Daniel Saukel
 */
public enum ItemType {

    CUSTOM_DEFAULT(CustomItem.class),
    CUSTOM_BANNER(CustomBanner.class),
    CUSTOM_ENCHANTED_BOOK(CustomEnchantedBook.class),
    CUSTOM_EQUIPMENT(CustomEquipment.class),
    CUSTOM_FIREWORK(CustomFirework.class),
    CUSTOM_HEAD(CustomHead.class),
    UNIVERSAL(UniversalItem.class);

    private Class<? extends UniversalItem> handler;

    ItemType(Class<? extends UniversalItem> handler) {
        this.handler = handler;
    }

    /**
     * @return
     * the handler
     */
    public Class<? extends UniversalItem> getHandler() {
        return handler;
    }

    /**
     * @return
     * a new instance of the handler class
     */
    public UniversalItem instantiate(CaliburnAPI api, String id, CaliConfiguration config) {
        try {
            Constructor<? extends UniversalItem> constructor = handler.getConstructor(CaliburnAPI.class, String.class, CaliConfiguration.class);
            return constructor.newInstance(api, id, config);

        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            MessageUtil.log("An error occurred while accessing the handler class of the item type " + toString() + ": " + exception.getClass().getSimpleName());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Used for deserialization
     *
     * @return
     * a new instance of the handler class
     */
    public UniversalItem instantiate(Map<String, Object> args) {
        try {
            Constructor<? extends UniversalItem> constructor = handler.getConstructor(Map.class);
            return constructor.newInstance(args);

        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            MessageUtil.log("An error occurred while accessing the handler class of the item type " + toString() + ": " + exception.getClass().getSimpleName());
            exception.printStackTrace();
            return null;
        }
    }

}
