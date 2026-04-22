package com.guuh.scheduler_bff.controller;


import com.guuh.scheduler_bff.business.PhoneService;
import com.guuh.scheduler_bff.business.dtos.PhoneDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/me/phones")
@RequiredArgsConstructor
@Tag(name = "Phone", description = "Phone management")
public class PhoneController {
    private final PhoneService phoneService;

    @PostMapping()
    @Operation(summary = "Add a phone", description = "Add a new phone to the authenticated user")
    @ApiResponse(responseCode = "201", description = "Phone created")
    @ApiResponse(responseCode = "409", description = "Phone already exists for this user")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<PhoneDTO> addPhoneToUser(@RequestBody PhoneDTO phoneDTO,
                                                   @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(phoneService.addPhoneToUser(phoneDTO, token));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update phone", description = "Update a specific phone of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Phone updated")
    @ApiResponse(responseCode = "404", description = "Phone not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<PhoneDTO> phoneUpdate(@RequestBody PhoneDTO phoneDTO,
                                                @PathVariable Long id,
                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(phoneService.updatePhones(phoneDTO, id, token));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get phone", description = "Retrieve a specific phone of the authenticated user")
    @ApiResponse(responseCode = "200", description = "Phone found")
    @ApiResponse(responseCode = "404", description = "Phone not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<PhoneDTO> getPhoneData(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(phoneService.getPhoneData(id, token));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete phone", description = "Delete a specific phone of the authenticated user")
    @ApiResponse(responseCode = "204", description = "Phone deleted")
    @ApiResponse(responseCode = "404", description = "Phone not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id,
                                            @RequestHeader("Authorization") String token) {
        phoneService.deletePhone(id, token);
        return ResponseEntity.status(204).build();
    }

}
