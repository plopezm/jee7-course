package com.everis.login.boundary;

import com.everis.common.boundary.Logged;
import com.everis.login.entity.User;
import com.everis.security.boundary.BasicAuthentication;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("users")
@Logged
@BasicAuthentication
@Produces({"application/json"})
public class UserResource {

    @Inject
    private LoginService loginService;

    @GET
    public List<User> getAllUsers(){
        return loginService.getAllUsers();
    }

    @GET
    @Path("{id}")
    public User getUserById(@PathParam("id") long id){
        return loginService.getUserById(id);
    }

    @POST
    @Consumes({"application/json"})
    public User createUser(User user){
        return loginService.createUser(user);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public User updateUser(@PathParam("id") long id, User user){
        user.setId(id);
        return loginService.updateUser(user);
    }

    @DELETE
    @Path("{id}")
    public User removeUser(@PathParam("id") long id){
        return loginService.removeUser(id);
    }
}
