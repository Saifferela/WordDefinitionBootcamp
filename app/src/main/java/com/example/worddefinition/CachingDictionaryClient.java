package com.example.worddefinition;

import java.util.HashMap;
import java.util.Map;

public class CachingDictionaryClient implements Client {

    DictionaryClient dictionaryClient;
    Map<String, Map<String, Object>> saveCaching = new HashMap<>();

    public CachingDictionaryClient(DictionaryClient dictionaryClient) {
        this.dictionaryClient = dictionaryClient;
    }

    @Override
    public Map<String, Object> getDefinition(String word) {

        if (saveCaching.get(word) != null) {
            return saveCaching.get(word);
        }

        Map<String, Object> definition = dictionaryClient.getDefinition(word);

        saveCaching.put(word, definition);
        System.out.println(saveCaching.entrySet());

        return definition;
    }
}
