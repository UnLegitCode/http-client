package ru.unlegit.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JsonContent {

    Gson gson;
    String json;

    public String raw() {
        return json;
    }

    public <T> T asObject() {
        return gson.fromJson(json, new TypeToken<T>() {}.getType());
    }

    public <T> List<T> asList() {
        return gson.fromJson(json, new TypeToken<ArrayList<T>>() {}.getType());
    }
}