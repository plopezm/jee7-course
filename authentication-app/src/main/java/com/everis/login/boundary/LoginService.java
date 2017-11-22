package com.everis.login.boundary;

import com.everis.login.entity.User;
import com.everis.login.exceptions.AuthUserNotFound;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LoginService {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public User createUser(User user){
        em.persist(user);
        em.flush();
        em.refresh(user);
        return user;
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List getAllUsers(){
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public User getUserById(long id){
        return em.find(User.class, id);
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public User getUserByEmail(String email){
       Query query = em.createNamedQuery("login.entity.User.FindByEmail");
       query.setParameter("email", email);
       return (User) query.getSingleResult();
    }

    public User removeUser(long id){
        User user = this.getUserById(id);
        em.remove(user);
        return user;
    }

    public User updateUser(User user){
        User userToModify = this.getUserById(user.getId());
        if(userToModify == null)
            throw new AuthUserNotFound();
        userToModify.updateFields(user);
        return userToModify;
    }

    public User validate(User user) {
        Query query = em.createNamedQuery("login.entity.User.Validate");
        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());
        return (User) query.getSingleResult();
    }
}
