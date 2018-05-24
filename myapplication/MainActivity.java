package com.example.ak.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.difficulty_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button start = (Button) findViewById(R.id.start_button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selection = ((Spinner) findViewById(R.id.difficulty_spinner))
                        .getSelectedItem().toString();
                if (selection.equals("Easy")) {
                    startActivity(new Intent(MainActivity.this, EasyActivity.class));
                }
                else if (selection.equals("Medium")) {
                    startActivity(new Intent(MainActivity.this, MediumActivity.class));
                }
                else if (selection.equals("Hard")) {
                    startActivity(new Intent(MainActivity.this, HardActivity.class));
                }

            }
        });
    }




}
