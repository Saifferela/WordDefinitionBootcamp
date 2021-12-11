package com.example.worddefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class DictionaryProcessingTest {

    private final DictionaryProcessing dictionaryProcessing = new DictionaryProcessing();

    MockWebServer server = new MockWebServer();

    @After
    public void shutdown() throws IOException {
        server.shutdown();
    }

    @Test
    public void getDefinitionViewForWe_shouldReturnDefinitionView() throws IOException {
        mockResponseBody("[\n" +
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
                "]");

        DictionaryClient dictionary = new DictionaryClient(server.url("request/").toString());
        Map<String, Object> wordData = dictionary.getDefinition("we");

        DefinitionView definitionView = dictionaryProcessing.processing(wordData);

        assertNotNull(definitionView);
    }

    @Test
    public void processingEmptyMap_shouldReturnDefinitionViewWithDefaultValues() {

        DefinitionView expectedResult = new DefinitionView();

        DefinitionView actual = dictionaryProcessing.processing(Collections.emptyMap());

        assertEquals(expectedResult.getDefinition(), actual.getDefinition());
        assertEquals(expectedResult.getPartOfSpeech(), actual.getPartOfSpeech());
        assertEquals(expectedResult.getPhonetic(), actual.getPhonetic());
    }

    private void mockResponseBody(String body) throws IOException {
        server.enqueue(new MockResponse().setBody(body));
        server.start();
    }
}
