package com.jb.CouponSystemProjectP2.Exceptions;

public class CustomerException extends Exception{
    public CustomerException() {
        super("General customer exception!");
    }

    public CustomerException(String message) {
        super(message);
    }
}
