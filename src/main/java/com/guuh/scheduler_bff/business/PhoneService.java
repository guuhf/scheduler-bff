package com.guuh.scheduler_bff.business;


import com.guuh.scheduler_bff.business.dtos.request.PhoneRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.PhoneResponseDTO;
import com.guuh.scheduler_bff.infrastructure.client.PhoneClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneClient phoneClient;

    public PhoneResponseDTO addPhoneToUser(PhoneRequestDTO dto, String token) {
       return phoneClient.addPhoneToUser(token, dto);
    }

    public  PhoneResponseDTO updatePhones(PhoneRequestDTO dto, Long id, String token){
        return phoneClient.phoneUpdate(token, dto, id);
    }

    public  PhoneResponseDTO getPhoneData(Long id, String token) {
        return phoneClient.getPhoneData(token, id);
    }

    public void deletePhone(Long id, String token) {
        phoneClient.deletePhone(token, id);
    }
}
