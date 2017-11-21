package com.everis.security.boundary;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@InterceptorBinding
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PasswordEncoded {
    String digestAlgorithm() default "SHA-512";
    String secureRandomType() default "SHA1PRNG";
    String secureRandomImpl() default "SUN";
}
