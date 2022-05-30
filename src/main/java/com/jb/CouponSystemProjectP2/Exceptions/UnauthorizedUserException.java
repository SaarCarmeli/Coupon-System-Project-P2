package com.jb.CouponSystemProjectP2.Exceptions;

public class UnauthorizedUserException extends Exception {
    public UnauthorizedUserException() {
        super("You are not authorized to use this method!");
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }
}
