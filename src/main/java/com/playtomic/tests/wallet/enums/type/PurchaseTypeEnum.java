package com.playtomic.tests.wallet.enums.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PurchaseTypeEnum {
    SALE("SALE"),
    REFUND("REFUND");

    private final String type;

    @Override
    public String toString() {
        return type;
    }
}
