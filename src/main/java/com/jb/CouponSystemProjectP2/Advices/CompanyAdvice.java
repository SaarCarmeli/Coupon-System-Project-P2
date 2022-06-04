package com.jb.CouponSystemProjectP2.Advices;

import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exception handler class for CompanyException.
 * Returns 400 Bad Request HTTP status response
 */
@RestController
@ControllerAdvice
public class CompanyAdvice {
    /**
     * Handles exceptions for REST response and returns error details
     *
     * @param e Exception
     * @return Error details
     */
    @ExceptionHandler(value = {CompanyException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("Company Error!", e.getMessage());
    }
}
