package com.jb.CouponSystemProjectP2.Exceptions;

/**
 * Exception for not-found errors related to Customer entity.
 */
public class CustomerNotFoundException extends Exception {
    /**
     * Generic exception constructor with hard-coded message: "Customer not found!"
     */
    public CustomerNotFoundException() {
        super("Customer not found!");
    }

    /**
     * Custom exception constructor.
     *
     * @param message Error message
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
