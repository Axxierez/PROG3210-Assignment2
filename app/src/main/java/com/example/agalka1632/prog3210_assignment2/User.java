package com.example.agalka1632.prog3210_assignment2;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by agalka1632 on 11/27/2017.
 */

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    long id;
    private String username;
    private String password;

    public User(){

    }
    // constructor
    public User( String username, String password){
        this.setUsername(username);
        this.setPassword(password);
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
