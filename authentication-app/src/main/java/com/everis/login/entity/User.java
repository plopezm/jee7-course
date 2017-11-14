package com.everis.login.entity;

import com.everis.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name="login.entity.User.FindByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User extends AbstractEntity{

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
