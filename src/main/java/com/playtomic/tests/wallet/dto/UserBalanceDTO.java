package com.playtomic.tests.wallet.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBalanceDTO {

    @NonNull
    private Long userId;

    private BigDecimal currentBalance;

    @NonNull
    private String currency;
}
