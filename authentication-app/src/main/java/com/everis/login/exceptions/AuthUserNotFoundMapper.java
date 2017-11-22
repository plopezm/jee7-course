package com.everis.login.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AuthUserNotFoundMapper implements ExceptionMapper<AuthUserNotFound>{
    @Override
    public Response toResponse(AuthUserNotFound authUserNotFound) {
        return Response.status(Response.Status.NOT_FOUND).entity(authUserNotFound.getMessage()).build();
    }
}
