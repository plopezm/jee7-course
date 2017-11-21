package com.everis.security.control;

import com.everis.login.boundary.LoginService;
import com.everis.login.entity.User;
import com.everis.security.boundary.PasswordEncoded;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.NoResultException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@PasswordEncoded
@Interceptor
@Priority(value = Interceptor.Priority.APPLICATION)
public class PasswordEncoder {

    public final static String DIGEST_ALGORITHM = "SHA512";

    @Inject
    private LoginService loginService;

    public static String getSecurePassword(String passwordToHash, byte[] salt, String digestAlgorithm) {
        String generatedPassword = passwordToHash;
        try {
            //Create MessageDigest instance for SHA512 (depends on the annotation value, by default it will be SHA512)
            MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
            //Add salt to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            Logger.getLogger(PasswordEncoder.class.getName()).log(Level.SEVERE, "Algorithm not found", e);
        }
        return generatedPassword;
    }

    //Add salt
    public static byte[] generateSalt(String secureRandomType, String secureRandomImpl){
        //Create array for salt
        byte[] salt = new byte[64];

        //Always use a SecureRandom generator
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance(secureRandomType, secureRandomImpl);
            //Get a random salt
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(PasswordEncoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return salt
        return salt;
    }

    @AroundInvoke
    public Object encode(InvocationContext ctx) throws Exception{
        Method method = ctx.getMethod();
        PasswordEncoded userPasswordHashed = method.getDeclaredAnnotation(PasswordEncoded.class);

        final String digestAlgorithm = userPasswordHashed.digestAlgorithm();
        final String secureRandomType = userPasswordHashed.secureRandomType();
        final String secureRandomImpl = userPasswordHashed.secureRandomImpl();

        Arrays.asList(ctx.getParameters()).forEach((obj) -> {
            if(obj instanceof User){
                User user = (User) obj;
                try {
                    byte[] userSalt = this.loginService.getUserSalt(user.getEmail());
                    user.setSalt(userSalt);
                }catch (NoResultException ex){
                    user.setSalt(generateSalt(secureRandomType, secureRandomImpl));
                }
                user.setPassword(getSecurePassword(user.getPassword(), user.getSalt(), digestAlgorithm));
           }
        });
        return ctx.proceed();
    }

}
