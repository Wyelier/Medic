package com.example.medicmadskill;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_activity);

        EditText[] codeDigits = {
                findViewById(R.id.editCode1),
                findViewById(R.id.editCode2),
                findViewById(R.id.editCode3),
                findViewById(R.id.editCode4)
        };

        for(int i = 0; i < codeDigits.length; i++) {
            final int currInd = i;
            final int nextInd = (i == codeDigits.length - 1) ? i : i + 1;
            final int prevInd = (i == 0) ? i : i - 1;

            codeDigits[i].addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1) {
                        if (nextInd != currInd) {
                            codeDigits[nextInd].requestFocus();
                        }
                    } else if (s.length() == 0) {
                        if (prevInd != currInd) {
                            codeDigits[prevInd].requestFocus();
                        }
                    }
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 1 && currInd != codeDigits.length - 1) {
                        codeDigits[nextInd].requestFocus();
                    }
                }
            });
        }
    }
}
