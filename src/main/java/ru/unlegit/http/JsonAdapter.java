package ru.unlegit.http;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public abstract class JsonAdapter<J extends JsonElement, T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private final JsonType<J> jsonType = (JsonType<J>) (new TypeToken<J>() {}.isAssignableFrom(JsonObject.class) ? JsonType.OBJECT : JsonType.ARRAY);

    @Override
    public final JsonElement serialize(T t, Type type, JsonSerializationContext context) {
        J json = jsonType.createElement();

        serialize(t, json, context);

        return json;
    }

    protected abstract void serialize(T t, J json, JsonSerializationContext context);

    @Override
    public final T deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return deserialize((J) json, context);
    }

    protected abstract T deserialize(J json, JsonDeserializationContext context) throws JsonParseException;

    @FunctionalInterface
    private interface JsonType<J extends JsonElement> {

        J createElement();

        JsonType<JsonObject> OBJECT = JsonObject::new;
        JsonType<JsonArray> ARRAY = JsonArray::new;
    }
}