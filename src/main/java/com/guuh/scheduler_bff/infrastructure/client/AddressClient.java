package com.guuh.scheduler_bff.infrastructure.client;

import com.guuh.scheduler_bff.business.dtos.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "addresses", url = "${user.url}")
public interface AddressClient {

    @PostMapping("/user/me/addresses")
    AddressDTO addAddressToUser(
            @RequestHeader("Authorization") String token,
            @RequestBody AddressDTO addressDTO);

    @PutMapping("/user/me/addresses/{id}")
    AddressDTO addressUpdate(
            @RequestHeader("Authorization") String token,
            @RequestBody AddressDTO addressDTO,
            @PathVariable Long id);

    @GetMapping("/user/me/addresses/{id}")
    AddressDTO getAddressData(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id);

    @DeleteMapping("/user/me/addresses/{id}")
    Void deleteAddress(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id);
}
