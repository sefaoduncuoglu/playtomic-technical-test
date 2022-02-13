package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.dto.TransactionDTO;
import com.playtomic.tests.wallet.entity.Transaction;

import java.util.List;

public interface TransactionService {

    void save(Transaction transaction);

    List<TransactionDTO> findAllByCreatedAtBetweenFirstDateAndLastDate(Long userId, String startDate, String endDate);
}
