/*
 * Copyright (C) 2015-2021 Daniel Saukel.
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
package de.erethon.caliburn.util;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel Saukel, Fyreum
 */
public class StringUtil {

    public static String formatId(String id) {
        String[] words = id.split("_");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            builder.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1).toLowerCase());
            if (i < words.length - 1) {
                builder.append(' ');
            }
        }
        return builder.toString();
    }

    public static String toString(Map<String, String> m) {
        String[] k = m.keySet().toArray(new String[0]);
        String[] v = m.values().toArray(new String[0]);
        int iMax = k.length - 1;

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(k[i]).append(":").append(v[i]);
            if (i == iMax) {
                return b.toString();
            }
            b.append(",");
        }
    }

    public static String toString(List<String> l) {
        int iMax = l.size() - 1;

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(l.get(i));
            if (i == iMax) {
                return b.toString();
            }
            b.append(",");
        }
    }
}
