package com.example.worddefinition;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Map;

public class DefinitionTask extends AsyncTask<String, String, DefinitionView> {

    private final MainActivity mainActivity;

    public DefinitionTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        mainActivity.widgets.getTextViewResultPhonetic().setText("Wait...");
        mainActivity.widgets.getTextViewResultPartOfSpeech().setText("");
        mainActivity.widgets.getTextViewResultDefinitions().setText("");
    }

    @Override
    protected DefinitionView doInBackground(String... strings) {
        Client dictionaryClient = new CachingDictionaryClient(new DictionaryClient());
        DictionaryProcessing dictionaryProcessing = new DictionaryProcessing();

        Map<String, Object> wordData = dictionaryClient.getDefinition(strings[0]);

        return dictionaryProcessing.processing(wordData);
    }

    @Override
    protected void onPostExecute(DefinitionView result) {
        super.onPostExecute(result);

        TextView textViewResultPhonetic = mainActivity.widgets.getTextViewResultPhonetic();
        TextView textViewResultPartOfSpeech = mainActivity.widgets.getTextViewResultPartOfSpeech();
        TextView textViewResultDefinitions = mainActivity.widgets.getTextViewResultDefinitions();

        textViewResultPhonetic.setText(result.getPhonetic());
        textViewResultPartOfSpeech.setText(result.getPartOfSpeech());
        textViewResultDefinitions.setText(result.getDefinition());
    }
}
