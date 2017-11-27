package com.example.agalka1632.prog3210_assignment2;

/**
 * Created by agalka1632 on 11/27/2017.
 */

public class User {

    private int id;
    private String username;
    private String password;

    public User(){

    }
    // constructor
    public User(int id, String username, String password){
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
