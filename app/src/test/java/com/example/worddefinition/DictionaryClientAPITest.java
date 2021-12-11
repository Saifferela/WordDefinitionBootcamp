package com.example.worddefinition;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DictionaryClientAPITest {

    private final DictionaryClient dictionary = new DictionaryClient();

    @Test
    public void getDefinitionForWe_shouldReturnDefinition() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String definitionForWe = "[\n" +
                "  {\n" +
                "    \"word\": \"we\",\n" +
                "    \"phonetic\": \"wiː\",\n" +
                "    \"phonetics\": [\n" +
                "      {\n" +
                "        \"text\": \"wiː\",\n" +
                "        \"audio\": \"//ssl.gstatic.com/dictionary/static/sounds/20200429/we--_gb_1.mp3\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"origin\": \"Old English, of Germanic origin; related to Dutch wij and German wir .\",\n" +
                "    \"meanings\": [\n" +
                "      {\n" +
                "        \"partOfSpeech\": \"pronoun\",\n" +
                "        \"definitions\": [\n" +
                "          {\n" +
                "            \"definition\": \"used by a speaker to refer to himself or herself and one or more other people considered together.\",\n" +
                "            \"example\": \"shall we have a drink?\",\n" +
                "            \"synonyms\": [],\n" +
                "            \"antonyms\": []\n" +
                "          },\n" +
                "          {\n" +
                "            \"definition\": \"used in formal contexts for or by a royal person, or by a writer or editor, to refer to himself or herself.\",\n" +
                "            \"example\": \"in this section we discuss the reasons for this decision\",\n" +
                "            \"synonyms\": [],\n" +
                "            \"antonyms\": []\n" +
                "          },\n" +
                "          {\n" +
                "            \"definition\": \"used condescendingly to refer to the person being addressed.\",\n" +
                "            \"example\": \"how are we today?\",\n" +
                "            \"synonyms\": [],\n" +
                "            \"antonyms\": []\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        List<Map<String, Object>> result = objectMapper.readValue(definitionForWe,
                new TypeReference<List<Map<String, Object>>>() {
                });

        Map<String, Object> definition = dictionary.getDefinition("we");

        assertEquals(definition, result.get(0));
    }

    @Test
    public void getDefinitionForEmptyWord_shouldReturnEmptyMap() {

        Map<String, Object> definition = dictionary.getDefinition("");
        assertEquals(definition, Collections.emptyMap());
    }

    @Test
    public void getDefinitionForNonExistingWord_shouldReturnEmptyMap() {

        Map<String, Object> definition = dictionary.getDefinition("sdfsdfsfd");
        assertEquals(definition, Collections.emptyMap());
    }

}