package com.guuh.scheduler_bff.infrastructure.client;

import com.guuh.scheduler_bff.business.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user/me")
    UserDTO getLoggedUserData(@RequestHeader("Authorization") String token);

    @PostMapping("/user")
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @PostMapping("/user/login")
    String login(@RequestBody UserDTO userDTO);

    @DeleteMapping("/user/me")
    Void deleteUserById(@RequestHeader("Authorization") String token);

    @PutMapping("/user/me")
    UserDTO updateUserById(@RequestBody UserDTO userDTO,
                           @RequestHeader("Authorization") String token);
}
