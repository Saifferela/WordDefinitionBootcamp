package com.example.worddefinition;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class DictionaryClient implements Client {

    public static String URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    public DictionaryClient() {
    }

    DictionaryClient(String url) {
        URL = url;
    }

    public Map<String, Object> getDefinition(String word) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            CloseableHttpResponse response = client.execute(new HttpGet(URL + word));

            String body = EntityUtils.toString(response.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(body.substring(1, body.length() - 1),
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (
                IOException | ParseException e) {
            e.getStackTrace();
        }
        return Collections.emptyMap();
    }
}