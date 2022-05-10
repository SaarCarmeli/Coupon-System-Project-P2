package com.jb.CouponSystemProjectP2.Exceptions;

public class TokenException extends Exception{
    public TokenException() {
        super("General token exception!");
    }

    public TokenException(String message) {
        super(message);
    }
}
