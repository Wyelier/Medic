package com.example.medicmadskill;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Button btnNext = findViewById(R.id.btnLog);
        EditText editEmail = findViewById(R.id.EditName);

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    btnNext.setEnabled(false);
                    btnNext.setTextColor(getResources().getColor(R.color.white));
                    btnNext.setBackgroundColor(getResources().getColor(R.color.btn_disabled));
                }
                else {
                    btnNext.setEnabled(true);
                    btnNext.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    btnNext.setOnClickListener(v -> {
                        Intent email = new Intent(AuthActivity.this, CodeActivity.class);
                        startActivity(email);
                        finish();
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
