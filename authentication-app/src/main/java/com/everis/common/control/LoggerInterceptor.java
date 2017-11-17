package com.everis.common.control;

import com.everis.common.boundary.Logged;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Logged
@Interceptor
@Priority(value = Interceptor.Priority.APPLICATION)
public class LoggerInterceptor {
    private static final Logger LOG = Logger.getLogger(LoggerInterceptor.class.getName());

    @AroundInvoke
    public Object applyLog(InvocationContext ctx) throws Exception{
        Method calledMethod = ctx.getMethod();
        Class clazzCalled = calledMethod.getDeclaringClass();

        LocalDateTime iniTime = LocalDateTime.now();
        Object result = ctx.proceed();
        Duration timeElapsed = Duration.between(iniTime, LocalDateTime.now());

        LOG.info("["+clazzCalled.getSimpleName()+":"+calledMethod.getName()+"] "+LocalDateTime.now()+" | "+timeElapsed.toMillis() + " ms");
        return result;
    }
}
