package ru.unlegit.http;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HttpResponse {

    Status status;
    JsonContent body;

    @AllArgsConstructor
    public enum Status {

        SUCCESS(200),
        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        NOT_FOUND(404),
        INTERNAL_SERVER_ERROR(500);

        @Getter
        private final int code;

        public static Status of(int code) {
            for (Status status : values()) {
                if (code == status.code) {
                    return status;
                }
            }

            throw new UnsupportedOperationException("non-implemented HTTP status");
        }
    }
}