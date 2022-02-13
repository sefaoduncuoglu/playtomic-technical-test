package com.playtomic.tests.wallet.service.imp;

import com.playtomic.tests.wallet.converter.WalletConverter;
import com.playtomic.tests.wallet.dto.UserBalanceDTO;
import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.dto.WalletTopUpDTO;
import com.playtomic.tests.wallet.entity.Charge;
import com.playtomic.tests.wallet.entity.Transaction;
import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.enums.type.TransactionTypeEnum;
import com.playtomic.tests.wallet.exception.EmptyInputException;
import com.playtomic.tests.wallet.exception.InvalidPaymentIdException;
import com.playtomic.tests.wallet.exception.WalletNotFoundException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImp implements WalletService {

    private final ChargeServiceImp chargeService;
    private final StripeServiceImp stripeService;
    private final TransactionServiceImp transactionService;
    private final WalletConverter walletConverter;
    private final WalletRepository walletRepository;

    public WalletServiceImp(ChargeServiceImp chargeService,
                            StripeServiceImp stripeService,
                            TransactionServiceImp transactionService,
                            WalletConverter walletConverter,
                            WalletRepository walletRepository) {
        this.chargeService = chargeService;
        this.stripeService = stripeService;
        this.transactionService = transactionService;
        this.walletConverter = walletConverter;
        this.walletRepository = walletRepository;
    }


    @Override
    public WalletDTO findById(Long walletId) throws WalletNotFoundException {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(WalletNotFoundException::new);
        return walletConverter.convertToDTO(wallet);
    }

    @Override
    public Wallet findByIdAndUserIdAndAndCurrency(Long walletId, Long userId, String currency) throws WalletNotFoundException {
        return walletRepository.findByIdAndUserIdAndCurrency(walletId, userId, currency).orElseThrow(WalletNotFoundException::new);
    }

    @Override
    public UserBalanceDTO getUserCurrentBalance(Long userId) throws WalletNotFoundException {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(WalletNotFoundException::new);
        return UserBalanceDTO.builder()
                .userId(wallet.getUserId())
                .currentBalance(wallet.getBalance())
                .currency(wallet.getCurrency())
                .build();
    }

    @Override
    @Transactional
    public WalletTopUpDTO topUpWallet(WalletTopUpDTO walletTopUpDTO) throws WalletNotFoundException {

        synchronized (this) {
            Wallet wallet = walletRepository.findByIdAndUserIdAndCurrency(
                    walletTopUpDTO.getWalletId(),
                    walletTopUpDTO.getUserId(),
                    walletTopUpDTO.getCurrency().getCurrencyCode()
            ).orElseThrow(WalletNotFoundException::new);

            try {
                StripeServiceImp.ChargeResponse chargeResponse = stripeService.charge(
                        walletTopUpDTO.getCreditCardNumber(),
                        walletTopUpDTO.getAmount());

                // paymentId will be returned so that user can return this transaction.
                walletTopUpDTO.setPaymentId(chargeResponse.getPaymentId());

                // save charge record.
                chargeService.save(
                        Charge.builder()
                                .userId(walletTopUpDTO.getUserId())
                                .amount(walletTopUpDTO.getAmount())
                                .currency(walletTopUpDTO.getCurrency().getCurrencyCode())
                                .paymentId(chargeResponse.getPaymentId())
                                .build());

                // add new transaction
                transactionService.save(
                        Transaction.builder()
                                .userId(walletTopUpDTO.getUserId())
                                .walletId(walletTopUpDTO.getWalletId())
                                .type(TransactionTypeEnum.CHARGE.toString())
                                .amount(walletTopUpDTO.getAmount())
                                .currency(walletTopUpDTO.getCurrency().getCurrencyCode())
                                .description(walletTopUpDTO.getAmount()
                                        + " "
                                        + walletTopUpDTO.getCurrency()
                                        + " has been added to wallet.")
                                .build());

                // update balance
                wallet.setBalance(wallet.getBalance().add(walletTopUpDTO.getAmount()));
                save(wallet);
            } catch (Exception exception) {
                if (exception.getMessage().contains("non-null")) {
                    throw new EmptyInputException();
                }
            }
        }
        return walletTopUpDTO;
    }

    @Override
    @Transactional
    public void refundTransaction(WalletTopUpDTO walletTopUpDTO) {

        synchronized (this) {

            Wallet wallet = walletRepository.findByUserId(walletTopUpDTO.getUserId())
                    .orElseThrow(WalletNotFoundException::new);

            // call stripe service
            try {
                stripeService.refund(walletTopUpDTO.getPaymentId());
            } catch (Exception e) {
                throw new InvalidPaymentIdException(e.getMessage());
            }
            // add new transaction
            transactionService.save(
                    Transaction.builder()
                            .userId(walletTopUpDTO.getUserId())
                            .walletId(walletTopUpDTO.getWalletId())
                            .type(TransactionTypeEnum.REFUND.toString())
                            .amount(walletTopUpDTO.getAmount())
                            .currency(walletTopUpDTO.getCurrency().getCurrencyCode())
                            .description("payment with" + walletTopUpDTO.getPaymentId() + " id has been refunded.")
                            .build());

            // minus balance after refund is completed
            wallet.setBalance(wallet.getBalance().subtract(walletTopUpDTO.getAmount()));
            this.save(wallet);
        }
    }

    @Override
    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public void save(WalletDTO walletDTO) {
        walletRepository.save(walletConverter.convertToEntity(walletDTO));
    }
}
