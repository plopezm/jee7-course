package com.everis.login.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AuthUserNotFound extends RuntimeException{
    public AuthUserNotFound(){
        super("User not found");
    }
}
