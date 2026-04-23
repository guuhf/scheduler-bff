package com.guuh.scheduler_bff.controller;

import com.guuh.scheduler_bff.business.AddressService;
import com.guuh.scheduler_bff.business.dtos.request.AddressRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.AddressResponseDTO;
import com.guuh.scheduler_bff.infrastructure.configs.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/me/addresses")
@RequiredArgsConstructor
@Tag(name = "Address", description = "Address management")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class AddressController {
    private final AddressService addressService;

    @PostMapping()
    @Operation(summary = "Add an address", description = "Add a new address to the authenticated user")
    @ApiResponse(responseCode = "201", description = "Address created")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<AddressResponseDTO> addAddressToUser(@RequestBody AddressRequestDTO dto,
                                                               @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.status(201).body(addressService.addAddresToUser(dto, token));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update address", description = "Update a specific address of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Address updated")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<AddressResponseDTO> addressUpdate(@RequestBody AddressRequestDTO dto,
                                                            @PathVariable Long id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(addressService.updateAddress(dto, id, token));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address", description = "Retrieve a specific address of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Address found")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<AddressResponseDTO> getAddressData(@PathVariable Long id,
                                                             @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(addressService.getAddressData(id, token));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete address", description = "Delete a specific address of the authenticated user")
    @ApiResponse(responseCode = "204", description = "Address deleted")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id,
                                            @RequestHeader(name = "Authorization", required = false) String token) {
        addressService.deleteAddress(id, token);
        return ResponseEntity.status(204).build();
    }

}
