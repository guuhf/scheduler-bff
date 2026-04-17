package com.guuh.scheduler_bff.controller;


import com.guuh.scheduler_bff.business.PhoneService;
import com.guuh.scheduler_bff.business.dtos.PhoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/me/phones")
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @PostMapping()
    public ResponseEntity<PhoneDTO> addPhoneToUser(@RequestBody PhoneDTO phoneDTO,
                                                   @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(phoneService.addPhoneToUser(phoneDTO, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneDTO> phoneUpdate(@RequestBody PhoneDTO phoneDTO,
                                                @PathVariable Long id,
                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(phoneService.updatePhones(phoneDTO, id, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneDTO> getPhoneData(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(phoneService.getPhoneData(id, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id,
                                            @RequestHeader("Authorization") String token) {
        phoneService.deletePhone(id, token);
        return ResponseEntity.status(204).build();
    }
}
