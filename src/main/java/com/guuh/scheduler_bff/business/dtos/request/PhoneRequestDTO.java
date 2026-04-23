package com.guuh.scheduler_bff.business.dtos.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneRequestDTO {

    private String phoneNumber;
    private String countryCode;
}
