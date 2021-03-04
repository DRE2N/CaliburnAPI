package de.erethon.caliburn.util;

/**
 * @author Fyreum
 */
public class Validate {

    private static final char[] formatChars = new char[]{';', '{', '}', '=', ':'};

    public static <T> T notNull(T t, String msg) throws ValidationException {
        if (t == null) {
            throw new ValidationException(msg);
        }
        return t;
    }

    public static char noSpace(char c, int column) throws ValidationException {
        if (c == ' ') {
            throw new ValidationException("Found forbidden space at column: " + column);
        }
        return c;
    }

    public static char[] braceFormat(char[] chars) throws ValidationException {
        if (chars[0] != '{') {
            throw new ValidationException("Expected '{' at column: 0 but found: '" + chars[0] + "'");
        }
        int lastIndex = chars.length - 1;
        if (chars[lastIndex] != '}') {
            throw new ValidationException("Expected '}' at column: " + lastIndex + " but found: '" + chars[0] + "'");
        }
        return chars;
    }

    public static boolean isFormatChar(char c) {
        for (char f : formatChars) {
            if (f == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFormatChar(char c, char... e) {
        if (Util.contains(e, c)) {
            return false;
        }
        return isFormatChar(c);
    }

    /*
     repeating pattern:

     c = char
     e = expected
     column = you know bra
     ex = exceptions (its okay if its this)
    */
    public static boolean isCorrectFormatChar(char c, char e, int column, char... ex) {
        if (Validate.isFormatChar(c, ex)) {
            if (c != e) {
                throw new ValidationException("Expected '" + e + "' but found wrong formatting char '" + c + "' at column: " + column);
            }
            return true;
        }
        return false;
    }

    public static String builderValue(SecuredStringBuilder builder) throws ValidationException {
        return builderValue(builder, "You need to set an value for: " + builder.getKey());
    }

    public static String builderValue(SecuredStringBuilder builder, String msg) throws ValidationException {
        if (builder.isEmpty()) {
            throw new ValidationException(msg);
        }
        return builder.toString();
    }

    public static void check(boolean condition, String msg) {
        if (!condition) {
            throw new ValidationException(msg);
        }
    }

    public static <T> T[] length(T[] array, int length, String msg) {
        if (array.length != length) {
            throw new ValidationException(msg);
        }
        return array;
    }

    public static String length(String string, int length, String msg) {
        if (string.length() != length) {
            throw new ValidationException(msg);
        }
        return string;
    }

}
