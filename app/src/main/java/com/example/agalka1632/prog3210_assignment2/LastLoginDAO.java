package com.example.agalka1632.prog3210_assignment2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by agalka1632 on 11/29/2017.
 */

@Dao
public interface LastLoginDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLastLogin(LastLogin login);

    @Query("SELECT * FROM last_login WHERE userId=:userId")
    List<LastLogin> findLastLogin(long userId);

}
