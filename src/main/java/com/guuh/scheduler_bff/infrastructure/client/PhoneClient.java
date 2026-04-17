package com.guuh.scheduler_bff.infrastructure.client;
import com.guuh.scheduler_bff.business.dtos.PhoneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface PhoneClient {

    @PostMapping("/user/me/phones")
    PhoneDTO addPhoneToUser(
            @RequestHeader("Authorization") String token,
            @RequestBody PhoneDTO phoneDTO);

    @PutMapping("/user/me/phones/{id}")
    PhoneDTO phoneUpdate(
            @RequestHeader("Authorization") String token,
            @RequestBody PhoneDTO phoneDTO,
            @PathVariable Long id);

    @GetMapping("/user/me/phones/{id}")
    PhoneDTO getPhoneData(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id);

    @DeleteMapping("/user/me/phones/{id}")
    Void deletePhone(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id);
}
