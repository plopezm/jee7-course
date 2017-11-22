package com.everis.login.boundary;

import com.everis.login.entity.User;
import com.everis.login.exceptions.AuthUserNotFound;
import com.everis.security.boundary.PasswordEncoded;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    @PasswordEncoded
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
        User user = em.find(User.class, id);
        if(user == null)
            throw new AuthUserNotFound();
        return user;
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

    @PasswordEncoded
    public User updateUser(User user){
        User userToModify = this.getUserById(user.getId());
        userToModify.updateFields(user);
        return userToModify;
    }

    @PasswordEncoded
    public User validate(User user) {
        Query query = em.createNamedQuery("login.entity.User.Validate");
        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());
        return (User) query.getSingleResult();
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public byte[] getUserSalt(String email){
        Query query = em.createNamedQuery("login.entity.User.GetUserSalt", byte[].class);
        query.setParameter("email", email);
        try {
            return (byte[]) query.getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }
}
