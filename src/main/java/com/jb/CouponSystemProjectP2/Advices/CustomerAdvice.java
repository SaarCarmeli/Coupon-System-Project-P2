package com.jb.CouponSystemProjectP2.Advices;

import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CustomerAdvice {
    @ExceptionHandler(value = {CustomerException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("Customer Error!", e.getMessage());
    }
}
