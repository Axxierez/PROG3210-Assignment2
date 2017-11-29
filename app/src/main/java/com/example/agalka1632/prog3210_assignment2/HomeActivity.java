package com.example.agalka1632.prog3210_assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getDatabase(getApplicationContext());
        setContentView(R.layout.activity_home);

        Bundle extras = getIntent().getExtras();
        String userLoggedIn = extras.getString("userLoggedIn");

        changeLastLoginMessage(userLoggedIn);


    }

    public void EditToast(View view){


        Bundle extras = getIntent().getExtras();
        String userLoggedIn = extras.getString("userLoggedIn");



    }

    private void changeLastLoginMessage(String userLoggedIn){

        List<User> users = database.userDAO().getUser(userLoggedIn);

        if (!users.isEmpty()){
            List<LastLogin> logins = database.lastLoginDAO().findLastLogin(users.get(0).id);

            if(!logins.isEmpty()){
                TextView lastLogin = (TextView) findViewById(R.id.textLastLogin);
                lastLogin.setText("Last Login " + logins.get(lastLogin.length()));
            }}
    }
}
