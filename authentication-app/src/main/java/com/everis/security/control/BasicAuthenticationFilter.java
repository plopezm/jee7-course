package com.everis.security.control;

import com.everis.login.boundary.LoginService;
import com.everis.login.entity.User;
import com.everis.security.boundary.BasicAuthentication;
import org.eclipse.persistence.internal.oxm.conversion.Base64;

import javax.inject.Inject;
import javax.persistence.Basic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

@Provider
@BasicAuthentication
public class BasicAuthenticationFilter implements ContainerRequestFilter {
    private static final Logger LOG = Logger.getLogger(BasicAuthenticationFilter.class.getName());

    @Inject
    private LoginService loginService;

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private HttpHeaders headers;

    @Context
    private ResourceInfo resourceInfo;


    public static User getHttpBasicAuthorization(HttpHeaders headers) {
        List<String> header = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);

        if(header == null || header.isEmpty())
            return null;

        String authorization = header.get(0);
        authorization = authorization.substring("Basic ".length());

        final String usernameAndPassword = new String(Base64.base64Decode(authorization.getBytes()));
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

        User user = new User();
        user.setEmail(tokenizer.nextToken());
        user.setPassword(tokenizer.nextToken());
        return user;
    }

    private boolean isHttpBasicAuthorized(HttpHeaders headers, BasicAuthentication basicAuthentication){
        User user = getHttpBasicAuthorization(headers);
        if(user == null){
            return false;
        }

        user = loginService.validate(user);
        return user != null;
    }

    private boolean isAuthorized(){
        HttpSession session = servletRequest.getSession();

        Method resourceMethod = resourceInfo.getResourceMethod();
        BasicAuthentication basicAuthentication = resourceMethod.getDeclaredAnnotation(BasicAuthentication.class);

        if(basicAuthentication == null){
            Class<?> resourceClass = resourceInfo.getResourceClass();
            basicAuthentication = resourceClass.getDeclaredAnnotation(BasicAuthentication.class);
        }
        //If no session detected the next step is check if the request has Httpbasic security
        return isHttpBasicAuthorized(headers, basicAuthentication);
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        final Response errResponse = Response.status(Response.Status.UNAUTHORIZED).build();

        if(!isAuthorized())
            containerRequestContext.abortWith(errResponse);
    }

}
