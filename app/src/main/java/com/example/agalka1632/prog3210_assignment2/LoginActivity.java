package com.example.agalka1632.prog3210_assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameText;
    private EditText passwordText;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getDatabase(getApplicationContext());
        setContentView(R.layout.activity_login);

    }

    public void login(View view){

        usernameText=(EditText) findViewById(R.id.registerUsername);
        passwordText=(EditText) findViewById(R.id.registerPassword);

        String usernameAnswer = usernameText.getText().toString();
        String passwordAnswer = passwordText.getText().toString();

        List<User> users = database.userDAO().getUser(usernameAnswer);

        if (!users.isEmpty() && passwordAnswer.equals(users.get(0).getPassword())){

            User user = users.get(0);
            //Creates a new entry in the last_login table
            database.lastLoginDAO().addLastLogin(new LastLogin(user.id,new Date().toString()));
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("userLoggedIn", usernameAnswer);
            startActivity(intent);
            finish();
        }else{
            Context context = getApplicationContext();
            CharSequence text = "Incorrect Password/User Does Not Exist";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void viewRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}

