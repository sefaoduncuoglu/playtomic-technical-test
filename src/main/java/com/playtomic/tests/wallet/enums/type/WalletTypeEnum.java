package com.playtomic.tests.wallet.enums.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WalletTypeEnum {

    PERSONAL("PERSONAL"),
    ENTERPRISE("ENTERPRISE");

    private final String type;

    @Override
    public String toString() {
        return type;
    }
}
