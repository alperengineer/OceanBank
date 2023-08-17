package com.aok.oceanbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogInScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
    }

    public void logIn(View view){

    }

    public void newCustomer(View view){

        Intent intent = new Intent(LogInScreen.this, RegisterScreen.class);
        startActivity(intent);
        finish();

    }

}