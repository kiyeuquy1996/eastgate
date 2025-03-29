package com.eastgate.demo.controller;

import com.eastgate.demo.repository.entity.User;
import com.eastgate.demo.service.DTO.UserRequestDTO;
import com.eastgate.demo.service.DTO.UserResponseDTO;
import com.eastgate.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * API tạo mới user
     * @return newUser
     */
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createKey(@RequestBody UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null || userRequestDTO.getUsername().isBlank() || userRequestDTO.getUsername().length() > 150) {
            return ResponseEntity.badRequest().build();
        }
        UserResponseDTO newUser = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(newUser);
    }

    /**
     * API lấy tất cả key user
     * @return list UserResponseDTO
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllKeys(@RequestParam Optional<String> username) {
        return ResponseEntity.ok(userService.getAllKeys(username));
    }

    /**
     * API validate secretKey
     * @return boolean
     */
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateKey(@RequestParam String secretKey) {
        boolean isValid = userService.isValidKey(secretKey);
        return isValid ? ResponseEntity.ok(true) : ResponseEntity.status(404).body(false);
    }
}
