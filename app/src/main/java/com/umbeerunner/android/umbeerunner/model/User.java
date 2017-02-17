package com.umbeerunner.android.umbeerunner.model;

/**
 * Created by Luis Vespa on 12/04/2016.
 */

public class User {
    private int id;
    private String userId;
    private String created_at;
    private String updated_at;
    private String username;
    private String password;
    private String name;
    private String usertype;

    /* Usamos un constructor sin parametros   */
    public User() {
    }

    public User(String userId, String created_at, String updated_at, String username, String password, String name, String usertype) {
        this.userId = userId;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.username = username;
        this.password = password;
        this.name = name;
        this.usertype = usertype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
