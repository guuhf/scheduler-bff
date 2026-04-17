package com.guuh.scheduler_bff.controller;

import com.guuh.scheduler_bff.business.AddressService;
import com.guuh.scheduler_bff.business.dtos.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/me/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping()
    public ResponseEntity<AddressDTO> addAddressToUser(@RequestBody AddressDTO addressDTO,
                                                       @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(addressService.addAddresToUser(addressDTO, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> addressUpdate(@RequestBody AddressDTO addressDTO,
                                                    @PathVariable Long id,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(addressService.updateAddress(addressDTO, id, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressData(@PathVariable Long id,
                                                     @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(addressService.getAddressData(id, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id,
                                            @RequestHeader("Authorization") String token) {
        addressService.deleteAddress(id, token);
        return ResponseEntity.status(204).build();
    }
}
