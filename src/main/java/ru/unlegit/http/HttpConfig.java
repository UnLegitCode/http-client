package ru.unlegit.http;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

public abstract class HttpConfig {

    public abstract int getConnectTimeout();

    public abstract void setConnectTimeout(int timeout);

    public abstract int getReadTimeout();

    public abstract void setReadTimeout(int timeout);

    public abstract boolean isUseSecureProtocol();

    public abstract void setUseSecureProtocol(boolean useSecureProtocol);

    @Data
    @EqualsAndHashCode(callSuper = false)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class Impl extends HttpConfig {

        int connectTimeout;
        int readTimeout;
        boolean useSecureProtocol;
    }
}