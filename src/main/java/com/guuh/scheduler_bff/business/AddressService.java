package com.guuh.scheduler_bff.business;

import com.guuh.scheduler_bff.business.dtos.request.AddressRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.AddressResponseDTO;
import com.guuh.scheduler_bff.infrastructure.client.AddressClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressClient addressClient;

    public AddressResponseDTO addAddresToUser(AddressRequestDTO dto, String token){
        return addressClient.addAddressToUser(token, dto);
    }

    public AddressResponseDTO updateAddress(AddressRequestDTO dto, Long id, String token) {
        return addressClient.addressUpdate(token, dto, id);
    }

    public AddressResponseDTO getAddressData(Long id, String token) {
        return addressClient.getAddressData(token, id);
    }

    public void deleteAddress(Long id, String token) {
        addressClient.deleteAddress(token, id);
    }
}

