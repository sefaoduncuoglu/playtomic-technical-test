package com.playtomic.tests.wallet.converter;

import com.playtomic.tests.wallet.dto.UserDTO;
import com.playtomic.tests.wallet.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public User convertToEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .build();
    }
}