package com.guuh.scheduler_bff.controller;


import com.guuh.scheduler_bff.business.UserService;
import com.guuh.scheduler_bff.business.dtos.request.LoginRequestDTO;
import com.guuh.scheduler_bff.business.dtos.request.UserRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.UserResponseDTO;
import com.guuh.scheduler_bff.infrastructure.configs.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a user", description = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "409", description = "User already exists")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.status(201).body(userService.saveUser(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Do the user login")
    @ApiResponse(responseCode = "200", description = "User logged")
    @ApiResponse(responseCode = "400", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public String login(@RequestBody LoginRequestDTO dto) {
        return userService.userLogin(dto);
    }

    @GetMapping("/me")
    @Operation(summary = "Get user data", description = "Get the user credentials")
    @ApiResponse(responseCode = "200", description = "User data received")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<UserResponseDTO> getLoggedUserData(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.status(200).body(userService.getLoggedUserData(token));
    }

    @DeleteMapping("/me")
    @Operation(summary = "Delete user", description = "Delete a user")
    @ApiResponse(responseCode = "204", description = "User deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<Void> deleteUserById(@RequestHeader(name = "Authorization", required = false) String token) {
        userService.deleteUser(token);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/me")
    @Operation(summary = "Update user data", description = "Update user credentials")
    @ApiResponse(responseCode = "200", description = "User data updated")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Unexpected error")
    public ResponseEntity<UserResponseDTO> updateUserById(@RequestBody UserRequestDTO dto,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.status(200).body(userService.updateUser(dto, token));
    }
}
