package com.jb.CouponSystemProjectP2.Advices;

import com.jb.CouponSystemProjectP2.Exceptions.LoginException;
import com.jb.CouponSystemProjectP2.Exceptions.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class LoginAdvice {
    @ExceptionHandler(value = {LoginException.class, TokenException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("Login failed!", e.getMessage());
    }
}
