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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Daniel Saukel
 */
public class SimpleDateUtil {

    public static String ddMMMMyyyyhhmmss(Date date) {
        return new SimpleDateFormat("dd. MMMM yyyy hh:mm:ss").format(date);
    }

    public static String ddMMMMyyyyhhmmss(long date) {
        return ddMMMMyyyyhhmmss(new Date(date));
    }

    public static String ddMMyyyyhhmm(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy hh:mm").format(date);
    }

    public static String ddMMyyyyhhmm(long date) {
        return ddMMyyyyhhmm(new Date(date));
    }

    public static String ddMMyyyy(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public static String ddMMyyyy(long date) {
        return ddMMyyyy(new Date(date));
    }

    public static String format(Date date, String formatting) {
        return new SimpleDateFormat(formatting).format(date);
    }

    public static String format(long date, String formatting) {
        return format(new Date(date), formatting);
    }

    /**
     * Converts a decimal double to an array of rounded sexagesimal ints.
     *
     * @param decimal the decimal
     * @param digits  digits in the sexagesimal number; greater than 0
     * @return an array of sexagesimal digits
     */
    public static int[] decimalToSexagesimal(double decimal, int digits) {
        if (digits < 1) {
            throw new IllegalArgumentException("amount of digits must be greater than 0");
        }
        int[] sexagesimal = new int[digits];
        int i = 0;
        while (true) {
            if (i == digits - 1) {
                sexagesimal[i] = (int) Math.round(decimal);
                break;
            }
            sexagesimal[i] = (int) decimal;
            decimal = (decimal - sexagesimal[i]) * 60;
            i++;
        }
        return sexagesimal;
    }

    /**
     * Converts a decimal double to a Strign of ":"-separated rounded sexagesimal ints.
     *
     * @param decimal the decimal
     * @param digits  digits in the sexagesimal number; greater than 0
     * @return a String of sexagesimal digits
     */
    public static String decimalToSexagesimalTime(double decimal, int digits) {
        int[] sexagesimal = decimalToSexagesimal(decimal, digits);
        StringBuilder builder = new StringBuilder().append(sexagesimal[0]);
        for (int i = 1; i < digits; i++) {
            builder.append(':').append(sexagesimal[i] < 10 ? "0" : "").append(sexagesimal[i]);
        }
        return builder.toString();
    }

}
