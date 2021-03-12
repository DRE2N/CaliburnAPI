package de.erethon.caliburn.util;

import de.erethon.commons.misc.NumberUtil;

/**
 * @author Fyreum
 */
public class Util {

    public static boolean contains(char[] a, char c) {
        for (char c1 : a) {
            if (c1 == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(int[] a, int t) {
        for (int value : a) {
            if (value == t) {
                return true;
            }
        }
        return false;
    }

    public static boolean handleChar(char c, int column, SecuredStringBuilder builder, char... ex) {
        return handleChar(c, ';' , column, builder, ex);
    }

    public static boolean handleChar(char c, int column, SecuredStringBuilder builder) {
        return handleChar(c, ';', column, builder);
    }

    public static boolean handleChar(char c, char e, int column, SecuredStringBuilder builder, char... ex) {
        if (Validate.isCorrectFormatChar(c, e, column, ex)) {
            builder.setAccessible(false);
            return true;
        }
        builder.builder().append(c);
        return false;
    }

    public static boolean handleChar(char c, char e, int column, StringBuilder builder, char... ex) {
        if (Validate.isCorrectFormatChar(c, e, column, ex)) {
            return true;
        }
        builder.append(c);
        return false;
    }

    public static int parseInt(String str, int def, int min, String msg) {
        int parsed = NumberUtil.parseInt(str, def);
        if (parsed < min) {
            throw new IllegalArgumentException(msg);
        }
        return parsed;
    }
}
