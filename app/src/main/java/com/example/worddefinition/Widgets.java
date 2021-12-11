package com.example.worddefinition;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Widgets {

    private final EditText editTextEnterWord;
    private final Button buttonSearch;
    private final TextView textViewResultPhonetic;
    private final TextView textViewResultDefinitions;
    private final TextView textViewResultPartOfSpeech;

    public Widgets(EditText editTextEnterWord,
                   Button buttonSearch,
                   TextView textViewResultPhonetic,
                   TextView textViewResultDefinitions,
                   TextView textViewResultPartOfSpeech) {
        this.editTextEnterWord = editTextEnterWord;
        this.buttonSearch = buttonSearch;
        this.textViewResultPhonetic = textViewResultPhonetic;
        this.textViewResultDefinitions = textViewResultDefinitions;
        this.textViewResultPartOfSpeech = textViewResultPartOfSpeech;
    }

    public EditText getEditTextEnterWord() {
        return editTextEnterWord;
    }

    public Button getButtonSearch() {
        return buttonSearch;
    }

    public TextView getTextViewResultPhonetic() {
        return textViewResultPhonetic;
    }

    public TextView getTextViewResultPartOfSpeech() {
        return textViewResultPartOfSpeech;
    }

    public TextView getTextViewResultDefinitions() {
        return textViewResultDefinitions;


    }
}
