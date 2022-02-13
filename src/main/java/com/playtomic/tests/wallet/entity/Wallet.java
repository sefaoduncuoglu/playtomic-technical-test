package com.playtomic.tests.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column
    private String address;

    @Column
    private BigDecimal balance;

    @Column
    private String currency;

    @Column
    private String type;

}
