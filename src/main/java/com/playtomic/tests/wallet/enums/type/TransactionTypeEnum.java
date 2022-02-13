package com.playtomic.tests.wallet.enums.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionTypeEnum {
    CHARGE("CHARGE"),
    REFUND("REFUND"),
    PURCHASE("PURCHASE");

    private final String type;

    @Override
    public String toString() {
        return type;
    }

}
