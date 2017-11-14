# Welcome to Java EE 7 course

This course is focused on learning Java EE basics.

### Exercise

1. Create a JAX-RS resource to login into the server if the user matchs
    * Security must be performed using basic authentication header
2. Create a JAX-RS resource to logout
3. Create a JAX-RS resource to get all users
    * Security must be performed using basic authentication header
4. Create a JAX-RS resource to get an specific user
    * Security must be performed using basic authentication header
5. Create a JAX-RS resource to create a new user
    * Security must be performed using basic authentication header
6. Create a JAX-RS resource to update a user
    * Security must be performed using basic authentication header
7. Create a JAX-RS resource to delete an user
    * Security must be performed using basic authentication header
8. Create the necessary datasource in Payara
9. Executes the application in Payara 4 and check that everything works

### Help

* You have to use LoginService using injection.
* It is recommended to perform a ContainerRequestFilter (similar to interceptors for JAX-RS) with a custom annotation.

### Solution

The solution is in chapter3.

