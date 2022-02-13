package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.dto.PurchaseDTO;
import com.playtomic.tests.wallet.entity.Purchase;

public interface PurchaseService {

    void save(Purchase purchase);

    PurchaseDTO save(PurchaseDTO purchaseDTO);

    void revertPurchase(Long purchaseID);
}
