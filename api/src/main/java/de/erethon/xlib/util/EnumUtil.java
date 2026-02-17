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

/**
 * @author Daniel Saukel
 */
public class EnumUtil {

    /**
     * Returns true if an enum value of the given name exists in the given enum; false if not.
     *
     * @param <E>       the enum
     * @param enumClass the enum
     * @param valueName the name of the enum value
     * @return if the enum value with this name is valid
     */
    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String valueName) {
        return getEnum(enumClass, valueName) != null;
    }

    /**
     * Returns the enum value of the given name if it exists in the given enum; null if not.
     * <p>
     * Ignores case.
     *
     * @param <E>       the enum
     * @param enumClass the enum
     * @param valueName the name of the enum value
     * @return the enum value if it exists. Not case-sensitive
     */
    public static <E extends Enum<E>> E getEnumIgnoreCase(Class<E> enumClass, String valueName) {
        return getEnum(enumClass, valueName.toUpperCase());
    }

    /**
     * Returns the enum value of the given name if it exists in the given enum; null if not.
     *
     * @param <E>       the enum
     * @param enumClass the enum
     * @param valueName the name of the enum value
     * @return the enum value if it exists
     */
    public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String valueName) {
        if (enumClass == null || valueName == null) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, valueName);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

}
