package com.jb.CouponSystemProjectP2.Exceptions;

public class CompanyException extends Exception{
    public CompanyException() {
        super("General company exception!");
    }

    public CompanyException(String message) {
        super(message);
    }
}
