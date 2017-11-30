package com.example.agalka1632.prog3210_assignment2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by agalka1632 on 11/29/2017.
 */

@Entity(tableName = "toast_message",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = { @Index(value = "id")}
)
public class ToastMessage {

    @PrimaryKey(autoGenerate = true)
    long id;

    public long userId;

    private String message;

    public ToastMessage(long userId, String message) {
        this.userId = userId;
        this.setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}