package com.example.worddefinition;

import java.util.Map;

public interface Client {
    Map<String, Object> getDefinition(String word);
}
