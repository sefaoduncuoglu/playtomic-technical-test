package com.playtomic.tests.wallet.repository;

import com.playtomic.tests.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUserId(Long userId);

    List<Wallet> findAllByUserId(Long userId);

    Optional<Wallet> findByIdAndUserIdAndCurrency(Long id, Long userId, String currency);

}
