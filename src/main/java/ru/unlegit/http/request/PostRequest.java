package ru.unlegit.http.request;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.unlegit.http.HttpClient;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class PostRequest extends HttpRequest {

    private final Object body;

    private PostRequest(String url, Object body) {
        super(url);

        this.body = body;
    }

    @Override
    @SneakyThrows
    protected void processConnection(HttpURLConnection connection, HttpClient client) {
        connection.setRequestMethod("POST");

        if (body != null) {
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(client.getGson().toJson(body).getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder extends HttpRequest.Builder<PostRequest, Builder> {

        private Object body;

        public Builder body(Object body) {
            this.body = body;

            return this;
        }

        @Override
        public PostRequest build() {
            return new PostRequest(constructURL(), body);
        }
    }
}