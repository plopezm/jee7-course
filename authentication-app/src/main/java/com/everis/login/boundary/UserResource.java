package com.everis.login.boundary;

import com.everis.login.entity.User;
import com.everis.security.boundary.BasicAuthentication;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("users")
@BasicAuthentication
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
    @Consumes({"application/json"})
    public User updateUser(User user){
        return loginService.updateUser(user);
    }

    @DELETE
    @Path("{id}")
    public User removeUser(@PathParam("id") long id){
        return loginService.removeUser(id);
    }
}
