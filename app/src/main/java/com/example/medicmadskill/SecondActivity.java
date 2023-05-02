package com.example.medicmadskill;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Button btnNext = findViewById(R.id.btnLog);
        EditText editEmail = findViewById(R.id.EditEmail);

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
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
