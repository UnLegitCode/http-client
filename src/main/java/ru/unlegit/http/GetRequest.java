package ru.unlegit.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class GetRequest extends HttpRequest {

    private GetRequest(String url) {
        super(url);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder extends HttpRequest.Builder<GetRequest, Builder> {

        @Override
        public GetRequest build() {
            return new GetRequest(constructURL());
        }
    }
}