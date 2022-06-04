package com.jb.CouponSystemProjectP2.Advices;

import com.jb.CouponSystemProjectP2.Exceptions.LoginException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.SignatureException;

/**
 * Exception handler class for LoginException, MalformedJwtException, SignatureException snd ExpiredJwtException
 * Returns 401 Unauthorized HTTP status response
 */
@RestController
@ControllerAdvice
public class LoginAdvice {
    /**
     * Handles exceptions for REST response and returns error details
     *
     * @param e Exception
     * @return Error details
     */
    @ExceptionHandler(value = {LoginException.class, MalformedJwtException.class, SignatureException.class, ExpiredJwtException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("Login failed!", e.getMessage());
    }
}
