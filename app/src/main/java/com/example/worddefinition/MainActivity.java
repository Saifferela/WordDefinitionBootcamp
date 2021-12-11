package com.example.worddefinition;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Widgets widgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        widgets = new Widgets(
                findViewById(R.id.editTextEnterWord),
                findViewById(R.id.buttonSearch),
                findViewById(R.id.textViewResultPhonetic),
                findViewById(R.id.textViewResultDefinitions),
                findViewById(R.id.textViewResultPartOfSpeech));

        widgets.getButtonSearch().setOnClickListener(v -> {
            if (widgets.getEditTextEnterWord().getText().toString().trim().equals("")) {
                Toast.makeText(MainActivity.this, R.string.noUserInput, Toast.LENGTH_LONG).show();
            } else {
                String word = widgets.getEditTextEnterWord().getText().toString();

                new DefinitionTask(this).execute(word);
            }
        });
    }
}