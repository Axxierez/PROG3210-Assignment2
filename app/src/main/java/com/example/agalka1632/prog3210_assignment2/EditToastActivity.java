package com.example.agalka1632.prog3210_assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class EditToastActivity extends AppCompatActivity {

    String userLoggedIn;
    private AppDatabase database;
    private ToastMessage existingToast;
    private EditText toastText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getDatabase(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        userLoggedIn = extras.getString("userLoggedIn");

        List<User> users = database.userDAO().getUser(userLoggedIn);
        List<ToastMessage> toasts = database.toastMessageDAO().findToastForUser(users.get(0).id);
        if (toasts.size()==1)
            existingToast=toasts.get(0);


        setContentView(R.layout.activity_edit_toast);
    }


    public void SaveToast(View view){
        List<User> users = database.userDAO().getUser(userLoggedIn);
        toastText=(EditText) findViewById(R.id.toastMessageText);


        String toastAnswer = toastText.getText().toString();
        String newToastText =toastAnswer.trim()!="" ? toastAnswer : "Welcome!";

        if(existingToast==null)
        database.toastMessageDAO().addToast(new ToastMessage(users.get(0).id, newToastText ));
        else {
            existingToast.setMessage(newToastText);
            database.toastMessageDAO().updateToast(existingToast);
        }


        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("userLoggedIn", userLoggedIn);
        startActivity(intent);
        finish();
    }
}
