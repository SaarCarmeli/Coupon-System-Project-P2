package com.jb.CouponSystemProjectP2.Exceptions;

/**
 * Exception for not-found errors related to Coupon entity.
 */
public class CouponNotFoundException extends Exception {
    /**
     * Generic exception constructor with hard-coded message: "Coupon not found!"
     */
    public CouponNotFoundException() {
        super("Coupon not found!");
    }

    /**
     * Custom exception constructor.
     *
     * @param message Error message
     */
    public CouponNotFoundException(String message) {
        super(message);
    }
}
