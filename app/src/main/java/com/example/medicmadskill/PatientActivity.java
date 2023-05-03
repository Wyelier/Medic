package com.example.medicmadskill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_card);

        TextView acTV1 = findViewById(R.id.EditGender);
        ImageView delButton = findViewById(R.id.delButton);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, getResources()
                .getStringArray(R.array.Gender_Names));
        String selection;
        acTV1.setAdapter(arrayAdapter);
        acTV1.setCursorVisible(false);
        acTV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                acTV1.showDropDown();
                selection = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selection,
                        Toast.LENGTH_SHORT);
                delButton.setAlpha(.8f);
            }
        });

        acTV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                acTV1.showDropDown();
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acTV1.setText(null);
                delButton.setAlpha(.2f);
                selection = null;
            }
        });
    }
}