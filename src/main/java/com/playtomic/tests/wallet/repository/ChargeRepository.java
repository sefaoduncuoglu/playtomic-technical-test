package com.playtomic.tests.wallet.repository;

import com.playtomic.tests.wallet.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {
}
