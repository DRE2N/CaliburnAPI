package de.erethon.caliburn.util;

/**
 * @author Fyreum
 */
public class RecipeUtil {

    public static <T> T[] length(T[] a, int l, String msg) {
        if (a.length != l) {
            throw new IllegalArgumentException(msg);
        }
        return a;
    }

    public static String length(String s, int l, String msg) throws IllegalArgumentException {
        if (s.length() != l) {
            throw new IllegalArgumentException(msg);
        }
        return s;
    }

    public static String toShapeString(String[] a) {
        length(a, 3, "Wrong shape format, need 3 rows");

        String one = length(a[0], 3, "Wrong shape format (row 1)");
        String two = length(a[1], 3, "Wrong shape format (row 2)");
        String three = length(a[2], 3, "Wrong shape format (row 3)");

        return one + ":" + two + ":" + three;
    }

    public static String[] toShape(String s) {
        length(s, 9, "Wrong shape format, need 3 rows");

        char[] one = new char[3];
        char[] two = new char[3];
        char[] three = new char[3];

        s.getChars(0, 3, one, 0);
        s.getChars(3, 6, two, 0);
        s.getChars(6, 9, three, 0);

        return new String[]{String.valueOf(one), String.valueOf(two), String.valueOf(three)};
    }

}
