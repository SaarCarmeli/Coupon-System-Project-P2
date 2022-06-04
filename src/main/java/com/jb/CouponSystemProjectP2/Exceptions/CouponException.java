package com.jb.CouponSystemProjectP2.Exceptions;

/**
 * General exception for errors related to Coupon entity.
 */
public class CouponException extends Exception {
    /**
     * Generic exception constructor with hard-coded message: "General coupon exception!"
     */
    public CouponException() {
        super("General coupon exception!");
    }

    /**
     * Custom exception constructor.
     *
     * @param message Error message
     */
    public CouponException(String message) {
        super(message);
    }
}
