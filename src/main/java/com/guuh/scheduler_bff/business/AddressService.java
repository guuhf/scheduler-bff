package com.guuh.scheduler_bff.business;

import com.guuh.scheduler_bff.business.dtos.AddressDTO;
import com.guuh.scheduler_bff.infrastructure.client.AddressClient;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressClient addressClient;

    public AddressDTO addAddresToUser(AddressDTO addressDTO, String token){
        return addressClient.addAddressToUser(token, addressDTO);
    }

    public AddressDTO updateAddress(AddressDTO addressDTO, Long id, String token) {
        return addressClient.addressUpdate(token, addressDTO, id);
    }

    public AddressDTO getAddressData(Long id, String token) {
        return addressClient.getAddressData(token, id);
    }

    public void deleteAddress(Long id, String token) {
        addressClient.deleteAddress(token, id);
    }
}

