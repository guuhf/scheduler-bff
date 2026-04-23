package com.guuh.scheduler_bff.infrastructure.client;

import com.guuh.scheduler_bff.business.dtos.request.AddressRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.AddressResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "addresses", url = "${user.url}")
public interface AddressClient {

    @PostMapping("/user/me/addresses")
    AddressResponseDTO addAddressToUser(
            @RequestHeader("Authorization") String token,
            @RequestBody AddressRequestDTO dto);

    @PutMapping("/user/me/addresses/{id}")
    AddressResponseDTO addressUpdate(
            @RequestHeader("Authorization") String token,
            @RequestBody AddressRequestDTO dto,
            @PathVariable Long id);

    @GetMapping("/user/me/addresses/{id}")
    AddressResponseDTO getAddressData(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id);

    @DeleteMapping("/user/me/addresses/{id}")
    Void deleteAddress(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id);
}
