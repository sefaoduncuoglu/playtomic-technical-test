package com.playtomic.tests.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Purchase extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "wallet_id")
    private Long walletId;

    @Column
    private BigDecimal amount;

    @Column
    private String currency;

    @Column
    private Boolean reverted = false;

}
