package com.playtomic.tests.wallet.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NonNull
    private String firstName;

    private String lastName;

    @NonNull
    private String email;

}
