package com.playtomic.tests.wallet.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPaymentIdException extends RuntimeException {
    public InvalidPaymentIdException(String message) {
        super(message);
    }
}
