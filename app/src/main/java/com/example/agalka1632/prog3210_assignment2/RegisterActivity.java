package com.example.agalka1632.prog3210_assignment2;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private EditText confirmPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

            DBHelper db = new DBHelper(this);

            User user = db.getUser(usernameAnswer);
            if(user==null){

                user= new User();
                user.setUsername(usernameAnswer);
                user.setPassword(passwordAnswer);

                String log = "Username: " + user.getUsername();
                Log.d("Name: ", log);
               db.addUser(user);

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
