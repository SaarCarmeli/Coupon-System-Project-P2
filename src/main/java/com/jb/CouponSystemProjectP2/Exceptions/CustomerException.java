package com.jb.CouponSystemProjectP2.Exceptions;

/**
 * General exception for errors related to Customer entity.
 */
public class CustomerException extends Exception {
    /**
     * Generic exception constructor with hard-coded message: "General customer exception!"
     */
    public CustomerException() {
        super("General customer exception!");
    }

    /**
     * Custom exception constructor.
     *
     * @param message Error message
     */
    public CustomerException(String message) {
        super(message);
    }
}
