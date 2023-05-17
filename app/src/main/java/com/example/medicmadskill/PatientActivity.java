package com.example.medicmadskill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.medicmadskill.MainScreen.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_card);
        Spinner spinner = findViewById(R.id.spinner);
        List<String> options = new ArrayList<>();
        options.add("Пол");
        options.add("Мужской");
        options.add("Женский");

        TextView textView = findViewById(R.id.textSkip);

        textView.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}