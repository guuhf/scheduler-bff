package com.guuh.scheduler_bff.business.dtos.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO{
    private String name;
    private String email;
    private String password;

    List<AddressResponseDTO> addresses;
    List<UserResponseDTO> phones;
}
