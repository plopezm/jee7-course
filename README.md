# Welcome to Java EE 7 course

This course is focused on learning Java EE basics.

### Scenario

In this chapter we are going to implement two resources. On the first hand it is LoginResource, it will expose two resources, login and logout. On the other hand we have UserResource, it will be used to get the data of the users of the system, create new users, update new users, etc. This exercise is focused on using LoginService to login/logout and retrieve the user data allocated in the database. In addition we are going to add some security (HTTP basic authentication header) because in futures chapters we will limit the access to some resources depending on the user logged.

### Exercise

1. Create a JAX-RS resource to login into the server if the user matchs
    * Security must be performed using basic authentication header
    * Create a new method called "validate(User user)" in LoginService. This method will validate that the email and the password are correct. This method must be tested
2. Create a JAX-RS resource to logout
3. Create a JAX-RS resource to get all users
    * Security must be performed using basic authentication header
4. Create a JAX-RS resource to get an specific user
    * Security must be performed using basic authentication header
5. Create a JAX-RS resource to create a new user
    * Security must be performed using basic authentication header
6. Create a JAX-RS resource to update a user
    * Security must be performed using basic authentication header
    * Implement update method in LoginService. This method must be tested
7. Create a JAX-RS resource to delete an user
    * Security must be performed using basic authentication header
8. Download Payara4 and start checking the admin panel (the admin panel is available on port 4848).
9. Download Derby database and start it in network mode (./startNetworkServer -h 0.0.0.0)
10. Create the necessary datasource in Payara (if necessary) and check if persistence.xml is correct. 
11. Execute the application in Payara 4 and check that everything works

### Help

* You have to use LoginService using injection.
* It is recommended to perform a ContainerRequestFilter (similar to interceptors for JAX-RS) with a custom annotation to implement the basic authorization.

### Solution

The solution is in chapter3.

