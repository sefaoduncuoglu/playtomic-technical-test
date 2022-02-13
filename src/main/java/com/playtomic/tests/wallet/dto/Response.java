package com.playtomic.tests.wallet.dto;

import com.playtomic.tests.wallet.constant.Constants;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Objects;

@Data
public class Response {

    private final HttpStatus httpStatus;
    private final HashMap<String, String> fields;

    private Response(Builder builder) {
        this.httpStatus = builder.httpStatus;
        this.fields = builder.fields;
    }

    public static class Builder {
        private HttpStatus httpStatus;
        private final HashMap<String, String> fields;

        public Builder() {
            this.fields = new HashMap<>();
        }

        public Builder add(String key, String value) {
            this.fields.put(key, Objects.requireNonNullElse(value, ""));
            return this;
        }

        public Builder code(String value) {
            this.fields.put(Constants.CODE, value);
            return this;
        }

        public Builder message(String value) {
            this.fields.put(Constants.MESSAGE, value);
            return this;
        }

        public Builder error(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder success() {
            this.httpStatus = HttpStatus.OK;
            return this;
        }

        public ResponseEntity<?> build() {
            return new ResponseEntity<>(new Response(this), this.httpStatus);
        }
    }
}