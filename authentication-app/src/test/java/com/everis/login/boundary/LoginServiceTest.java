package com.everis.login.boundary;

import com.everis.login.entity.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
        assertEquals(1, createdUser.getId());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
        tx.rollback();
    }

    @Test
    public void getAllUsers() throws Exception {
    }

    @Test
    public void getUserById() throws Exception {
    }

    @Test
    public void getUserByEmail() throws Exception {
    }

    @Test
    public void removeUser() throws Exception {
    }

}