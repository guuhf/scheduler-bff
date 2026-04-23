package com.guuh.scheduler_bff.business;

import com.guuh.scheduler_bff.business.dtos.request.LoginRequestDTO;
import com.guuh.scheduler_bff.business.dtos.request.UserRequestDTO;
import com.guuh.scheduler_bff.business.dtos.response.UserResponseDTO;
import com.guuh.scheduler_bff.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserResponseDTO saveUser(UserRequestDTO dto) {
        return userClient.saveUser(dto);
    }

    public String userLogin(LoginRequestDTO dto){
        return userClient.login(dto);
    }

    public UserResponseDTO getLoggedUserData(String token) {
        return userClient.getLoggedUserData(token);
    }

    public void deleteUser(String token) {
        userClient.deleteUserById(token);
    }

    public UserResponseDTO updateUser(UserRequestDTO dto, String token) {
        return userClient.updateUserById(dto, token);
    }

}
