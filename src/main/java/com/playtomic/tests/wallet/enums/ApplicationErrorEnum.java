package com.playtomic.tests.wallet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationErrorEnum {
    SOMETHING_WENT_WRONG("1000", "Something went wrong"),
    INVALID_INPUT("1001", "Invalid input"),
    INVALID_METHOD_ARGUMENTS("1002", "Invalid Method Arguments"),
    USER_NOT_FOUND("1003", "User not found"),
    WALLET_NOT_FOUND("1004", "Wallet not found"),
    PURCHASE_NOT_FOUND("1005", "Purchase not found"),
    INVALID_PAYMENT_ID("1006", "Invalid payment id:  "),
    INSUFFICIENT_BALANCE("1007", "Insufficient balance! Your balance is not enough for this transaction."),
    DATE_FORMAT_EXCEPTION("1008", "Date field must be entered in this format: yyyy-MM-dd"),
    EMPTY_CREDIT_CARD_OR_AMOUNT_INPUT("1009", "Credit card or amount is null.");

    private final String code;
    private final String message;

}