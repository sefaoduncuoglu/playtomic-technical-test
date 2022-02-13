package com.playtomic.tests.wallet.converter;

import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.entity.Wallet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletConverter {
    public List<WalletDTO> convertToDTO(List<Wallet> walletList) {
        return walletList.stream().map(item -> WalletDTO.builder()
                .id(item.getId())
                .userId(item.getUserId())
                .address(item.getAddress())
                .type(item.getType())
                .balance(item.getBalance())
                .currency(item.getCurrency())
                .build()).collect(Collectors.toList());
    }

    public WalletDTO convertToDTO(Wallet wallet) {
        return WalletDTO.builder()
                .id(wallet.getId())
                .userId(wallet.getUserId())
                .address(wallet.getAddress())
                .type(wallet.getType())
                .balance(wallet.getBalance())
                .currency(wallet.getCurrency())
                .build();
    }

    public Wallet convertToEntity(WalletDTO walletDTO) {
        return Wallet.builder()
                .id(walletDTO.getId())
                .userId(walletDTO.getUserId())
                .address(walletDTO.getAddress())
                .type(walletDTO.getType())
                .balance(walletDTO.getBalance())
                .currency(walletDTO.getCurrency())
                .build();
    }
}
