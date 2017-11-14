# Welcome to Java EE 7 course

This course is focused on learning Java EE basics.

### Exercise

1. Define a entity class named User with the following attributes
    * private long id
    * private long version
    * private String email
    * private String password
2. Define UserService class for User with the following methods:
    * public User createUser(User user)
    * public List getAllUsers()
    * public User getUserById(long id)
    * public User getUserByEmail(String email)
    * public User removeUser(long id)
3. Define the integration tests for each method of UserService class using Derby embedded
4. Every test must be green :)

### Help

* Sometimes the name of a entity matchs with some reserved word of the database. The solution is to define @Table annotation in the entity.
* It is possible to use a superclass as @MappedSuperclass to avoid 'id' and 'version' repetition.
* In User class there must be an attribute called version with @version annotation.
* UserService must be a @Stateful bean
* When a method is read-only, it is recommended set the transaction as support

### Solution

The solution is in the tag "chapter2".
