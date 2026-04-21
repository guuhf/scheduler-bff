package com.guuh.scheduler_bff.controller;


import com.guuh.scheduler_bff.business.UserService;
import com.guuh.scheduler_bff.business.dtos.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a user", description = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created")
    @ApiResponse(responseCode = "409", description = "User already exists")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Do the user login")
    @ApiResponse(responseCode = "200", description = "User logged")
    @ApiResponse(responseCode = "400", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.userLogin(userDTO);
    }

    @GetMapping("/me")
    @Operation(summary = "Get user data", description = "Get the user credentials")
    @ApiResponse(responseCode = "200", description = "User data received")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<UserDTO> getLoggedUserData(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(200).body(userService.getLoggedUserData(token));
    }

    @DeleteMapping("/me")
    @Operation(summary = "Delete user", description = "Delete a user")
    @ApiResponse(responseCode = "204", description = "User deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<Void> deleteUserById(@RequestHeader("Authorization") String token) {
        userService.deleteUser(token);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/me")
    @Operation(summary = "Update user data", description = "Update user credentials")
    @ApiResponse(responseCode = "200", description = "User data updated")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserDTO userDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(200).body(userService.updateUser(userDTO, token));
    }
}
