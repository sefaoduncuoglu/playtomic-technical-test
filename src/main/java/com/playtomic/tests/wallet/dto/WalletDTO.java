package com.playtomic.tests.wallet.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {

    private Long id;

    @NonNull
    private Long userId;

    @NonNull
    private String address;

    private BigDecimal balance;

    @NonNull
    private String currency;

    @NonNull
    private String type;

}
