package com.jb.CouponSystemProjectP2.Advices;

import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CouponNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exception handler class for CompanyNotFoundException, CouponNotFoundException and CustomerNotFoundException
 * Returns 404 Not Found HTTP status response
 */
@RestController
@ControllerAdvice
public class NotFoundAdvice {
    /**
     * Handles exceptions for REST response and returns error details
     *
     * @param e Exception
     * @return Error details
     */
    @ExceptionHandler(value = {CompanyNotFoundException.class, CouponNotFoundException.class, CustomerNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("Not Found!", e.getMessage());
    }
}
