package com.vogella.retrofitgerrit;

import java.io.Serializable;

/**
 * Used ONLY to communicate with the LB4
 */
public class UserData {
    //  @SerializedName(value = "name")
    private String name;
    // @SerializedName(value = "email")
    private String email;
    // @SerializedName(value = "password")
    private String password;
    // @SerializedName(value = "role")
    private String role;

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