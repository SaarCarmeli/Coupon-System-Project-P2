package com.jb.CouponSystemProjectP2.Exceptions;

public class LoginException extends Exception {
    public LoginException() {
        super("General login exception!");
    }

    public LoginException(String message) {
        super(message);
    }
}
