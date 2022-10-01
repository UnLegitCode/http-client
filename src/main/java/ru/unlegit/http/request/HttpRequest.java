package ru.unlegit.http.request;

import javafx.util.Pair;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import ru.unlegit.http.HttpClient;
import ru.unlegit.http.config.HttpConfig;
import ru.unlegit.http.HttpResponse;
import ru.unlegit.http.JsonContent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class HttpRequest {

    String url;

    protected void processConnection(HttpURLConnection connection, HttpClient client) {}

    @SneakyThrows
    public HttpResponse execute(HttpClient client) {
        HttpConfig config = client.getConfig();
        HttpURLConnection connection = (HttpURLConnection) (new URL(String.format(url, config.isUseSecureProtocol() ? "https" : "http", client.getAddress()))).openConnection();

        if (config.getConnectTimeout() > 0) {
            connection.setConnectTimeout(config.getConnectTimeout());
        }

        if (config.getReadTimeout() > 0) {
            connection.setReadTimeout(config.getReadTimeout());
        }

        processConnection(connection, client);

        String rawBody;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            rawBody = reader.lines().reduce((line1, line2) -> line1 + line2).orElse("");
        }

        return new HttpResponse(HttpResponse.Status.of(connection.getResponseCode()), new JsonContent(client.getGson(), rawBody));
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    @FieldDefaults(level = AccessLevel.PROTECTED)
    protected static abstract class Builder<R extends HttpRequest, B extends Builder<R, B>> {

        final List<Pair<String, Object>> parameters = new ArrayList<>();
        final List<String> paths = new ArrayList<>();

        public B path(String path) {
            paths.add(path);

            return self();
        }

        public B parameter(String name, Object value) {
            parameters.add(new Pair<>(name, value));

            return self();
        }

        private B self() {
            return (B) this;
        }

        protected String constructURL() {
            StringBuilder urlBuilder = new StringBuilder("%s").append("://").append("%s");

            for (int i = 0; i < paths.size(); i++) {
                urlBuilder.append("/").append(paths.get(i));
            }

            if (!parameters.isEmpty()) {
                urlBuilder.append("?").append(parameters.stream()
                        .map(pair -> pair.getKey() + "=" + pair.getValue())
                        .reduce((parameter1, parameter2) -> parameter1 + "&" + parameter2)
                        .get());
            }

            return urlBuilder.toString();
        }

        public abstract R build();
    }
}