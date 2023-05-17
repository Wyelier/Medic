package com.example.medicmadskill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicmadskill.OnBoard.OnBoardActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                if (sharedPref.contains("token") && sharedPref.contains("pin")) {
                    Intent main = new Intent(SplashActivity.this, PasswordAppActivity.class);
                    startActivity(main);
                    finish();
                } else {
                    Intent onBoard = new Intent(SplashActivity.this, OnBoardActivity.class);
                    startActivity(onBoard);
                    finish();
                }
            }
        }, 2000);
    }
}