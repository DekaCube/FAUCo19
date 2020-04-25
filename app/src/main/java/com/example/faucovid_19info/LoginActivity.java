package com.example.faucovid_19info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Kill the ugly App Title Bar at the top
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
    }
}
