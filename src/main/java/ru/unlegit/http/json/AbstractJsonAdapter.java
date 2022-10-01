package ru.unlegit.http.json;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public abstract class AbstractJsonAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {}