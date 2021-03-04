package de.erethon.caliburn.util;

/**
 * @author Fyreum
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 5977486330383552792L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
