package com.eastgate.demo.service.impl;

import com.eastgate.demo.repository.entity.User;
import com.eastgate.demo.repository.entity.UserRepository;
import com.eastgate.demo.service.DTO.UserRequestDTO;
import com.eastgate.demo.service.DTO.UserResponseDTO;
import com.eastgate.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Random random = new Random();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        // TODO: Tạo mới secretKey theo công thức : secretKey = <username>#<random_number_from_1_to_10>
        String secretKey = generateSecretKey(userRequestDTO.getUsername());

        // Lưu user key
        User user = new User(userRequestDTO.getUsername(), secretKey);
        userRepository.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setSecretKey(user.getSecretKey());
        userResponseDTO.setCreatedDate(user.getCreatedDate());
        return userResponseDTO;
    }

    public List<UserResponseDTO> getAllKeys(Optional<String> username) {
        return username.map(userRepository::findByUsernameOrderByCreatedDateAsc)
                .orElseGet(userRepository::findAll)
                .stream()
                .map(UserResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public boolean isValidKey(String secretKey) {
        Optional<User> UserOpt = userRepository.findBySecretKey(secretKey);
        return UserOpt.isPresent() && UserOpt.get().getCreatedDate().isAfter(LocalDateTime.now().minusMinutes(10));
    }

    public void deleteExpiredKeys() {
        userRepository.deleteByCreatedDateBefore(LocalDateTime.now().minusMinutes(10));
    }

    private String generateSecretKey(String username) {
        int randomNumber = random.nextInt(10) + 1;
        return Base64.getEncoder().encodeToString((username + "#" + randomNumber).getBytes());
    }
}
