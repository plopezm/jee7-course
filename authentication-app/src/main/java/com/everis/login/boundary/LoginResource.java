package com.everis.login.boundary;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    private LoginService loginService;

    @GET
    public Response Login(){


        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    public Response Logout(){
        return Response.status(Response.Status.OK).build();
    }
}
