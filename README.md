# Welcome to Java EE 7 course

This course is focused on learning Java EE basics.

### Introduction

In this course, we are going to learn how to create an enterprise application in Java EE 7. In this course we a going to implement an authorization microservice based on JWT step by step. It is recommended to read the book Java EE Essentials to understand every part of this project. If you have some suggestion, please report it in issues.

### Scenario

In this chapter we are going to implement the Entity User to store and provide authentication to the users of this microservice. Once it is defined, we are going to implement the service that can be used to interact with the database. In addition, we are going to implement the tests to be sure that the service is working before implementing other services or resources. In order to ease the development of this exercise, I have implemented a Java EE scaffolding that can be used to get started.

### Exercise

1. Define a entity class named User with the following attributes
    * private long id
    * private long version
    * private String email
    * private String password
2. Define LoginService class for User with the following methods:
    * public User createUser(User user)
    * public List getAllUsers()
    * public User getUserById(long id)
    * public User getUserByEmail(String email)
    * public User removeUser(long id)
3. Define the integration tests for each method of LoginService class using Derby embedded
4. Every test must be green :)

### Help

* Sometimes the name of a entity matchs with some reserved word of the database. The solution is to define @Table annotation in the entity.
* It is possible to use a superclass as @MappedSuperclass to avoid 'id' and 'version' repetition.
* In User class there must be an attribute called version with @version annotation.
* LoginService must be a @Stateless bean
* When a method is read-only, it is recommended set the transaction as support

### Solution

The solution is in chapter2.
