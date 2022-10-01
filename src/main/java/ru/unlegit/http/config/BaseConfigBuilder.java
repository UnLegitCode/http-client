package ru.unlegit.http.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseConfigBuilder<B extends BaseConfigBuilder<B>> {

    int connectTimeout;
    int readTimeout;
    boolean useSecureProtocol;

    private B self() {
        return (B) this;
    }

    public B setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;

        return self();
    }

    public B setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;

        return self();
    }

    public B useSecureProtocol() {
        useSecureProtocol = true;

        return self();
    }

    public HttpConfig build() {
        return new HttpConfig(connectTimeout, readTimeout, useSecureProtocol);
    }
}