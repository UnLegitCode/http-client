package ru.unlegit.http;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import ru.unlegit.http.config.BaseConfigBuilder;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class LinkedConfigBuilder extends BaseConfigBuilder<LinkedConfigBuilder> {

    private final HttpClient.Builder clientBuilder;

    public HttpClient.Builder and() {
        return clientBuilder.withConfig(build());
    }
}