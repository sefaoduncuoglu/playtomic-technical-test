package com.playtomic.tests.wallet.exception;

import com.playtomic.tests.wallet.dto.Response;
import com.playtomic.tests.wallet.enums.ApplicationErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.playtomic.tests.wallet.constant.Constants.DETAILS;

/**
 * REST exception handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {
        return new Response
                .Builder()
                .error(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(ApplicationErrorEnum.SOMETHING_WENT_WRONG.getCode())
                .message(ApplicationErrorEnum.SOMETHING_WENT_WRONG.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidInputException(HttpMessageNotReadableException e) {
        return new Response
                .Builder()
                .error(HttpStatus.BAD_REQUEST)
                .code(ApplicationErrorEnum.INVALID_INPUT.getCode())
                .message(ApplicationErrorEnum.INVALID_INPUT.getMessage())
                .add(DETAILS, e.getCause().getMessage())
                .build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new Response
                .Builder()
                .error(HttpStatus.BAD_REQUEST)
                .code(ApplicationErrorEnum.INVALID_METHOD_ARGUMENTS.getCode())
                .message(ApplicationErrorEnum.INVALID_METHOD_ARGUMENTS.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        return new Response
                .Builder()
                .error(HttpStatus.NOT_FOUND)
                .code(ApplicationErrorEnum.USER_NOT_FOUND.getCode())
                .message(ApplicationErrorEnum.USER_NOT_FOUND.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<?> handleWalletNotFoundException(WalletNotFoundException ex) {
        return new Response
                .Builder()
                .error(HttpStatus.NOT_FOUND)
                .code(ApplicationErrorEnum.WALLET_NOT_FOUND.getCode())
                .message(ApplicationErrorEnum.WALLET_NOT_FOUND.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return new Response
                .Builder()
                .error(HttpStatus.BAD_REQUEST)
                .code(ApplicationErrorEnum.INSUFFICIENT_BALANCE.getCode())
                .message(ApplicationErrorEnum.INSUFFICIENT_BALANCE.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidPaymentIdException.class)
    public ResponseEntity<?> handleInvalidPaymentIdException(InvalidPaymentIdException ex) {
        return new Response
                .Builder()
                .error(HttpStatus.BAD_REQUEST)
                .code(ApplicationErrorEnum.INVALID_PAYMENT_ID.getCode())
                .message(ApplicationErrorEnum.INVALID_PAYMENT_ID.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }


    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<?> handlePurchaseNotFoundException(PurchaseNotFoundException ex) {
        return new Response
                .Builder()
                .error(HttpStatus.BAD_REQUEST)
                .code(ApplicationErrorEnum.PURCHASE_NOT_FOUND.getCode())
                .message(ApplicationErrorEnum.PURCHASE_NOT_FOUND.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }


    @ExceptionHandler(DateFormatException.class)
    public ResponseEntity<?> handleDateFormatException(DateFormatException ex) {
        return new Response
                .Builder()
                .error(HttpStatus.NOT_FOUND)
                .code(ApplicationErrorEnum.DATE_FORMAT_EXCEPTION.getCode())
                .message(ApplicationErrorEnum.DATE_FORMAT_EXCEPTION.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<?> handleEmptyInputException(EmptyInputException ex) {
        return new Response
                .Builder()
                .error(HttpStatus.NOT_FOUND)
                .code(ApplicationErrorEnum.EMPTY_CREDIT_CARD_OR_AMOUNT_INPUT.getCode())
                .message(ApplicationErrorEnum.EMPTY_CREDIT_CARD_OR_AMOUNT_INPUT.getMessage())
                .add(DETAILS, ex.getMessage())
                .build();
    }

}
