package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.dto.UserDTO;
import com.playtomic.tests.wallet.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImp userService;

    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {

        UserDTO userConvertedDTO = userService.findById(userDTO.getId());
        userConvertedDTO.setFirstName(userDTO.getFirstName());
        userConvertedDTO.setLastName(userDTO.getLastName());
        userConvertedDTO.setEmail(userDTO.getEmail());
        userService.save(userConvertedDTO);
        return ResponseEntity.ok().body(userConvertedDTO);
    }
}
