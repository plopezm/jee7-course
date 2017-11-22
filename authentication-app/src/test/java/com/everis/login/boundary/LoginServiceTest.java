package com.everis.login.boundary;

import com.everis.login.entity.User;
import com.everis.login.exceptions.AuthUserNotFound;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

public class LoginServiceTest {

    private static EntityManagerFactory emf;

    @BeforeClass
    public static void init(){
        emf = Persistence.createEntityManagerFactory("tests");
    }

    private LoginService underTest;

    @Before
    public void setUp(){
        underTest = new LoginService();
        underTest.setEm(emf.createEntityManager());
    }

    @Test
    public void createUser() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        User user = new User();
        user.setEmail("pabloplm@gmail.com");
        user.setPassword("password");
        //When
        tx.begin();
        User createdUser = underTest.createUser(user);
        //Then
        assertNotNull(createdUser);
        assertNotEquals(0, createdUser.getId());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
        tx.rollback();
    }

    @Test
    public void getAllUsers() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        //When
        tx.begin();
        for(int i =0; i< 10; i++) {
            User user = new User();
            user.setEmail("user"+i);
            user.setPassword("test");
            underTest.createUser(user);
        }
        //Then
        List<User> users = underTest.getAllUsers();
        assertNotNull(users);
        assertEquals(10, users.size());
        long firstId = users.get(0).getId();
        for(int i = 0;i<users.size();i++){
            assertNotNull(users.get(i));
            assertEquals(firstId, (int) users.get(i).getId());
            firstId++;
        }
        tx.rollback();
    }

    @Test
    public void getUserById() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        User user = new User();
        user.setEmail("pabloplm@gmail.com");
        user.setPassword("password");
        //When
        tx.begin();
        underTest.createUser(user);
        //Then
        User userCreated = underTest.getUserById(user.getId());
        assertNotNull(userCreated);
        assertNotEquals(0, userCreated.getId());
        assertEquals(user.getId(), (int) userCreated.getId());
        tx.rollback();
    }

    @Test(expected = AuthUserNotFound.class)
    public void getUserByIdNotFound() throws Exception {
        //Given
        //When
        User userCreated = underTest.getUserById(12345);
        //Then
    }

    @Test
    public void getUserByEmail() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        User user = new User();
        user.setEmail("pabloplm@gmail.com");
        user.setPassword("password");
        //When
        tx.begin();
        underTest.createUser(user);
        //Then
        User userCreated = underTest.getUserByEmail(user.getEmail());
        assertNotNull(userCreated);
        assertNotEquals(0, userCreated.getId());
        assertEquals(user.getId(), (int) userCreated.getId());
        tx.rollback();
    }

    @Test
    public void removeUser() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        User user = new User();
        user.setEmail("pabloplm@gmail.com");
        user.setPassword("password");
        //When
        tx.begin();
        underTest.createUser(user);
        User userRemoved = underTest.removeUser(user.getId());
        //Then
        assertEquals(user.getId(), userRemoved.getId());
        tx.rollback();
    }

    @Test
    public void updateUser() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        User user = new User();
        user.setEmail("pabloplm@gmail.com");
        user.setPassword("password");
        //When
        tx.begin();
        underTest.createUser(user);
        user.setPassword("otherPassword");
        underTest.updateUser(user);
        user = underTest.getUserById(user.getId());
        //Then
        assertNotNull(user);
        assertEquals("otherPassword", user.getPassword());
        tx.rollback();
    }

    @Test(expected = AuthUserNotFound.class)
    public void updateUserNotFound() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        User user = new User();
        user.setId(12345);
        user.setEmail("pabloplm@gmail.com");
        user.setPassword("password");
        //When
        tx.begin();
        underTest.updateUser(user);
        //Then
    }

    @Test
    public void validate() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        User user = new User();
        user.setEmail("pabloplm@gmail.com");
        user.setPassword("password");
        //When
        tx.begin();
        underTest.createUser(user);
        User validatedUser = underTest.validate(user);
        //Then
        assertNotNull(validatedUser);
        assertEquals(user.getId(), validatedUser.getId());
        tx.rollback();
    }

    @Test(expected = NoResultException.class)
    public void validateNotFound() throws Exception {
        EntityTransaction tx = underTest.getEm().getTransaction();
        //Given
        User user = new User();
        user.setEmail("pabloplm@gmail.com");
        user.setPassword("password");
        //When
        tx.begin();
        User validatedUser = underTest.validate(user);
        //Then
        tx.rollback();
    }
}