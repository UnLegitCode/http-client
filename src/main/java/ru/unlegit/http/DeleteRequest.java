package ru.unlegit.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class DeleteRequest extends HttpRequest {

    private DeleteRequest(String url) {
        super(url);
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
