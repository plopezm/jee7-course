# Welcome to Java EE 7 course

This course is focused on learning Java EE basics.

### Introduction

Now we have a small security system based on Basic authentication. If you have executed the application you probably have been noticed that the error requests show ugly output. To solve this I am going to introduce the exception mappers. An exception mapper catches the exceptions and transform them into a custom ouput for JAX-RS.

### Exercise

* Identify the exceptions you have in your application.
* When a user is not authorized, a exception must be launched. The message sent to the client must be a JSON error object.
* Create a new ExceptionMapper for each one.
* In addition we are going to create a LoggerInterceptor. This interceptor will detect when a web resource is called and will print the time required to perform that resource method.

### Help

* When a SQL queries returns null an exception is launched.
* Could be interesting to define a exception if an user is not registered into the system
* If JAX-RS does not find a serializer, probably you will need to add @Produces({"application/json"}) to each JAX-RS resource

### Solution

The solution is in chapter4.
