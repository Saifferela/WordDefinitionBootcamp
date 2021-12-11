package com.example.worddefinition;

import java.util.Collections;
import java.util.List;

public class DefinitionView {
    private String phonetic;
    private String partOfSpeech;
    private List<String> definition;

    private String descriptionPhonetic = "Phonetic: ";
    private String descriptionPartOfSpeech = "Part of Speech: ";
    private String descriptionDefinitions = "Definitions: ";
    private String descriptionSpace = "\n\n";

    public DefinitionView(String phonetic,
                          String partOfSpeech,
                          List<String> definition) {
        this.phonetic = phonetic;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
    }

    public DefinitionView() {
        this.phonetic = "Wrong word!";
        this.partOfSpeech = "";
        this.definition = Collections.singletonList("");
    }

    public String getPhonetic() {
        return descriptionPhonetic + phonetic;
    }

    public String getPartOfSpeech() {
        return descriptionPartOfSpeech + partOfSpeech;
    }

    public String getDefinition() {
        String result = "";

        for (int i = 0; i < definition.size(); i++) {
            result += descriptionDefinitions + definition.get(i) + descriptionSpace;
        }

        return result;
    }
}
