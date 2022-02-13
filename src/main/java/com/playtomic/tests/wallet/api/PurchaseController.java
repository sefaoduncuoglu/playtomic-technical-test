package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.dto.PurchaseDTO;
import com.playtomic.tests.wallet.service.imp.PurchaseServiceImp;
import com.playtomic.tests.wallet.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    private final PurchaseServiceImp purchaseService;
    private final UserServiceImp userService;

    public PurchaseController(PurchaseServiceImp purchaseService, UserServiceImp userService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> newPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        userService.findById(purchaseDTO.getUserId());
        purchaseService.save(purchaseDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/revert/purchase")
    public ResponseEntity<HttpStatus> revertPurchase(@RequestParam("purchaseId") Long purchaseId) {
        purchaseService.revertPurchase(purchaseId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
