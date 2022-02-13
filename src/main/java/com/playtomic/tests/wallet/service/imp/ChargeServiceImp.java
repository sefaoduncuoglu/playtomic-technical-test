package com.playtomic.tests.wallet.service.imp;

import com.playtomic.tests.wallet.entity.Charge;
import com.playtomic.tests.wallet.repository.ChargeRepository;
import com.playtomic.tests.wallet.service.ChargeService;
import org.springframework.stereotype.Service;

@Service
public class ChargeServiceImp implements ChargeService {

    private final ChargeRepository chargeRepository;

    public ChargeServiceImp(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    @Override
    public void save(Charge charge) {
        chargeRepository.save(charge);
    }
}
