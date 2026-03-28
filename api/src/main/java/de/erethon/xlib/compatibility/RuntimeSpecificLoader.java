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
package de.erethon.xlib.compatibility;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import org.bukkit.Bukkit;

/**
 * @author Daniel Saukel
 */
public class RuntimeSpecificLoader {

    /**
     * Loads a class that provides implementation details that depend on the runtime. If available, a Paper implementation is preferred over NMS/OBC.
     *
     * @param <T>                the superclass of the internals provider
     * @param superclass         the superclass of the internals provider; not null
     * @param preferPaperMinimum the minimum version when a class called PaperAPIProvider in the same package should be preferred over implementation-specifics
     * @param preferNMSMaximum   the maximum version until when a class named after the loaded OBC/NMS package relocation should be preferred.
     * @param fallback           a fallback internals provider that only uses Spigot API available on all supported platforms
     * @return an instance of T that fits the current runtime best
     */
    public static <T> T loadImplementation(Class<T> superclass, Version preferPaperMinimum, Version preferNMSMaximum, T fallback) {
        String className = superclass.getPackage().getName() + ".";
        if (Version.isAtLeast(preferPaperMinimum) && RuntimeType.get() == RuntimeType.PAPER) {
            className += "PaperAPIProvider";
        } else if (Version.isAtMost(preferNMSMaximum)) {
            className += Version.get().getRelocationTarget();
        } else if (fallback != null) {
            return fallback;
        } else {
            throw new RuntimeException("XLib is not supported on this server.");
        }
        try {
            Constructor constructor = Class.forName(className).getDeclaredConstructor();
            constructor.setAccessible(true);
            return (T) constructor.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException
                | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException exception) {
            Bukkit.getLogger().log(Level.SEVERE, "XLib could not find a valid implementation for {0}", className);
            exception.printStackTrace();
            return fallback;
        }
    }

}
