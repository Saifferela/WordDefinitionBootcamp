package com.example.worddefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DictionaryProcessing {

    public DefinitionView processing(Map<String, Object> wordData) {

        DefinitionView result;
        String phonetic;
        String partOfSpeech;
        List<String> definition = new ArrayList<>();

        List<Map<String, Object>> meanings;
        Map<String, Object> meaningsList;
        List<Map<String, Object>> definitions;

        if (wordData == null || wordData.equals(Collections.emptyMap())) {
            return new DefinitionView();
        }

        meanings = (List<Map<String, Object>>) wordData.get("meanings");
        meaningsList = meanings.get(0);
        definitions = (List<Map<String, Object>>) meaningsList.get("definitions");


        phonetic = (String) wordData.get("phonetic");
        partOfSpeech = (String) meaningsList.get("partOfSpeech");
        for (int i = 0; i < definitions.size(); i++) {
            definition.add((String) definitions.get(i).get("definition"));
        }

        result = new DefinitionView(phonetic, partOfSpeech, definition);
        return result;
    }

}