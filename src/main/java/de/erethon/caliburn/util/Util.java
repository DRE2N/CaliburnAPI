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

    public static char getChar(int i) {
        if (i < 0 | i > 9) {
            throw new ValidationException("Chars can only be integers between 0-9");
        }
        switch (i) {
            case 0:
                return '0';
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '5';
            case 6:
                return '6';
            case 7:
                return '7';
            case 8:
                return '8';
            case 9:
                return '9';
        }
        // shouldn't happen
        return ' ';
    }

}
