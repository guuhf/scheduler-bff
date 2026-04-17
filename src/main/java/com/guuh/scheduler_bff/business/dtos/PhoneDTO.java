package com.guuh.scheduler_bff.business.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDTO {

    private String phoneNumber;
    private String countryCode;
}
