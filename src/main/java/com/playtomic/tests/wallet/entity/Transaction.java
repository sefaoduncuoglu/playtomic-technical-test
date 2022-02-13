package com.playtomic.tests.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@SuperBuilder(toBuilder = true)
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "wallet_id")
    private Long walletId;

    @Column
    private String type;

    @Column
    private BigDecimal amount;

    @Column
    private String currency;

    @Column
    private String description;

}
