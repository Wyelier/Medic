package com.example.medicmadskill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicmadskill.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeActivity extends AppCompatActivity {

    EditText numcode1, numcode2, numcode3, numcode4;
    TextView timeCode;
    ImageView back;

    private int resendTime = 60;
    private int selectedPosition = 0;
    private boolean isCurrentCode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        numcode1 = findViewById(R.id.editCode1);
        numcode2 = findViewById(R.id.editCode2);
        numcode3 = findViewById(R.id.editCode3);
        numcode4 = findViewById(R.id.editCode4);

        timeCode = findViewById(R.id.textNextCode);
        back = findViewById(R.id.emailBack);

        numcode1.addTextChangedListener(watcher);
        numcode2.addTextChangedListener(watcher);
        numcode3.addTextChangedListener(watcher);
        numcode4.addTextChangedListener(watcher);
        showKeyboard(numcode1);

        startCountDownTime();

        back.setOnClickListener(v -> {
            goEmail();
        });
    }

    private void goEmail() {
        Intent email = new Intent(this, AuthActivity.class);
        startActivity(email);
        finish();
    }

    private void showKeyboard(EditText code) {
        code.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(code, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTime() {
        new CountDownTimer(resendTime * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeCode.setText("Отправить код повторно можно \n будет через "+(millisUntilFinished/1000) +" секунд");
            }

            @Override
            public void onFinish() {
                timeCode.setText("Отправить код повторно");
            }
        }.start();
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() > 0) {
                switch(selectedPosition) {
                    case 0:
                        selectedPosition = 1;
                        showKeyboard(numcode2);
                        break;
                    case 1:
                        selectedPosition = 2;
                        showKeyboard(numcode3);
                        break;
                    case 2:
                        selectedPosition = 3;
                        showKeyboard(numcode4);
                        break;
                    case 3:
                        isCurrentCode = checkCode();
                        numcode1.setEnabled(false);
                        numcode2.setEnabled(false);
                        numcode3.setEnabled(false);
                        numcode4.setEnabled(false);

                        if(isCurrentCode) {
                            Toast.makeText(CodeActivity.this, "Все верно", Toast.LENGTH_SHORT).show();
                            Intent create_password = new Intent(CodeActivity.this, PasswordActivity.class);
                            startActivity(create_password);
                        } else {
                            Toast.makeText(CodeActivity.this, "Неверно введен код", Toast.LENGTH_SHORT).show();
                            goEmail();

                        }
                }
            }
        }
    };

    private boolean checkCode() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(CodeActivity.this);
        String email = sharedPref.getString("email", "");
        String userCode = numcode1.getText().toString() + numcode2.getText().toString() + numcode3.getText().toString() + numcode4.getText().toString();

        MedicAPI api = MedicAPI.retrofit.create(MedicAPI.class);
        Call<User> call = api.signin(email, userCode);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    User token = response.body();
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", token.getToken());
                    editor.apply();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(CodeActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return sharedPref.contains("token");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_DEL) {
            switch(selectedPosition) {
                case 1:
                    selectedPosition = 0;
                    showKeyboard(numcode1);
                    break;
                case 2:
                    selectedPosition = 1;
                    showKeyboard(numcode2);
                    break;
                case 3:
                    selectedPosition = 2;
                    showKeyboard(numcode3);
                    break;
            }
            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }
}
