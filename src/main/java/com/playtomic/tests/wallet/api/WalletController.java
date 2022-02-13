package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.dto.UserBalanceDTO;
import com.playtomic.tests.wallet.dto.UserDTO;
import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.dto.WalletTopUpDTO;
import com.playtomic.tests.wallet.service.imp.UserServiceImp;
import com.playtomic.tests.wallet.service.imp.WalletServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final UserServiceImp userService;
    private final WalletServiceImp walletService;

    public WalletController(UserServiceImp userService, WalletServiceImp walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletDTO> getWalletById(@RequestParam("walletId") Long walletId) {
        return ResponseEntity.ok().body(walletService.findById(walletId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createWallet(@Valid @RequestBody WalletDTO walletDTO) {
        userService.findById(walletDTO.getUserId());
        walletService.save(walletDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/balance/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBalanceDTO> getUserBalance(@RequestParam("userId") Long userId) {
        UserDTO userDTO = userService.findById(userId);
        UserBalanceDTO userBalanceDTO = walletService.getUserCurrentBalance(userDTO.getId());
        return new ResponseEntity<>(userBalanceDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/topUpWallet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletTopUpDTO> topUpWallet(@Valid @RequestBody WalletTopUpDTO walletTopUpDTO) {
        WalletTopUpDTO response = walletService.topUpWallet(walletTopUpDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/refund", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletTopUpDTO> refund(@Valid @RequestBody WalletTopUpDTO walletTopUpDTO) {
        walletService.refundTransaction(walletTopUpDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
