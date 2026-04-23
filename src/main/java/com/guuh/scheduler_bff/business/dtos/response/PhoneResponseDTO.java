package com.guuh.scheduler_bff.business.dtos.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  PhoneResponseDTO {

    private String phoneNumber;
    private String countryCode;
}
