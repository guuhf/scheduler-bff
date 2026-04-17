package com.guuh.scheduler_bff.business;


import com.guuh.scheduler_bff.business.dtos.PhoneDTO;
import com.guuh.scheduler_bff.infrastructure.client.PhoneClient;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneClient phoneClient;

    public PhoneDTO addPhoneToUser(PhoneDTO phoneDTO, String token) {
       return phoneClient.addPhoneToUser(token, phoneDTO);
    }

    public PhoneDTO updatePhones(PhoneDTO phoneDTO, Long id, String token){
        return phoneClient.phoneUpdate(token, phoneDTO, id);
    }

    public PhoneDTO getPhoneData(Long id, String token) {
        return phoneClient.getPhoneData(token, id);
    }

    public void deletePhone(Long id, String token) {
        phoneClient.deletePhone(token, id);
    }
}
