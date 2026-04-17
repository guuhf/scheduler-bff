package com.guuh.scheduler_bff.business;

import com.guuh.scheduler_bff.business.dtos.UserDTO;
import com.guuh.scheduler_bff.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDTO saveUser(UserDTO userDTO) {
        return userClient.saveUser(userDTO);
    }

    public String userLogin(UserDTO userDTO){
        return userClient.login(userDTO);
    }

    public UserDTO getLoggedUserData(String token) {
        return userClient.getLoggedUserData(token);
    }

    public void deleteUser(String token) {
        userClient.deleteUserById(token);
    }

    public UserDTO updateUser(UserDTO userDTO, String token) {
        return userClient.updateUserById(userDTO, token);
    }

}
