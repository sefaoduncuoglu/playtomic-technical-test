package com.playtomic.tests.wallet.service.imp;

import com.playtomic.tests.wallet.converter.TransactionConverter;
import com.playtomic.tests.wallet.dto.TransactionDTO;
import com.playtomic.tests.wallet.entity.Transaction;
import com.playtomic.tests.wallet.repository.TransactionRepository;
import com.playtomic.tests.wallet.service.TransactionService;
import com.playtomic.tests.wallet.util.DateFormatter;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;
    private final DateFormatter dateFormatter;

    public TransactionServiceImp(TransactionRepository transactionRepository,
                                 TransactionConverter transactionConverter, DateFormatter dateFormatter) {
        this.transactionRepository = transactionRepository;
        this.transactionConverter = transactionConverter;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDTO> findAllByCreatedAtBetweenFirstDateAndLastDate(Long userId, String startDate, String endDate) {
        List<Transaction> transactionList = transactionRepository.findAllByUserIdAndCreatedAtGreaterThanAndCreatedAtLessThan(
                userId,
                dateFormatter.startTimeOfDay(startDate),
                dateFormatter.endTimeOfDay(endDate));
        List<TransactionDTO> transactionDTOS = transactionConverter.convertToDTO(transactionList);
        return transactionDTOS.stream()
                .sorted(Comparator.comparing(TransactionDTO::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
