package com.everis.security.exceptions;


import com.everis.login.dto.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {
    @Override
    public Response toResponse(UnauthorizedException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessage(Response.Status.UNAUTHORIZED, e.getMessage())).build();
    }
}
