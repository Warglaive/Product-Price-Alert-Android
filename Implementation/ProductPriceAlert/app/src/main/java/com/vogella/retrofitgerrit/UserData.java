package com.vogella.retrofitgerrit;

import com.models.User;

/**
 * Used ONLY to communicate with the LB4
 */
public class UserData {
    private String name;
    private String email;
    private String password;
    private String role;

    /**
     * Get a User object and assign it to each field
     * @param user
     * */
    //TODO: Delete constructor if unable to communicate with LB4
    public UserData(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}