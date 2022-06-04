package com.jb.CouponSystemProjectP2.Exceptions;

/**
 * Exception for not-found errors related to Company entity.
 */
public class CompanyNotFoundException extends Exception {
    /**
     * Generic exception constructor with hard-coded message: "Company not found!"
     */
    public CompanyNotFoundException() {
        super("Company not found!");
    }

    /**
     * Custom exception constructor.
     *
     * @param message Error message
     */
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
