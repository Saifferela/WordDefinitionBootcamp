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

public class DictionaryClientTest {

    MockWebServer server = new MockWebServer();

    @After
    public void shutdown() throws IOException {
        server.shutdown();
    }

    @Test
    public void getDefinitionForWe_shouldReturnDefinition() throws IOException {
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
        Map<String, Object> definition = dictionary.getDefinition("we");
        assertNotNull(definition);
    }

    @Test
    public void getDefinitionForEmptyWord_shouldReturnEmptyMap() throws IOException {

        mockResponseBody("Cannot GET /api/v2/entries/en/");

        DictionaryClient dictionary = new DictionaryClient(server.url("request/").toString());
        Map<String, Object> definition = dictionary.getDefinition("");
        assertEquals(definition, Collections.emptyMap());
    }

    @Test
    public void getDefinitionForNonExistingWord_shouldReturnEmptyMap() throws IOException {
        mockResponseBody("{\"title\":\"No Definitions Found\"," +
                "\"message\":\"Sorry pal, we couldn't find definitions for the word you were looking for.\"," +
                "\"resolution\":\"You can try the search again at later time or head to the web instead.\"}");

        DictionaryClient dictionary = new DictionaryClient(server.url("request/").toString());
        Map<String, Object> definition = dictionary.getDefinition("");
        assertEquals(definition, Collections.emptyMap());
    }

    private void mockResponseBody(String body) throws IOException {
        server.enqueue(new MockResponse().setBody(body));
        server.start();
    }
}