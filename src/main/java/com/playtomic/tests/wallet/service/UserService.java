package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.dto.UserDTO;

public interface UserService {

    UserDTO findById(Long id);

    void save(UserDTO userDTO);
}
