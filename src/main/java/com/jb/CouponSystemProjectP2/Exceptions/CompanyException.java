package com.jb.CouponSystemProjectP2.Exceptions;

/**
 * General exception for errors related to Company entity.
 */
public class CompanyException extends Exception {
    /**
     * Generic exception constructor with hard-coded message: "General company exception!"
     */
    public CompanyException() {
        super("General company exception!");
    }

    /**
     * Custom exception constructor.
     *
     * @param message Error message
     */
    public CompanyException(String message) {
        super(message);
    }
}
