package com.playtomic.tests.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Charge extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column
    private BigDecimal amount;

    @Column
    private String currency;

    @Column(name = "payment_id")
    private String paymentId;

}
