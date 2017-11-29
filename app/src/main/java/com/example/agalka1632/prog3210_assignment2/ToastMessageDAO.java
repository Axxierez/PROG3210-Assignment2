package com.example.agalka1632.prog3210_assignment2;

/**
 * Created by agalka1632 on 11/29/2017.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface ToastMessageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToast(ToastMessage toast);

    @Query("SELECT * FROM toast_message WHERE userId=:userId")
    List<ToastMessage> findToastForUser(int userId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateToast(ToastMessage toast);

    @Query("delete from toast_message where userId = :userId")
    void delete(int userId);

}