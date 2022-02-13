package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.dto.UserBalanceDTO;
import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.dto.WalletTopUpDTO;
import com.playtomic.tests.wallet.entity.Wallet;

import java.util.List;

public interface WalletService {

    WalletDTO findById(Long walletId);

    Wallet findByIdAndUserIdAndAndCurrency(Long walletId, Long userId, String currency);

    UserBalanceDTO getUserCurrentBalance(Long userId);

    WalletTopUpDTO topUpWallet(WalletTopUpDTO walletTopUpDTO);

    void refundTransaction(WalletTopUpDTO walletTopUpDTO);

    void save(Wallet wallet);

    void save(WalletDTO walletDTO);
}
