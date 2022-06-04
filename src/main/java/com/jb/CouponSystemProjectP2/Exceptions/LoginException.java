package com.jb.CouponSystemProjectP2.Exceptions;

/**
 * General exception for errors related to log-in function.
 */
public class LoginException extends Exception {
    /**
     * Generic exception constructor with hard-coded message: "General login exception!"
     */
    public LoginException() {
        super("General login exception!");
    }

    /**
     * Custom exception constructor.
     *
     * @param message Error message
     */
    public LoginException(String message) {
        super(message);
    }
}
