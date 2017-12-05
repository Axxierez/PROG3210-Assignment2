package com.example.agalka1632.prog3210_assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
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
        String toastMessage = "Welcome!";

        ToastMessage existingToast=null;

        List<User> users = database.userDAO().getUser(userLoggedIn);
        List<ToastMessage> toasts = database.toastMessageDAO().findToastForUser(users.get(0).id);
        if (toasts.size()==1)
            existingToast=toasts.get(0);

        if (existingToast!=null)
            toastMessage=existingToast.getMessage();
        //Creates a toast with the message saved in the database
        Toast.makeText(getApplicationContext(),toastMessage, Toast.LENGTH_LONG).show();


    }

    public void EditToast(View view){
        Bundle extras = getIntent().getExtras();
        String userLoggedIn = extras.getString("userLoggedIn");

        Intent intent = new Intent(this, EditToastActivity.class);
        intent.putExtra("userLoggedIn", userLoggedIn);
        startActivity(intent);
        finish();

    }

    private void changeLastLoginMessage(String userLoggedIn){

        List<User> users = database.userDAO().getUser(userLoggedIn);

        if (!users.isEmpty()){
            List<LastLogin> logins = database.lastLoginDAO().findLastLogin(users.get(0).id);
            if(!logins.isEmpty()){
                TextView lastLogin = (TextView) findViewById(R.id.textLastLogin);
                Date login = new Date(logins.get(logins.size()-(logins.size()==1 ?1:2)).date);
                lastLogin.setText("Last Login " + login.toString());
            }}
    }
}
