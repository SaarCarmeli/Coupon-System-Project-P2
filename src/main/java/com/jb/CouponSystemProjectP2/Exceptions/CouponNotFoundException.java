package com.jb.CouponSystemProjectP2.Exceptions;

public class CouponNotFoundException extends Exception {
    public CouponNotFoundException() {
        super("Coupon not found!");
    }

    public CouponNotFoundException(String message) {
        super(message);
    }
}
