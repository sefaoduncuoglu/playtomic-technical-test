package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.service.imp.StripeServiceImp;
import lombok.NonNull;

import java.math.BigDecimal;

public interface StripeService {

    StripeServiceImp.ChargeResponse charge(@NonNull String creditCardNumber, @NonNull BigDecimal amount);

    void refund(@NonNull String paymentId);

}
