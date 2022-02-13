package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.dto.TransactionDTO;
import com.playtomic.tests.wallet.dto.UserDTO;
import com.playtomic.tests.wallet.service.imp.TransactionServiceImp;
import com.playtomic.tests.wallet.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transaction")
public class TransactionController {

    private final TransactionServiceImp transactionService;
    private final UserServiceImp userService;

    public TransactionController(TransactionServiceImp transactionService, UserServiceImp userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping(value = "/history/user")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistoryList(@RequestParam("userId") Long userId,
                                                                          @RequestParam(value = "startDate") String startDate,
                                                                          @RequestParam(value = "endDate") String endDate) {

        UserDTO userDTO = userService.findById(userId);
        List<TransactionDTO> conversationDTOResponse = transactionService.findAllByCreatedAtBetweenFirstDateAndLastDate(userDTO.getId(), startDate, endDate);
        return new ResponseEntity<>(conversationDTOResponse, HttpStatus.OK);
    }
}
