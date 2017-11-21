# Welcome to Java EE 7 course

This course is focused on learning Java EE basics.

### Introduction

Now we have a complete security system based on Basic authentication. The problem is that the passwords are stored directly into the database so anyone can access to the database an see the password in clear text. The solution to this problem is create a hash of the password and store it. In addition we can use a "salt" per user in order to randomize more the hash produced.

### Exercise

1. Create a new interceptor to hash the password of a user received. We are going to use SHA512 algorithm to do it. The interceptor requires an annotation to bind the interceptor with other methods or classes. The name of the annotation must be @PasswordEncoded
2. Mark with @Password encoded the service methods "createUser", "updateUser" and "validate" from LoginService.
3. From User.class, email must be unique and email and password cannot be null.
4. Ensure that the tests are still working.

### Help

* Salt field MUST not be showed in json responses
* In addition you can hide "version" field in the same way that "salt".
* It is interesting to understand what a hash algorithm is and what a salt is.

### Solution

The solution is in chapter5.
