package com.jb.CouponSystemProjectP2.Exceptions;

/**
 * Exception for errors related to user-method mismatch.
 */
public class UnauthorizedUserException extends Exception {
    /**
     * Generic exception constructor with hard-coded message: "You are not authorized to use this method!"
     */
    public UnauthorizedUserException() {
        super("You are not authorized to use this method!");
    }

    /**
     * Custom exception constructor.
     *
     * @param message Error message
     */
    public UnauthorizedUserException(String message) {
        super(message);
    }
}
