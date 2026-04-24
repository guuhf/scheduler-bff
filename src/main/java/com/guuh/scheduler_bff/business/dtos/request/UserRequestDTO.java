package com.guuh.scheduler_bff.business.dtos.request;


import com.guuh.scheduler_bff.business.dtos.response.AddressResponseDTO;
import com.guuh.scheduler_bff.business.dtos.response.PhoneResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;

    List<AddressRequestDTO> addresses;
    List<PhoneRequestDTO> phones;
}
