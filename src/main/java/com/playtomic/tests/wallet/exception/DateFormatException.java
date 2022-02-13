package com.playtomic.tests.wallet.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateFormatException extends RuntimeException {
    public DateFormatException(String message) {
        super(message);
    }
}
