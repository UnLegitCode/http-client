package ru.unlegit.http.request;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.unlegit.http.HttpClient;

import java.net.HttpURLConnection;

public class DeleteRequest extends HttpRequest {

    private DeleteRequest(String url) {
        super(url);
    }

    @Override
    @SneakyThrows
    protected void processConnection(HttpURLConnection connection, HttpClient client) {
        connection.setRequestMethod("DELETE");

        super.processConnection(connection, client);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder extends HttpRequest.Builder<DeleteRequest, Builder> {

        @Override
        public DeleteRequest build() {
            return new DeleteRequest(constructURL());
        }
    }
}
