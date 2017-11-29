package com.example.agalka1632.prog3210_assignment2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by agalka1632 on 11/29/2017.
 */

@Entity(tableName = "last_login",
        foreignKeys = {
                @ForeignKey(
                        entity = LastLogin.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = { @Index(value = "id")}
)
public class LastLogin {

    @PrimaryKey(autoGenerate = true)
    long id;

    public long userId;
    String date;

    public LastLogin(long userId, String date) {
        this.userId = userId;
        this.date = date;
    }
}