package ru.unlegit.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.unlegit.http.config.HttpConfig;
import ru.unlegit.http.json.AbstractJsonAdapter;
import ru.unlegit.http.request.HttpRequest;

import java.lang.reflect.Proxy;
import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HttpClient {

    private static final Gson DEFAULT_GSON = new Gson();
    private static final HttpConfig DEFAULT_CONFIG = new HttpConfig(1000, 2000, false);

    HttpConfig config;
    Gson gson;
    String address;

    public HttpResponse executeRequest(HttpRequest request) {
        return request.execute(this);
    }

    public <T> T implementRequester(Class<T> requesterType) {
        if (!requesterType.isInterface()) throw new UnsupportedOperationException("requester type must be an interface for proxy-implementation");

        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { requesterType }, (proxy, method, args) -> {


            return null;
        });
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static HttpClient newClient(String address) {
        return new HttpClient(DEFAULT_CONFIG, DEFAULT_GSON, address);
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder {

        HttpConfig config;
        final GsonBuilder gsonBuilder = new GsonBuilder().setLenient();
        String address;

        public Builder withConfig(HttpConfig config) {
            this.config = config;

            return this;
        }

        public LinkedConfigBuilder withConfig() {
            return new LinkedConfigBuilder(this);
        }

        public <T> Builder setTypeAdapter(Class<T> type, AbstractJsonAdapter<T> adapter) {
            gsonBuilder.registerTypeAdapter(type, adapter);

            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;

            return this;
        }

        public HttpClient build() {
            return new HttpClient(config, gsonBuilder.create(), Objects.requireNonNull(address));
        }
    }
}