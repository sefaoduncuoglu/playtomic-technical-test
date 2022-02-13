package com.playtomic.tests.wallet.service.imp;

import com.playtomic.tests.wallet.converter.UserConverter;
import com.playtomic.tests.wallet.dto.UserDTO;
import com.playtomic.tests.wallet.entity.User;
import com.playtomic.tests.wallet.exception.UserNotFoundException;
import com.playtomic.tests.wallet.repository.UserRepository;
import com.playtomic.tests.wallet.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public UserServiceImp(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userConverter.convertToDTO(user);
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = userConverter.convertToEntity(userDTO);
        userRepository.save(user);
    }
}
