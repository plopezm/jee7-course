package com.everis.common.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message){
        super(message);
    }
}
