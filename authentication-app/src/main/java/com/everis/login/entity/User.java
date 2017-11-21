package com.everis.login.entity;

import com.everis.common.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "users",
        indexes = {
                @Index(name = "index_users_email", columnList = "email", unique = true)
        }
)
@NamedQueries({
        @NamedQuery(name="login.entity.User.FindByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
        @NamedQuery(name="login.entity.User.GetUserSalt", query = "SELECT u.salt FROM User u WHERE u.email = :email"),
        @NamedQuery(name="login.entity.User.Validate", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
})
public class User extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    private byte[] salt;

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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void updateFields(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
