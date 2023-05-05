package com.example.medicmadskill;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PassActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_activity);

        TextView textView = findViewById(R.id.textSkip);

        textView.setOnClickListener(v -> {
            Intent intent = new Intent(this, PatientActivity.class);
            startActivity(intent);
            finish();
        });
    }
}