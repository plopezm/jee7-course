package com.everis.login.boundary;

import com.everis.login.entity.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

}