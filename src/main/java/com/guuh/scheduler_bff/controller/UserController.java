package com.guuh.scheduler_bff.controller;


import com.guuh.scheduler_bff.business.UserService;
import com.guuh.scheduler_bff.business.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.userLogin(userDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getLoggedUserData(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(200).body(userService.getLoggedUserData(token));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUserById(@RequestHeader("Authorization") String token) {
        userService.deleteUser(token);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserDTO userDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(200).body(userService.updateUser(userDTO, token));
    }
}
