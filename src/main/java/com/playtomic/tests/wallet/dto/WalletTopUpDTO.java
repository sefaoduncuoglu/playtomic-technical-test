package com.playtomic.tests.wallet.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletTopUpDTO {

    @NonNull
    private Long userId;

    @NonNull
    private Long walletId;

    @NonNull
    private String creditCardNumber;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private Currency currency;

    private String paymentId;

}
