package com.example.medicmadskill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                btnNext.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    btnNext.setEnabled(false);
                    btnNext.setTextColor(getResources().getColor(R.color.white));
                    btnNext.setBackgroundColor(getResources().getColor(R.color.btn_disabled));
                } else {
                    btnNext.setEnabled(true);
                    btnNext.setBackgroundColor(getResources().getColor(R.color.purple_500));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(editEmail.getText())) {
                    btnNext.setEnabled(false);
                }
            }
        });

        btnNext.setOnClickListener(v -> {
            MedicAPI api = MedicAPI.retrofit.create(MedicAPI.class);
            Call<Void> call = api.sendCode(editEmail.getText().toString());

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AuthActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("email", editEmail.getText().toString());
                        editor.apply();
                        Intent code = new Intent(AuthActivity.this, CodeActivity.class);
                        startActivity(code);
                    } else {
                        Toast.makeText(AuthActivity.this, "" + response.body(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, Throwable t) {
                    Toast.makeText(AuthActivity.this, "Ошибка" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }
}