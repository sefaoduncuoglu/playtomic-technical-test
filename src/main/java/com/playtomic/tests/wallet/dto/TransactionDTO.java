package com.playtomic.tests.wallet.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;

    @NonNull
    private Long userId;

    @NonNull
    private Long walletId;

    @NonNull
    private String type;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private String currency;

    private String description;

    @NonNull
    private LocalDateTime createdAt;

}
