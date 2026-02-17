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
public class NumberUtil {

    /* Integer */
    /**
     * @param string the String to parse
     * @return the number as an int
     */
    public static int parseInt(String string) {
        return parseInt(string, 0);
    }

    /**
     * @param string        the String to parse
     * @param defaultReturn the value which will be returned if the String is not parsable
     * @return the number as an int
     */
    public static int parseInt(String string, int defaultReturn) {
        int i;
        try {
            i = Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            i = defaultReturn;
        }
        return i;
    }

    /* Double */
    /**
     * @param string the String to parse
     * @return the number as a double
     */
    public static double parseDouble(String string) {
        return parseDouble(string, 0d);
    }

    /**
     * @param string        the String to parse
     * @param defaultReturn the value which will be returned if the String is not parsable
     * @return the number as a double
     */
    public static double parseDouble(String string, double defaultReturn) {
        double d;
        try {
            d = Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            d = defaultReturn;
        }
        return d;
    }

}
