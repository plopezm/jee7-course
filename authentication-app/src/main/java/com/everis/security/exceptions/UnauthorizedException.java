package com.everis.security.exceptions;

import com.everis.common.exceptions.AuthenticationException;

public class UnauthorizedException extends AuthenticationException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
