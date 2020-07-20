package com.example.userlogin.model;

import java.io.Serializable;

public class User implements Serializable {
    String username;
    String password;

    public CharSequence getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
