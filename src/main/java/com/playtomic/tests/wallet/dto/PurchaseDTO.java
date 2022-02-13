package com.playtomic.tests.wallet.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

    private Long id;

    @NonNull
    private Long userId;

    @NonNull
    private Long walletId;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private String currency;
}
