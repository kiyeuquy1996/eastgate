package com.eastgate.demo.service;

import com.eastgate.demo.service.DTO.UserRequestDTO;
import com.eastgate.demo.service.DTO.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    List<UserResponseDTO> getAllKeys(Optional<String> username);

    boolean isValidKey(String secretKey);
}
