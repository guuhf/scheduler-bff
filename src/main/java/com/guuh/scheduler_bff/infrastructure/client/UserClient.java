package com.guuh.scheduler_bff.infrastructure.client;

import com.guuh.scheduler_bff.business.dtos.request.LoginRequestDTO;
import com.guuh.scheduler_bff.business.dtos.request.UserRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user/me")
    UserResponseDTO getLoggedUserData(@RequestHeader("Authorization") String token);

    @PostMapping("/user")
    UserResponseDTO saveUser(@RequestBody UserRequestDTO dto);

    @PostMapping("/user/login")
    String login(@RequestBody LoginRequestDTO dto);

    @DeleteMapping("/user/me")
    Void deleteUserById(@RequestHeader("Authorization") String token);

    @PutMapping("/user/me")
    UserResponseDTO updateUserById(@RequestBody UserRequestDTO dto,
                                   @RequestHeader("Authorization") String token);
}
