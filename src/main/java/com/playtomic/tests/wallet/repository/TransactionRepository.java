package com.playtomic.tests.wallet.repository;

import com.playtomic.tests.wallet.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserIdAndCreatedAtGreaterThanAndCreatedAtLessThan(Long userId, LocalDateTime startDate, LocalDateTime endDate);

}
