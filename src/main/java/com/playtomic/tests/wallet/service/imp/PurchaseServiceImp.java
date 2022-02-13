package com.playtomic.tests.wallet.service.imp;

import com.playtomic.tests.wallet.dto.PurchaseDTO;
import com.playtomic.tests.wallet.entity.Purchase;
import com.playtomic.tests.wallet.entity.Transaction;
import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.enums.type.TransactionTypeEnum;
import com.playtomic.tests.wallet.exception.InsufficientBalanceException;
import com.playtomic.tests.wallet.exception.PurchaseNotFoundException;
import com.playtomic.tests.wallet.repository.PurchaseRepository;
import com.playtomic.tests.wallet.service.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImp implements PurchaseService {

    private final WalletServiceImp walletService;
    private final TransactionServiceImp transactionService;

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImp(WalletServiceImp walletService,
                              TransactionServiceImp transactionService,
                              PurchaseRepository purchaseRepository) {
        this.walletService = walletService;
        this.transactionService = transactionService;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    @Transactional
    public PurchaseDTO save(PurchaseDTO purchaseDTO) {

        synchronized (this) {

            // save user's purchase item info
            Wallet wallet = walletService.findByIdAndUserIdAndAndCurrency(
                    purchaseDTO.getWalletId(),
                    purchaseDTO.getUserId(),
                    purchaseDTO.getCurrency());

            // check whether user has sufficient balance in wallet for purchase.
            if (wallet.getBalance().compareTo(purchaseDTO.getAmount()) < 0) {
                throw new InsufficientBalanceException();
            }

            Purchase purchase = Purchase.builder()
                    .userId(purchaseDTO.getUserId())
                    .amount(purchaseDTO.getAmount())
                    .currency(purchaseDTO.getCurrency())
                    .build();
            purchaseRepository.save(purchase);
            purchaseDTO.setId(purchase.getId());

            // minus balance after transaction
            wallet.setBalance(wallet.getBalance().subtract(purchase.getAmount()));
            walletService.save(wallet);

            // add new transaction
            transactionService.save(
                    Transaction.builder()
                            .userId(purchaseDTO.getUserId())
                            .walletId(purchaseDTO.getWalletId())
                            .type(TransactionTypeEnum.PURCHASE.toString())
                            .amount(purchaseDTO.getAmount())
                            .currency(purchaseDTO.getCurrency())
                            .description(purchaseDTO.getAmount()
                                    + " "
                                    + purchaseDTO.getCurrency()
                                    + " has been spent! from balance.")
                            .build()
            );
        }

        return purchaseDTO;
    }

    @Override
    @Transactional
    public void revertPurchase(Long purchaseId) {

        synchronized (this) {

            Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(PurchaseNotFoundException::new);

            purchase.setReverted(true);
            purchaseRepository.save(purchase);
            // find wallet
            Wallet wallet = walletService.findByIdAndUserIdAndAndCurrency(purchase.getWalletId(), purchase.getUserId(), purchase.getCurrency());

            // add balance after revert and update wallet
            wallet.setBalance(wallet.getBalance().add(purchase.getAmount()));
            walletService.save(wallet);

            // add new transaction
            transactionService.save(
                    Transaction.builder()
                            .userId(purchase.getUserId())
                            .type(TransactionTypeEnum.REFUND.toString())
                            .amount(purchase.getAmount())
                            .currency(purchase.getCurrency())
                            .description("Purchase: " + purchase.getId() + " has been reverted! Amount will be added into wallet back!")
                            .build()
            );
        }
    }
}
