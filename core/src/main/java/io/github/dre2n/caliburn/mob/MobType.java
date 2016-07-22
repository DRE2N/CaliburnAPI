/*
 * Copyright (C) 2016 Daniel Saukel
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
package io.github.dre2n.caliburn.mob;

import io.github.dre2n.caliburn.CaliburnAPI;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author Daniel Saukel
 */
public enum MobType {

    CUSTOM_DEFAULT(CustomMob.class),
    UNIVERSAL(UniversalMob.class);

    private Class<? extends UniversalMob> handler;

    MobType(Class<? extends UniversalMob> handler) {
        this.handler = handler;
    }

    /**
     * @return
     * the handler
     */
    public Class<? extends UniversalMob> getHandler() {
        return handler;
    }

    /**
     * @return
     * a new instance of the handler class
     */
    public UniversalMob instantiate(CaliburnAPI api, String id, ConfigurationSection config) {
        try {
            Constructor<? extends UniversalMob> constructor = handler.getConstructor(CaliburnAPI.class, String.class, ConfigurationSection.class);
            return constructor.newInstance(api, id, config);

        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            MessageUtil.log("An error occurred while accessing the handler class of the mob type " + toString() + ": " + exception.getClass().getSimpleName());
            return null;
        }
    }

}
