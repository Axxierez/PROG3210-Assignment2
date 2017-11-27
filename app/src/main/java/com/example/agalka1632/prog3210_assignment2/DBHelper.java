package com.example.agalka1632.prog3210_assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by agalka1632 on 11/27/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "assignment2";

    // Contacts table name
    private static final String USERS_TABLE = "users";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + USERS_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PASS + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASS, user.getPassword());

        db.insert(USERS_TABLE, null, values);
        db.close();
    }

    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USERS_TABLE, new String[] { KEY_ID,
                        KEY_USERNAME, KEY_PASS }, KEY_USERNAME + "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.getCount()==0)
            return null;

        User user = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return user;

    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASS, user.getPassword());

        // updating row
        return db.update(USERS_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });

    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERS_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }
}
