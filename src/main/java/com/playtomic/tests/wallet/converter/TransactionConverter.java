package com.playtomic.tests.wallet.converter;

import com.playtomic.tests.wallet.dto.TransactionDTO;
import com.playtomic.tests.wallet.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {

    public TransactionDTO convertToDTO(Transaction transaction) {
        return TransactionDTO.builder().build();
    }

    public List<TransactionDTO> convertToDTO(List<Transaction> transactionList) {
        return transactionList.stream().map(item -> TransactionDTO.builder()
                .id(item.getId())
                .userId(item.getUserId())
                .walletId(item.getWalletId())
                .type(item.getType())
                .amount(item.getAmount())
                .currency(item.getCurrency())
                .description(item.getDescription())
                .createdAt(item.getCreatedAt())
                .build()).collect(Collectors.toList());
    }
}