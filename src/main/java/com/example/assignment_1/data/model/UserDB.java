package com.example.assignment_1.data.model;

import com.example.assignment_1.business.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class UserDB {

    private Long id;

    private String username;
    private String password;
    private UserRole role;
    private String token;

    public UserDB() {

    }

    public UserDB(String username, String password, UserRole role, String token) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    public UserDB(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDB(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
