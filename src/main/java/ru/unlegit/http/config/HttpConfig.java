package ru.unlegit.http.config;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class HttpConfig {

    int connectTimeout;
    int readTimeout;
    boolean useSecureProtocol;

    public static HttpConfigBuilder newBuilder() {
        return new HttpConfigBuilder();
    }
}