package ru.unlegit.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.function.Consumer;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HttpClient {

    private static final Gson DEFAULT_GSON = new Gson();

    HttpConfig config;
    Gson gson;

    public HttpResponse executeRequest(HttpRequest request) {
        return request.execute(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static HttpClient newClient() {
        return new HttpClient(new HttpConfig.Impl(), DEFAULT_GSON);
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder {

        @Setter
        HttpConfig config;
        final GsonBuilder gsonBuilder = new GsonBuilder().setLenient();

        public Builder editConfig(Consumer<HttpConfig> configEditor) {
            if (config == null) {
                config = new HttpConfig.Impl();
            }

            configEditor.accept(config);

            return this;
        }

        public <T> Builder setTypeAdapter(Class<T> type, JsonAdapter<?, T> adapter) {
            gsonBuilder.registerTypeAdapter(type, adapter);

            return this;
        }

        public HttpClient build() {
            return new HttpClient(config, gsonBuilder.create());
        }
    }
}