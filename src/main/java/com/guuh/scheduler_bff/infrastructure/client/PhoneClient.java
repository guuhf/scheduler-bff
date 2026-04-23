package com.guuh.scheduler_bff.infrastructure.client;
import com.guuh.scheduler_bff.business.dtos.request.PhoneRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.PhoneResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "phone", url = "${user.url}")
public interface PhoneClient {

    @PostMapping("/user/me/phones")
    PhoneResponseDTO addPhoneToUser(
            @RequestHeader("Authorization") String token,
            @RequestBody PhoneRequestDTO dto);

    @PutMapping("/user/me/phones/{id}")
    PhoneResponseDTO phoneUpdate(
            @RequestHeader("Authorization") String token,
            @RequestBody PhoneRequestDTO dto,
            @PathVariable Long id);

    @GetMapping("/user/me/phones/{id}")
    PhoneResponseDTO getPhoneData(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id);

    @DeleteMapping("/user/me/phones/{id}")
    Void deletePhone(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id);
}
