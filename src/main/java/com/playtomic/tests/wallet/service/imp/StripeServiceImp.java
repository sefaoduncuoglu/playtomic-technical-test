package com.playtomic.tests.wallet.service.imp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.playtomic.tests.wallet.exception.stripe.StripeRestTemplateResponseErrorHandler;
import com.playtomic.tests.wallet.exception.stripe.StripeServiceException;
import com.playtomic.tests.wallet.service.StripeService;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class StripeServiceImp implements StripeService {

    @NonNull
    private final URI chargesUri;

    @NonNull
    private final URI refundsUri;

    @NonNull
    private final RestTemplate restTemplate;

    public StripeServiceImp(@Value("${stripe.simulator.charges-uri}") @NonNull URI chargesUri,
                            @Value("${stripe.simulator.refunds-uri}") @NonNull URI refundsUri,
                            @NotNull RestTemplateBuilder restTemplateBuilder) {

        this.chargesUri = chargesUri;
        this.refundsUri = refundsUri;
        this.restTemplate = restTemplateBuilder.errorHandler(new StripeRestTemplateResponseErrorHandler()).build();
    }

    @Override
    public ChargeResponse charge(@NonNull String creditCardNumber, @NonNull BigDecimal amount) throws StripeServiceException {
        ChargeRequest body = ChargeRequest.builder().creditCardNumber(creditCardNumber).amount(amount).build();
        return restTemplate.postForObject(chargesUri.toString(), body, ChargeResponse.class);

    }

    @Override
    public void refund(@NonNull String paymentId) throws StripeServiceException {
        restTemplate.postForEntity(refundsUri.toString(), null, Object.class, paymentId);
    }

    @Builder
    @AllArgsConstructor
    private static class ChargeRequest {

        @NonNull
        @JsonProperty("credit_card")
        String creditCardNumber;

        @NonNull
        @JsonProperty("amount")
        BigDecimal amount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChargeResponse {

        @JsonProperty("id")
        private String paymentId;

        private BigDecimal amount;
    }

}
