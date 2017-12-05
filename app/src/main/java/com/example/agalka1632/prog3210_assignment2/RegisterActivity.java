package com.example.agalka1632.prog3210_assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = AppDatabase.getDatabase(getApplicationContext());
    }


    public void registerAccount(View view){

        usernameText=(EditText) findViewById(R.id.registerUsername);
        passwordText=(EditText) findViewById(R.id.registerPassword);
        confirmPasswordText=(EditText) findViewById(R.id.registerConfirmPassword);

        String usernameAnswer = usernameText.getText().toString();
        String passwordAnswer = passwordText.getText().toString();
        String confirmPasswordAnswer = confirmPasswordText.getText().toString();

        if (!passwordAnswer.equals(confirmPasswordAnswer)){
            passwordText.setError("Password does not match");
            confirmPasswordText.setError("Password does not match");

        }else{

            List<User> users = database.userDAO().getUser(usernameAnswer);
            //creates a new account if there is no user with the username provided
            if(users.isEmpty()){
                database.userDAO().addUser(new User( usernameAnswer, passwordAnswer));
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("userLoggedIn", usernameAnswer);
                startActivity(intent);
                finish();
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "That user already exists";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }


    }

}
