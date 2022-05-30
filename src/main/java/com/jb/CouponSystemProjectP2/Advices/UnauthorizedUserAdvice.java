package com.jb.CouponSystemProjectP2.Advices;

import com.jb.CouponSystemProjectP2.Exceptions.UnauthorizedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class UnauthorizedUserAdvice {
    @ExceptionHandler(value = {UnauthorizedUserException.class})
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("Unauthorized User!", e.getMessage());
    }
}
