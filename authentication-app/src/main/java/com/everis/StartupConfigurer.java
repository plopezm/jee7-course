package com.everis;

import com.everis.login.boundary.LoginService;
import com.everis.login.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupConfigurer {

    @Inject
    private LoginService loginService;

    @PostConstruct
    public void init(){
        User user = new User();
        user.setEmail("admin");
        user.setPassword("admin");

        loginService.createUser(user);
    }

}
