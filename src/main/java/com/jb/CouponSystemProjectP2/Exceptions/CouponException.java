package com.jb.CouponSystemProjectP2.Exceptions;

public class CouponException extends Exception {
    public CouponException() {
        super("General coupon exception!");
    }

    public CouponException(String message) {
        super(message);
    }
}
